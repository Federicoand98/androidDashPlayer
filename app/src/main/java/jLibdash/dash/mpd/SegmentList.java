package jLibdash.dash.mpd;

import java.util.Vector;

public class SegmentList extends MultipleSegmentBase implements ISegmentList {

	private Vector<ISegmentURL> segmentURLs;
	private String xlinkHref;
	private String xlinkActuate;
	
	public SegmentList() {
		super();
		segmentURLs = new Vector<ISegmentURL>();
		xlinkActuate = "onRequest";
	}
	
	public Vector<ISegmentURL> getSegmentURLs() {
		return this.segmentURLs;
	}
	
	public void addSegmentURL(SegmentURL segmentURL) {
		this.segmentURLs.add(segmentURL);
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((segmentURLs == null) ? 0 : segmentURLs.hashCode());
		result = prime * result + ((xlinkActuate == null) ? 0 : xlinkActuate.hashCode());
		result = prime * result + ((xlinkHref == null) ? 0 : xlinkHref.hashCode());
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
		SegmentList other = (SegmentList) obj;
		if (segmentURLs == null) {
			if (other.segmentURLs != null)
				return false;
		} else if (!segmentURLs.equals(other.segmentURLs))
			return false;
		if (xlinkActuate == null) {
			if (other.xlinkActuate != null)
				return false;
		} else if (!xlinkActuate.equals(other.xlinkActuate))
			return false;
		if (xlinkHref == null) {
			if (other.xlinkHref != null)
				return false;
		} else if (!xlinkHref.equals(other.xlinkHref))
			return false;
		return true;
	}
}
