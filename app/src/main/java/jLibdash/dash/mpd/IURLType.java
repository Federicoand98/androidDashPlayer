package jLibdash.dash.mpd;

import java.util.Vector;

import jLibdash.dash.metrics.HTTPTransactionType;


public interface IURLType extends IMPDElement {
	
	public String getSourceURL();
	public String getRange();
	public HTTPTransactionType getType();
	public ISegment toSegment(Vector<IBaseUrl> baseurls);
	
	public int hashCode();
	public boolean equals(Object obj);

}
