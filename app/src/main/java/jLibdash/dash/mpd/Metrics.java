package jLibdash.dash.mpd;

import java.util.Vector;

public class Metrics extends AbstractMPDElement implements IMetrics {

	private Vector<IDescriptor> reportings;
	private Vector<IRange> ranges;
	private String metrics;
	
	public Metrics() {
		super();
		reportings = new Vector<IDescriptor>();
		ranges = new Vector<IRange>();
	}
	
	public Vector<IDescriptor> getReportings() {
		return this.reportings;
	}
	
	public void addReporting(IDescriptor reporting) {
		this.reportings.add(reporting);
	}

	public Vector<IRange> getRanges() {
		return this.ranges;
	}
	
	public void addRange(IRange range) {
		this.ranges.add(range);
	}

	public String getMetrics() {
		return this.metrics;
	}
	
	public void setMetrics(String metrics) {
		this.metrics = metrics;
	}

}
