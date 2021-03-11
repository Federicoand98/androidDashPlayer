package jLibdash.dash.metrics;

import java.util.Vector;

public interface IHTTPTransaction {
	
	public Integer getTCPId();
	public HTTPTransactionType getType();
	public String getOriginalUrl();
	public String getActualUrl();
	public String getRange();
	public String getRequestSentTime();
	public String getResponseReceivedTime();
	public int getResponseCode();
	public Long getInterval();
	public Vector<ThroughputMeasurement> getThroughputTrace();
	public String getHTTPHeader();

}
