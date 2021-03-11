package jLibdash.dash.mpd;

import java.util.Vector;

public interface IBaseUrl extends IMPDElement {
	
	public String getUrl();
	public String getServiceLocation();
	public String getByteRange();
	public ISegment toMediaSegment(Vector<IBaseUrl> baseurls);

}
