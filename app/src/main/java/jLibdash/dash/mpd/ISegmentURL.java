package jLibdash.dash.mpd;

import java.util.Vector;

public interface ISegmentURL extends IMPDElement {
	
	public String  getMediaURI();
	public String  getMediaRange();
	public String getIndexURI();
	public String getIndexRange();
	public ISegment toMediaSegment(Vector<IBaseUrl> baseurls);
	public ISegment toIndexSegment(Vector<IBaseUrl> baseurls);
	
	public int hashCode();
	public boolean equals(Object obj);

}
