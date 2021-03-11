package jLibdash.dash.mpd;

public interface ITimeline extends IMPDElement {
	
	public int getStartTime();
	public int getDuration();
	public int getRepeatCount();
	
	public int hashCode();
	public boolean equals(Object obj);

}
