package jLibdash.dash.helpers;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Iterator;
import java.util.Optional;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class SyncedBlockStream {
	
	protected long length;
	protected Deque<Block> blockQueque;
	private Lock lock;
	private Condition condition;
	private boolean eos;
	
	public SyncedBlockStream() {
		this.eos = false;
		this.length = 0;
		blockQueque = new ArrayDeque<Block>();
		this.lock = new ReentrantLock();
		this.condition = lock.newCondition();
	}
	
	public void popAndDeleteFront() throws InterruptedException {
		lock.lock();
		try {
			if(this.blockQueque.isEmpty())
				return;
			
			this.length -= (long) this.blockQueque.getFirst().getLength();
			this.blockQueque.pop();
		} finally {lock.unlock();}
	}
	
	public void pushBack(Block block) throws InterruptedException {
		lock.lock();
		try {
			this.length += (long) block.getLength();
			this.blockQueque.add(block);
			condition.signalAll();
		} finally {lock.unlock();}
		
	}
	
	public void pushFront(Block block) throws InterruptedException {
		lock.lock();
		try {
			this.length += (long) block.getLength();
			this.blockQueque.push(block);
			condition.signalAll();
		} finally {lock.unlock();}
	}
	
	public Optional<Block> getBytes(int len) throws InterruptedException {
		lock.lock();
		try {
			
			while(this.length == 0 && !this.eos)
				condition.await();
			
			if(this.length < len || this.length == 0)
				return Optional.empty();
			
			byte[] newData = new byte[len];
			this.blockQuequeGetBytes(newData, len);
			
			this.length -= len;
			return Optional.of(new Block(newData));
		} finally {lock.unlock();}
	}
	
	public int getBytes(byte[] data, int len) throws InterruptedException {
		lock.lock();
		try {

			while(this.length == 0 && !this.eos)
				condition.await();
			
			if(this.length == 0)
				return 0;
			
			int min_len = Math.min(data.length, (int) this.length);
			if(len > min_len)
				len = min_len;
			
			this.blockQuequeGetBytes(data, len);
			this.length -= len;
			
			return len;
		} finally {lock.unlock();}
	}
	
	public int peekBytes(byte[] data, int len) throws InterruptedException {
		lock.lock();
		try {
			
			while(this.length == 0 && !this.eos)
				condition.await();
			
			if(this.length == 0)
				return 0;
			
			int min_len = Math.min(data.length, (int) this.length);
			if(len > min_len)
				len = min_len;
			
			this.blockQuequePeekBytes(data, len, 0);
			
			return len;
		} finally {lock.unlock();}
	}
	
	public int peekBytes(byte[] data, int len, int offset) throws InterruptedException {
		lock.lock();
		try {
			
			while(this.length == 0 || offset >= this.length && !this.eos)
				condition.await();
			
			if(this.length == 0 || offset >= this.length)
				return 0;
			
			int min_len = Math.min(data.length, (int) (this.length-offset));
			if(len > min_len)
				len = min_len;
			
			this.blockQuequePeekBytes(data, len, offset);
			return len;
			
		} finally {lock.unlock();}
	}
	
	public long getLength() throws InterruptedException {
		lock.lock();
		try {
			return this.length;
		} finally {lock.unlock();}
	}
	
	public Optional<Block> getFront() throws InterruptedException {
		lock.lock();
		try {
			
			while(this.length == 0 && !this.eos)
				condition.await();
			
			if(this.blockQueque.isEmpty())
				return Optional.empty();
			
			Block ret = this.blockQueque.getFirst();
			this.length -= (float) ret.getLength();
			this.blockQueque.pop();
			
			return Optional.of(ret);
		} finally {lock.unlock();}
	}
	
	public Optional<Block> front() throws InterruptedException {
		lock.lock();
		try {
			
			while(this.length == 0 && !this.eos)
				condition.await();
			
			if(this.blockQueque.isEmpty())
				return Optional.empty();
			
			return Optional.of(this.blockQueque.getFirst());
		} finally {lock.unlock();}
	}
	
	private boolean blockQuequeGetBytes(byte[] data, int len) {
		
		int pos = 0;
		byte[] source, newFrontData;
		
		while(pos < len) {
			
			source = this.blockQueque.getFirst().getData();
			if((len-pos) < (source.length)) {
				
				for(int i = 0; i < (len - pos); i++)
					data[pos+i] = source[i];
				
				this.blockQueque.pop();
				
				newFrontData = new byte[source.length - (len - pos)];
				
				for(int i = 0; i < newFrontData.length; i++)
					newFrontData[i] = source[i + (len - pos)];
				
				this.blockQueque.push(new Block(newFrontData));
				return true;				
			}
			
			else {
				
				for(int i = 0; i < source.length; i++)
					data[i+pos] = source[i];
				pos += source.length;
				this.blockQueque.pop();				
			}
		}
		
		return false;
		
	}
	
	private boolean blockQuequePeekBytes(byte[] data, int len, int offset) {
		
		long pos = 0;
		int sub;
		byte[] source;
		
		Iterator<Block> iterator = this.blockQueque.iterator();
		while(iterator.hasNext()) {
			source = iterator.next().getData();
			if(offset > (pos + source.length))
				pos += source.length;
			else {
				sub = (int) (offset - pos);
				pos = (long) offset;
				
				for(int i = 0; i < len; i++, sub++) {
					if(sub == source.length) {
						source = iterator.next().getData();
						sub = 0;
					}
					data[i] = source[sub];
				}
				return true;
			}
		}
		return false;
		
		/* -- oppure:
	 	Optional<Byte[]> byteArrayOpt = this.getByteArray();
		if(!byteArrayOpt.isPresent() || offset ...)
			return false;
		Byte[] byteArray = byteArrayOpt.get();
		
		for(int i=0; i<len; i++)
			data[i] = byteArray[i+offset];
		
		return true; */
	}
	
	
	
	/* private Optional<Byte[]> getByteArray() {
		
		Iterator<Block> iterator = this.blockQueque.iterator();
		
		if(this.length > Integer.MAX_VALUE || this.length == 0)
			return Optional.empty();
		
		Vector<Byte> toReturn = new Vector<Byte>();
		byte[] source;
		
		while(iterator.hasNext()) {
			source = iterator.next().getData();
			
			for(int i=0; i<source.length; i++)
				toReturn.add(source[i]);
		}
		
		return Optional.of(toReturn.toArray(new Byte[0]));
	} */
	
	public Optional<Byte> byteAt(long position) throws InterruptedException {
		lock.lock();
		try {
			
			while(this.length < position && !this.eos)
				condition.await();
			
			if(position < 0 || position > this.length)
				return Optional.empty();
			
			long pos = 0;
			
			for(Block block : this.blockQueque) {
				
				if((pos + block.getLength()) > position)
					return Optional.of(block.getData()[(int) (position - pos)]);
				else
					pos += (long) block.getLength();			
			}

			return Optional.empty();
		} finally {lock.unlock();}
	}
	
	public Optional<Block> toBlock() throws InterruptedException {
		lock.lock();
		try {
			
			while(this.length == 0 && !this.eos)
				condition.await();
			
			if(this.length == 0 || this.length > Integer.MAX_VALUE)
				return Optional.empty();
			
			return this.getBytes((int) this.length);
		} finally {lock.unlock();}
	}
	
	public void clear() throws InterruptedException {
		lock.lock();
		try {
			this.blockQueque.clear();
			this.length = 0;
		} finally {lock.unlock();}
	}
	
	public void eraseFront(long len) throws InterruptedException {
		lock.lock();
		try {
			if(len > this.length)
				len = this.length;
			
			long actLen = 0;
			byte[] front, newFront;
			int diff;
			
			while(actLen < len) {
				if(this.blockQueque.size() == 0)
					return;
				
				front = this.blockQueque.getFirst().getData();
				
				if((actLen + front.length) <= len) {
					this.length -= (long) front.length;
					actLen += (long) front.length;
					this.blockQueque.pop();
				}
				else {
					diff = (int) (len - actLen);
					this.length -= (float) diff;
					actLen += (float) diff;
					
					newFront = new byte[front.length - diff];
					
					for(int i = 0; i<newFront.length; i++)
						newFront[i] = front[i+diff];
					
					this.blockQueque.pop();
					this.blockQueque.push(new Block(newFront));
				}
			}
		} finally {lock.unlock();}
	}
	
	public Optional<SyncedBlockStream> getBlocks(long len) throws InterruptedException {
		lock.lock();
		try {
			
			while(this.length == 0 && !this.eos)
				condition.await();
			
			if(len > this.length)
				return Optional.empty();
			
			SyncedBlockStream blocks = new SyncedBlockStream();
			Block front;
			byte[] block, newFront, frontData;
			long actLen = 0;
			
			while(actLen < len) {
				front = this.blockQueque.getFirst();
				
				if((actLen + front.getLength()) <= len) {
					this.length -= (long) front.getLength();
					actLen += front.getLength();
					
					blocks.pushBack(front);
					this.blockQueque.pop();
				}
				else {
					int diff = (int) (len - actLen);
					this.length -= (long) diff;
					actLen += (long) diff;
					
					frontData = front.getData();
					block = new byte[diff];
					newFront = new byte[frontData.length - diff];
					
					for(int i = 0; i < diff; i++)
						block[i] = frontData[i];
					blocks.pushBack(new Block(block));
					
					for(int i = 0; i < newFront.length; i++)
						newFront[i] = frontData[i+diff];
					
					this.blockQueque.pop();
					this.blockQueque.push(new Block(newFront));
				}
			}
			
			return Optional.of(blocks);
		} finally {lock.unlock();}
	}
	
	public void setEOS(boolean value) throws InterruptedException {
		lock.lock();
		try {
			this.eos = value;
			condition.signalAll();
		} finally {lock.unlock();}
		return;
	}

}
