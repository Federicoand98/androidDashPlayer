package jLibdash.dash.mpd;

public interface IMultipleSegmentBase extends ISegmentBase {
	
	public ISegmentTimeline getSegmentTimeline();
	public IURLType getBitstreamSwitching();
	public int getDuration();
	public int getStartNumber();

}
