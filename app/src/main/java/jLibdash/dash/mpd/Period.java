package jLibdash.dash.mpd;

import java.util.Vector;

public class Period extends AbstractMPDElement implements IPeriod {

	private Vector<IBaseUrl> baseURLs;
	private ISegmentBase segmentBase;
	private ISegmentList segmentList;
	private ISegmentTemplate segmentTemplate;
	private Vector<IAdaptationSet> adaptationSets;
	private Vector<ISubset> subsets;
	private String xlinkHref;
	private String xlinkActuate;
	private String id;
	private String start;
	private String duration;
	private boolean isBitstreamSwitching;
	
	public Period() {
		this.baseURLs = new Vector<IBaseUrl>();
		this.adaptationSets = new Vector<IAdaptationSet>();
		this.subsets = new Vector<ISubset>();
		this.xlinkActuate = "onRequest";
		this.isBitstreamSwitching = false;
	}

	public Vector<IBaseUrl> getBaseURLs() {
		return this.baseURLs;
	}
	
	public void addBaseUrl(IBaseUrl baseUrl) {
		this.baseURLs.add(baseUrl);
	}

	public ISegmentBase getSegmentBase() {
		return this.segmentBase;
	}
	
	public void setSegmentBase(ISegmentBase segmentBase) {
		this.segmentBase = segmentBase;
	}

	public ISegmentList getSegmentList() {
		return this.segmentList;
	}
	
	public void setSegmentList(ISegmentList segmentList) {
		this.segmentList = segmentList;
	}

	public ISegmentTemplate getSegmentTemplate() {
		return this.segmentTemplate;
	}
	
	public void setSegmentTemplate(ISegmentTemplate segmentTemplate) {
		this.segmentTemplate = segmentTemplate;
	}

	public Vector<IAdaptationSet> getAdaptationSets() {
		return this.adaptationSets;
	}
	
	public void addAdaptationSet(IAdaptationSet adaptationSet) {
		this.adaptationSets.add(adaptationSet);
	}

	public Vector<ISubset> getSubsets() {
		return this.subsets;
	}
	
	public void addSubset(ISubset subset) {
		this.subsets.add(subset);
	}

	public String getXlinkHref() {
		return this.xlinkHref;
	}
	
	public void setXlinkHref(String xlinkHref) {
		this.xlinkHref = xlinkHref;
	}

	public String getXlinkActuate() {
		return this.xlinkActuate;
	}
	
	public void setXlinkActuate(String xlinkActuate) {
		this.xlinkActuate = xlinkActuate;
	}

	public String getId() {
		return this.id;
	}
	
	public void setId(String id) {
		this.id = id;
	}

	public String getStart() {
		return this.start;
	}
	
	public void setStart(String start) {
		this.start = start;
	}

	public String getDuration() {
		return this.duration;
	}
	
	public void setDuration(String duration) {
		this.duration = duration;
	}

	public boolean getBitstreamSwitching() {
		return this.isBitstreamSwitching;
	}
	
	public void setBitstreamSwitching(boolean isBitstreamSwitching) {
		this.isBitstreamSwitching = isBitstreamSwitching;
	}

}
