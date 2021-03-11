package jLibdash.dash.helpers;

public class ObjectRange {
	
	private long startByte;
	private long endByte;
	
	public ObjectRange(long startByte, long endByte) {
		this.startByte = startByte;
		this.endByte = endByte;
	}

	public long getStartByte() {
		return startByte;
	}

	public long getEndByte() {
		return endByte;
	}
	
	public boolean equals(ObjectRange objRange) {
		if(this.startByte == objRange.getStartByte()
				&& this.endByte == objRange.getEndByte())
			return true;
		else return false;
	}

}
