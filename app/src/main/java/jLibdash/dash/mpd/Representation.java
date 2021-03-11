package jLibdash.dash.mpd;

import java.util.Vector;

import jLibdash.dash.helpers.VectorUtils;


public class Representation extends RepresentationBase implements IRepresentation {

	private Vector<IBaseUrl> baseURLs;
	private Vector<ISubRepresentation> subRepresentation;
	private ISegmentBase segmentBase;
	private ISegmentList segmentList;
	private ISegmentTemplate segmentTemplate;
	private String id;
	private int bandwidth;
	private int qualityRanking;
	private Vector<String> dependencyId;
	private Vector<String> mediaStreamStructureId;
	
	public Representation() {
		super();
		this.baseURLs = new Vector<IBaseUrl>();
		this.subRepresentation = new Vector<ISubRepresentation>();
		this.dependencyId = new Vector<String>();
		this.mediaStreamStructureId = new Vector<String>();
		this.bandwidth = 0;
		this.qualityRanking = 0;
	}

	public Vector<IBaseUrl> getBaseURLs() {
		return this.baseURLs;
	}
	
	public void addBaseUrl(IBaseUrl baseUrl) {
		this.baseURLs.add(baseUrl);
	}

	public Vector<ISubRepresentation> getSubRepresentations() {
		return this.subRepresentation;
	}
	
	public void addSubRepresentation(ISubRepresentation subRepresentation) {
		this.subRepresentation.add(subRepresentation);
	}

	public ISegmentBase getSegmentBase() {
		return this.segmentBase;
	}
	
	public void setSegmentBase(SegmentBase segmentBase) {
		this.segmentBase = segmentBase;
	}

	public ISegmentList getSegmentList() {
		return this.segmentList;
	}
	
	public void setSegmentList(SegmentList segmentList) {
		this.segmentList = segmentList;
	}

	public ISegmentTemplate getSegmentTemplate() {
		return this.segmentTemplate;
	}
	
	public void setSegmentTemplate(SegmentTemplate segmentTemplate) {
		this.segmentTemplate = segmentTemplate;
	}

	public String getId() {
		return this.id;
	}
	
	public void setId(String id) {
		this.id = id;
	}

	public int getBandwidth() {
		return this.bandwidth;
	}
	
	public void setBandwidth(int bandwidth) {
		this.bandwidth = bandwidth;
	}

	public int getQualityRanking() {
		return this.qualityRanking;
	}
	
	public void setQualityRanking(int qualityRanking) {
		this.qualityRanking = qualityRanking;
	}

	public Vector<String> getDependencyId() {
		return this.dependencyId;
	}
	
	public void setDependencyId(String dependencyId) {
		this.dependencyId = VectorUtils.toVector(dependencyId.split(" "));
	}

	public Vector<String> getMediaStreamStructureId() {
		return this.mediaStreamStructureId;
	}
	
	public void setMediaStreamStructureId(String mediaStreamStructureId) {
		this.mediaStreamStructureId = VectorUtils.toVector(mediaStreamStructureId.split(" "));
	}
	
	@Override
	public String toString() {
		return this.id + " " + this.bandwidth + " bps " + this.getWidth() + "x" + this.getHeight();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + bandwidth;
		result = prime * result + ((baseURLs == null) ? 0 : baseURLs.hashCode());
		result = prime * result + ((dependencyId == null) ? 0 : dependencyId.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((mediaStreamStructureId == null) ? 0 : mediaStreamStructureId.hashCode());
		result = prime * result + qualityRanking;
		result = prime * result + ((segmentBase == null) ? 0 : segmentBase.hashCode());
		result = prime * result + ((segmentList == null) ? 0 : segmentList.hashCode());
		result = prime * result + ((segmentTemplate == null) ? 0 : segmentTemplate.hashCode());
		result = prime * result + ((subRepresentation == null) ? 0 : subRepresentation.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Representation other = (Representation) obj;
		if (bandwidth != other.bandwidth)
			return false;
		if (baseURLs == null) {
			if (other.baseURLs != null)
				return false;
		} else if (!baseURLs.equals(other.baseURLs))
			return false;
		if (dependencyId == null) {
			if (other.dependencyId != null)
				return false;
		} else if (!dependencyId.equals(other.dependencyId))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (mediaStreamStructureId == null) {
			if (other.mediaStreamStructureId != null)
				return false;
		} else if (!mediaStreamStructureId.equals(other.mediaStreamStructureId))
			return false;
		if (qualityRanking != other.qualityRanking)
			return false;
		if (segmentBase == null) {
			if (other.segmentBase != null)
				return false;
		} else if (!segmentBase.equals(other.segmentBase))
			return false;
		if (segmentList == null) {
			if (other.segmentList != null)
				return false;
		} else if (!segmentList.equals(other.segmentList))
			return false;
		if (segmentTemplate == null) {
			if (other.segmentTemplate != null)
				return false;
		} else if (!segmentTemplate.equals(other.segmentTemplate))
			return false;
		if (subRepresentation == null) {
			if (other.subRepresentation != null)
				return false;
		} else if (!subRepresentation.equals(other.subRepresentation))
			return false;
		return true;
	}

}
