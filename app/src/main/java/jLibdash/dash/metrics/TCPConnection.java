package jLibdash.dash.metrics;

public class TCPConnection implements ITCPConnection {
	
	private Integer tcpId;
	private String dest;
	private String tOpen;
	private String tClose;
	private Long tConnect;
	
	public Integer getTCPId() {
		return tcpId;
	}
	
	public String getDestinationAddress() {
		return dest;
	}
	
	public String getConnectionOpenedTime() {
		return tOpen;
	}
	
	public String getConnectionClosedTime() {
		return tClose;
	}
	
	public Long getConnectionTime() {
		return tConnect;
	}

	public void setTCPId(Integer tcpId) {
		this.tcpId = tcpId;
	}

	public void setDestationAddress(String dest) {
		this.dest = dest;
	}

	public void setConnectionOpenedTime(String tOpen) {
		this.tOpen = tOpen;
	}

	public void setConnectionClosedTime(String tClose) {
		this.tClose = tClose;
	}

	public void setConnectionTime(Long tConnect) {
		this.tConnect = tConnect;
	}
	
	
	

}
