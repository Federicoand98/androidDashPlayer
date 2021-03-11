package jLibdash.dash.metrics;

import java.util.Vector;

public class ThroughputMeasurement implements IThroughputMeasurement {

	private String startOfPeriod;
	private Long durationOfPeriod;
	private Vector<Integer> receivedBytesPerTrace;
	
	public ThroughputMeasurement() {
		this.receivedBytesPerTrace = new Vector<Integer>();
	}
	
	public Long getDurationOfPeriod() {
		return durationOfPeriod;
	}
	public void setDurationOfPeriod(Long durationOfPeriod) {
		this.durationOfPeriod = durationOfPeriod;
	}
	public Vector<Integer> getReceivedBytesPerTrace() {
		return receivedBytesPerTrace;
	}
	public void setReceivedBytesPerTrace(Vector<Integer> receivedBytesPerTrace) {
		this.receivedBytesPerTrace = receivedBytesPerTrace;
	}
	public String getStartOfPeriod() {
		return startOfPeriod;
	}
	
	public void addReceivedBytes(Integer numberOfBytes) {
		this.receivedBytesPerTrace.add(numberOfBytes);
	}
	
	
	
	

}
