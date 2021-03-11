package jLibdash.dash.mpd;

import java.util.Vector;

import jLibdash.dash.helpers.VectorUtils;
import jLibdash.dash.metrics.IHTTPTransaction;
import jLibdash.dash.metrics.ITCPConnection;


public class MPD extends AbstractMPDElement implements IMPD {

	private Vector<IProgramInformation> programInformations;
	private Vector<IBaseUrl> baseUrls;
	private Vector<String> locations;
	private Vector<IPeriod> periods;
	private Vector<IMetrics> metrics;
	private String id;
	private Vector<String> profiles;
	private String type;
	private String availabilityStarttime;
	private String availabilityEndtime;
	private String mediaPresentationDuration;
	private String minimumUpdatePeriod;
	private String minBufferTime;
	private String timeShiftBufferDepth;
	private String suggestedPresentationDelay;
	private String maxSegmentDuration;
	private String maxSubsegmentDuration;
	private BaseUrl mpdPathBaseUrl;
	private int fetchTime;
	
	private Vector<ITCPConnection> tcpConnections;
	private Vector<IHTTPTransaction> httpTransactions;
	
	
	public MPD() {
		super();
		this.programInformations = new Vector<IProgramInformation>();
		this.baseUrls = new Vector<IBaseUrl>();
		this.locations = new Vector<String>();
		this.periods = new Vector<IPeriod>();
		this.metrics = new Vector<IMetrics>();
		
		this.type = "static";
		
		this.tcpConnections = new Vector<ITCPConnection>();
		this.httpTransactions = new Vector<IHTTPTransaction>();
	}


	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public Vector<String> getProfiles() {
		return profiles;
	}


	public void setProfiles(String profiles) {
		this.profiles = VectorUtils.toVector(profiles.split(","));
	}


	public String getType() {
		return type;
	}


	public void setType(String type) {
		this.type = type;
	}


	public String getAvailabilityStarttime() {
		return availabilityStarttime;
	}


	public void setAvailabilityStarttime(String availabilityStarttime) {
		this.availabilityStarttime = availabilityStarttime;
	}


	public String getAvailabilityEndtime() {
		return availabilityEndtime;
	}


	public void setAvailabilityEndtime(String availabilityEndtime) {
		this.availabilityEndtime = availabilityEndtime;
	}


	public String getMediaPresentationDuration() {
		return mediaPresentationDuration;
	}


	public void setMediaPresentationDuration(String mediaPresentationDuration) {
		this.mediaPresentationDuration = mediaPresentationDuration;
	}


	public String getMinimumUpdatePeriod() {
		return minimumUpdatePeriod;
	}


	public void setMinimumUpdatePeriod(String minimumUpdatePeriod) {
		this.minimumUpdatePeriod = minimumUpdatePeriod;
	}


	public String getMinBufferTime() {
		return minBufferTime;
	}


	public void setMinBufferTime(String minBufferTime) {
		this.minBufferTime = minBufferTime;
	}


	public String getTimeShiftBufferDepth() {
		return timeShiftBufferDepth;
	}


	public void setTimeShiftBufferDepth(String timeShiftBufferDepth) {
		this.timeShiftBufferDepth = timeShiftBufferDepth;
	}


	public String getSuggestedPresentationDelay() {
		return suggestedPresentationDelay;
	}


	public void setSuggestedPresentationDelay(String suggestedPresentationDelay) {
		this.suggestedPresentationDelay = suggestedPresentationDelay;
	}


	public String getMaxSegmentDuration() {
		return maxSegmentDuration;
	}


	public void setMaxSegmentDuration(String maxSegmentDuration) {
		this.maxSegmentDuration = maxSegmentDuration;
	}


	public String getMaxSubsegmentDuration() {
		return maxSubsegmentDuration;
	}


	public void setMaxSubsegmentDuration(String maxSubsegmentDuration) {
		this.maxSubsegmentDuration = maxSubsegmentDuration;
	}


	public BaseUrl getMPDPathBaseUrl() {
		return mpdPathBaseUrl;
	}


	public void setMpdPathBaseUrl(BaseUrl mpdPathBaseUrl) {
		this.mpdPathBaseUrl = mpdPathBaseUrl;
	}


	public int getFetchTime() {
		return fetchTime;
	}


	public void setFetchTime(int fetchTime) {
		this.fetchTime = fetchTime;
	}


	public Vector<IProgramInformation> getProgramInformations() {
		return programInformations;
	}


	public Vector<IBaseUrl> getBaseURLs() {
		return baseUrls;
	}


	public Vector<String> getLocations() {
		return locations;
	}


	public Vector<IPeriod> getPeriods() {
		return periods;
	}


	public Vector<IMetrics> getMetrics() {
		return metrics;
	}


	public Vector<ITCPConnection> getTCPConnectionList() {
		return tcpConnections;
	}


	public Vector<IHTTPTransaction> getHTTPTransactionList() {
		return httpTransactions;
	}
	
	public void addProgramInformation(IProgramInformation programInformation) {
		this.programInformations.add(programInformation);
	}
	
	public void addBaseUrl(IBaseUrl baseUrl) {
		this.baseUrls.add(baseUrl);
	}
	
	public void addLocation(String location) {
		this.locations.add(location);
	}
	
	public void addPeriod(IPeriod period) {
		this.periods.add(period);
	}
	
	public void addMetrics(IMetrics metrics) {
		this.metrics.add(metrics);
	}
	
	public void addTCPConnection(ITCPConnection tcpConnection) {
		this.tcpConnections.add(tcpConnection);
	}
	
	public void addHTTPTransaction(IHTTPTransaction transaction) {
		this.httpTransactions.add(transaction);
	}
	
	
	
}