package jLibdash.dash.metrics;

import java.util.Vector;

public interface IThroughputMeasurement {
	
	public String getStartOfPeriod();
	public Long getDurationOfPeriod();
	public Vector<Integer> getReceivedBytesPerTrace();

}
