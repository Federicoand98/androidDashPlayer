package jLibdash.dash.mpd;

public class MultipleSegmentBase extends SegmentBase implements IMultipleSegmentBase {

	private SegmentTimeline segmentTimeline;
	private URLType bitstreamSwitching;
	private int duration;
	private int startNumber;
	
	public MultipleSegmentBase() {
		super();
		this.duration = 0;
		this.startNumber = 1;
	}

	public ISegmentTimeline getSegmentTimeline() {
		return this.segmentTimeline;
	}
	
	public void setSegmentTimeline(SegmentTimeline segmentTimeline) {
		this.segmentTimeline = segmentTimeline;
	}

	public IURLType getBitstreamSwitching() {
		return this.bitstreamSwitching;
	}
	
	public void setBitStreamSwitching(URLType bitstreamSwitching) {
		this.bitstreamSwitching = bitstreamSwitching;
	}

	public int getDuration() {
		return this.duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}
	
	public int getStartNumber() {
		return this.startNumber;
	}
	
	public void setStartNumber(int startNumber) {
		this.startNumber = startNumber;
	}
}
