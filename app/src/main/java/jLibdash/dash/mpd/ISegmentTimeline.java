package jLibdash.dash.mpd;

import java.util.Vector;

public interface ISegmentTimeline extends IMPDElement {
	
	public Vector<ITimeline> getTimelines();
	
	public int hashCode();
	public boolean equals(Object obj);

}
