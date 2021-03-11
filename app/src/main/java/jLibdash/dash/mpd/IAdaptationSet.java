package jLibdash.dash.mpd;

import java.util.Vector;

public interface IAdaptationSet extends IRepresentationBase {
	
	public Vector<IDescriptor> getAccessibility();
	public Vector<IDescriptor> getRole();
	public Vector<IDescriptor> getRating();
	public Vector<IDescriptor> getViewpoint();
	public Vector<IContentComponent> getContentComponent();
	public Vector<IBaseUrl> getBaseURLs();
	public ISegmentBase getSegmentBase();
	public ISegmentList getSegmentList();
	public ISegmentTemplate getSegmentTemplate();
	public Vector<IRepresentation> getRepresentation();
	public String getXlinkHref();
	public String getXlinkActuate();
	public int getId();
	public int getGroup();
	public String getLang();
	public String getContentType();
	public String getPar();
	public int getMinBandwidth();
	public int getMaxBandwidth();
	public int getMinWidth();
	public int getMaxWidth();
	public int getMinHeight();
	public int getMaxHeight();
	public String getMinFramerate();
	public String getMaxFramerate();
	public boolean segmentAlignmentIsBoolValue();
	public boolean hasSegmentAlignment();
	public int getSegmentAligment();
	public boolean subsegmentAlignmentIsBoolValue();
	public boolean hasSubsegmentAlignment();
	public int getSubsegmentAlignment();
	public byte getSubsegmentStartsWithSAP();
	public boolean getBitstreamSwitching();
}
