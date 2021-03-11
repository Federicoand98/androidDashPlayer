package jLibdash.dash.metrics;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.util.Vector;

public class HTTPTransaction implements IHTTPTransaction {
	
	private Integer tcpId;
	private HTTPTransactionType type;
	private String url;
	private String actualUrl;
	private String range;
	private String tRequest;
	private String tResponse;
	private int responseCode;
	private Long interval;
	private Vector<ThroughputMeasurement> trace;
	private String httpHeader;
	
	@RequiresApi(api = Build.VERSION_CODES.O)
	public HTTPTransaction() {
		tcpId = Integer.parseUnsignedInt("0");
		type = HTTPTransactionType.Other;
		responseCode = 0;
		interval = Long.parseUnsignedLong("0");
		url = "";
		actualUrl = "";
		range = "";
		tRequest = "";
		tResponse = "";
		httpHeader = "";
		trace = new Vector<ThroughputMeasurement>();
		
	}
	
	public HTTPTransaction(IHTTPTransaction obj) {
		this.tcpId = obj.getTCPId();
		this.type = obj.getType();
		this.url = obj.getOriginalUrl();
		this.actualUrl = obj.getActualUrl();
		this.range = obj.getRange();
		this.tRequest = obj.getRequestSentTime();
		this.tResponse = obj.getResponseReceivedTime();
		this.responseCode = obj.getResponseCode();
		this.interval = obj.getInterval();
		this.trace = obj.getThroughputTrace();
		this.httpHeader = obj.getHTTPHeader();
	}

	public Integer getTCPId() {
		return this.tcpId;
	}

	public HTTPTransactionType getType() {
		return this.type;
	}

	public String getOriginalUrl() {
		return this.url;
	}

	public String getActualUrl() {
		return this.actualUrl;
	}

	public String getRange() {
		return this.range;
	}

	public String getRequestSentTime() {
		return this.tRequest;
	}

	public String getResponseReceivedTime() {
		return this.tResponse;
	}

	public int getResponseCode() {
		return this.responseCode;
	}

	public Long getInterval() {
		return this.interval;
	}

	public Vector<ThroughputMeasurement> getThroughputTrace() {
		return this.trace;
	}

	public String getHTTPHeader() {
		return this.httpHeader;
	}
	
	public void setTCPId (Integer tcpId) {
		this.tcpId = tcpId;
		
	}
	
     public void setType(HTTPTransactionType type) {
    	 this.type = type;
     }
     
     public void setOriginalUrl(String origUrl) {
    	this.url = origUrl;
     }
     
     public void setActualUrl(String actUrl) {
    	 this.actualUrl = actUrl;
     }
     
     public void setRange(String range) {
    	 this.range = range;
     }
     
    public void setRequestSentTime(String tRequest) {
    	this.tRequest = tRequest;
    }
    
    public void setResponseReceivedTime(String tResponse) {
    	this.tResponse = tResponse;
    }
    
    public void setResponseCode(int respCode) {
    	this.responseCode = respCode;
    }
 
    public void setInterval(Long interval) {
    	this.interval = interval;
    }
    
    public void addThroughputMeasurement(ThroughputMeasurement throuputEntry) {
    	trace.add(throuputEntry);
    }
    
    public void addHTTPHeaderLine(String headerLine) {
    	httpHeader += headerLine;
    }


}
