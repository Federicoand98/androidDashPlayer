package jLibdash.dash.mpd;

import java.util.Vector;

public interface IPeriod extends IMPDElement {
	
	public Vector<IBaseUrl> getBaseURLs();
	public ISegmentBase getSegmentBase();
	public ISegmentList getSegmentList();
	public ISegmentTemplate getSegmentTemplate();
	public Vector<IAdaptationSet> getAdaptationSets();
	public Vector<ISubset> getSubsets();
	public String getXlinkHref();
	public String getXlinkActuate();
	public String getId();
	public String getStart();
	public String getDuration();
	public boolean getBitstreamSwitching();

}
