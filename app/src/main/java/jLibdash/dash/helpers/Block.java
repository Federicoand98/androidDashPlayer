package jLibdash.dash.helpers;

import java.util.Arrays;

public class Block {
	
	private byte[] data;
	private float millisec;
	private long offset;
	
	public Block() {
		this.millisec = 0;
		this.offset = 0;
	}
	
	public Block(byte[] data) {
		this.data = data;
		this.millisec = 0;
		this.offset = 0;
	}

	public byte[] getData() {
		return data;
	}

	public void setData(byte[] data) {
		this.data = data;
	}

	public float getMillisec() {
		return millisec;
	}

	public void setMillisec(float millisec) {
		this.millisec = millisec;
	}

	public long getOffset() {
		return offset;
	}

	public void setOffset(int offset) {
		this.offset = offset;
	}
	
	public int getLength() {
		return this.data.length;
	}
	
	public boolean equals(Block block) {
		if(Arrays.equals(this.data, block.getData()) 
				&& this.millisec == block.getMillisec()
				&& this.offset == block.getOffset())
			return true;
		else return false;
	}

}
