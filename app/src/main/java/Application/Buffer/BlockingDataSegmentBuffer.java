package Application.Buffer;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import Application.Network.DataSegment;

public class BlockingDataSegmentBuffer implements DataSegmentBuffer {

    private Deque<DataSegment> rawBuffer;
    //private Deque<DecodedSegment<?>> decodedBuffer;

    //private List<DataSegmentBufferObserver> observers;
    private final int capacity;
    private int freeSpace;

    private boolean blocking;
    private Lock lock;
    private Condition waitForSpace;
    private Condition waitForElement;
    private Condition waitForDecoding;

    public BlockingDataSegmentBuffer(int capacity) {
        this.capacity = capacity;
        this.freeSpace = capacity;
        this.rawBuffer = new ArrayDeque<DataSegment>();
        //this.decodedBuffer = new ArrayDeque<DecodedSegment<?>>();
        this.blocking = true;

        lock = new ReentrantLock();
        waitForSpace = lock.newCondition();
        waitForElement = lock.newCondition();
        waitForDecoding = lock.newCondition();

        //this.observers = new ArrayList<DataSegmentBufferObserver>();
    }


    @Override
    public boolean put(DataSegment dataSegment) throws InterruptedException {
        lock.lock();
        try {
            while(freeSpace < dataSegment.getSize() && blocking) {
                //observers.forEach(o -> o.onWaitingForRawSpace());
                waitForSpace.await();
            }

            if(freeSpace < dataSegment.getSize())
                return false;

            rawBuffer.add(dataSegment);
            freeSpace -= dataSegment.getSize();

            //observers.forEach(o -> o.onRawSegmentPut(dataSegment, freeSpace));
            waitForDecoding.signalAll();
        } finally {lock.unlock();}

        return true;
    }

    /*
    @Override
    public DecodedSegment<?> take() throws InterruptedException {
        DecodedSegment<?> res = null;
        lock.lock();
        try {
            while(decodedBuffer.size() == 0 && blocking) {
                observers.forEach(o -> o.onWaitingForDecodedElement());
                waitForElement.await();
            }

            if(decodedBuffer.size() == 0)
                return null;

            res = decodedBuffer.pop();
            freeSpace += res.getDataSegment().getSize();

            for(DataSegmentBufferObserver o : observers)
                o.onDecodedSegmentTaken(res, freeSpace);

            waitForSpace.signalAll();
        } finally {lock.unlock();}

        return res;
    }
     */

    @Override
    public DataSegment peekFirstElement() throws InterruptedException {
        DataSegment res = null;
        lock.lock();

        try {
            while(rawBuffer.size() == 0 && blocking) {
                //observers.forEach(o -> o.onWaitingForRawElement());
                waitForDecoding.await();
            }

            if(rawBuffer.size() == 0)
                return null;

            res = rawBuffer.peek();

        } finally {
            lock.unlock();
        }

        /*
        for(DataSegmentBufferObserver o : observers) {
            o.onDecodedSegmentPeeked(res);
        }
         */

        return res;
    }

    /*
    @Override
    public boolean switchElement(DecodedSegment<?> decodedSegment) throws InterruptedException {
        lock.lock();
        DataSegment dataSegment = null;

        try {
            if(rawBuffer.size() == 0) {
                return false;
            }

            dataSegment = rawBuffer.remove();
            decodedBuffer.add(decodedSegment);

            waitForElement.signalAll();
        } finally {
            lock.unlock();
        }

        for(DataSegmentBufferObserver o : observers) {
            o.onSegmentSwiched(decodedSegment);
        }

        return true;
    }
    */

    @Override
    public void clear() {
        lock.lock();
        try {
            rawBuffer.clear();
            //decodedBuffer.clear();
            this.freeSpace = capacity;

            waitForSpace.signalAll();
            waitForElement.signalAll();

            //observers.forEach(o -> o.onBuffersCleaned());
        } finally {lock.unlock();}

    }

    @Override
    public int lenght() {
        lock.lock();
        try {
            return capacity - freeSpace;
        } finally {
            lock.unlock();
        }
    }

    @Override
    public int capacity() {
        lock.lock();
        try {
            return capacity;
        } finally {
            lock.unlock();
        }
    }

    @Override
    public void setBlocking(boolean blocking) {
        lock.lock();
        try {
            this.blocking = blocking;
        } finally {
            lock.unlock();
        }
    }
}
