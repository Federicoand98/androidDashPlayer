package jLibdash.dash.metrics;

public interface ITCPConnection {
	
	public Integer getTCPId();
	public String getDestinationAddress();
	public String getConnectionOpenedTime();
	public String getConnectionClosedTime();
	public Long getConnectionTime();

}
