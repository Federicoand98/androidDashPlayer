package jLibdash.dash.mpd;

import java.util.Vector;

import jLibdash.dash.metrics.IDASHMetrics;


public interface IMPD extends IDASHMetrics, IMPDElement {
	
	public Vector<IProgramInformation> getProgramInformations();
	public Vector<IBaseUrl> getBaseURLs();
	public Vector<String> getLocations();
	public Vector<IPeriod> getPeriods();
	public Vector<IMetrics> getMetrics();
	public String getId();
	public Vector<String> getProfiles();
	public String getType();
	public String getAvailabilityStarttime();
	public String getAvailabilityEndtime();
	public String getMediaPresentationDuration();
	public String getMinimumUpdatePeriod();
	public String getMinBufferTime();
	public String getTimeShiftBufferDepth();
	public String getSuggestedPresentationDelay();
	public String getMaxSegmentDuration();
	public String getMaxSubsegmentDuration();
	public IBaseUrl getMPDPathBaseUrl();
	public int getFetchTime();

}
