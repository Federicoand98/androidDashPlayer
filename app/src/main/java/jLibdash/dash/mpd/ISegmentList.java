package jLibdash.dash.mpd;

import java.util.Vector;

public interface ISegmentList extends IMultipleSegmentBase {
	
	public Vector<ISegmentURL> getSegmentURLs();
	public String getXlinkHref();
	public String getXlinkActuate();
	
	public int hashCode();
	public boolean equals(Object obj);

}
