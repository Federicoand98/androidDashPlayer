package jLibdash.dash.mpd;

import java.util.Vector;

public interface IMetrics extends IMPDElement {
	
	public Vector<IDescriptor> getReportings();
	public Vector<IRange> getRanges();
	public String getMetrics();

}
