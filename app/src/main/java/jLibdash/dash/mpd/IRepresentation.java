package jLibdash.dash.mpd;

import java.util.Vector;

public interface IRepresentation extends IRepresentationBase {
	
	public Vector<IBaseUrl> getBaseURLs();
	public Vector<ISubRepresentation> getSubRepresentations();
	public ISegmentBase getSegmentBase();
	public ISegmentList getSegmentList();
	public ISegmentTemplate getSegmentTemplate();
	public String getId();
	public int getBandwidth();
	public int getQualityRanking();
	public Vector<String> getDependencyId();
	public Vector<String> getMediaStreamStructureId();
	
	@Override
	public String toString();
	public int hashCode();
	public boolean equals(Object obj);

}
