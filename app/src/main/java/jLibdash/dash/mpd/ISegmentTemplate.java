package jLibdash.dash.mpd;

import java.util.Vector;

public interface ISegmentTemplate extends IMultipleSegmentBase {
	
	public String getMedia();
	public String getIndex();
	public String getSegmentTemplateInitialization();
	public String getSegmentTemplateBitstreamSwitching();
	public ISegment toInitializationSegment(
			Vector<IBaseUrl> baseurls, String representationID, int bandwidth);
	public ISegment toBitstreamSwitchingSegment(
			Vector<IBaseUrl> baseurls, String representationID, int bandwidth);
	public ISegment getMediaSegmentFromNumber(
			Vector<IBaseUrl> baseurls, String representationID, int bandwidth, int number);
	public ISegment getIndexSegmentFromNumber(
			Vector<IBaseUrl> baseurls, String representationID, int bandwidth, int number);
	public ISegment getMediaSegmentFromTime(
			Vector<IBaseUrl> baseurls, String representationID, int bandwidth, int time);
	public ISegment getIndexSegmentFromTime(
			Vector<IBaseUrl> baseurls, String representationID, int bandwidth, int time);
	
	public int hashCode();
	public boolean equals(Object obj);
	
}
