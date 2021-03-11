package jLibdash.dash.mpd;

import java.util.Vector;

import jLibdash.dash.metrics.HTTPTransactionType;


public class SegmentURL extends AbstractMPDElement implements ISegmentURL {

	private String mediaURI;
	private String mediaRange;
	private String indexURI;
	private String indexRange;
	
	public SegmentURL() {
		super();
	}

	public String getMediaURI() {
		return this.mediaURI;
	}
	
	public void setMediaURI(String mediaURI) {
		this.mediaURI = mediaURI;
	}

	public String getMediaRange() {
		return this.mediaRange;
	}
	
	public void setMediaRange(String mediaRange) {
		this.mediaRange = mediaRange;
	}

	public String getIndexURI() {
		return this.indexURI;
	}

	public void setIndexURI(String indexURI) {
		this.indexURI = indexURI;
	}
	
	public String getIndexRange() {
		return this.indexRange;
	}
	
	public void setIndexRange(String indexRange) {
		this.indexRange = indexRange;
	}

	public ISegment toMediaSegment(Vector<IBaseUrl> baseurls) {
		Segment seg = new Segment();
		
		if(seg.init(baseurls, this.mediaURI, this.mediaRange, HTTPTransactionType.MediaSegment))
			return seg;
		
		return null;
	}

	public ISegment toIndexSegment(Vector<IBaseUrl> baseurls) {
		Segment seg = new Segment();
		if(seg.init(baseurls, this.indexURI, this.indexRange, HTTPTransactionType.IndexSegment))
			return seg;
		
		return null;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((indexRange == null) ? 0 : indexRange.hashCode());
		result = prime * result + ((indexURI == null) ? 0 : indexURI.hashCode());
		result = prime * result + ((mediaRange == null) ? 0 : mediaRange.hashCode());
		result = prime * result + ((mediaURI == null) ? 0 : mediaURI.hashCode());
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
		SegmentURL other = (SegmentURL) obj;
		if (indexRange == null) {
			if (other.indexRange != null)
				return false;
		} else if (!indexRange.equals(other.indexRange))
			return false;
		if (indexURI == null) {
			if (other.indexURI != null)
				return false;
		} else if (!indexURI.equals(other.indexURI))
			return false;
		if (mediaRange == null) {
			if (other.mediaRange != null)
				return false;
		} else if (!mediaRange.equals(other.mediaRange))
			return false;
		if (mediaURI == null) {
			if (other.mediaURI != null)
				return false;
		} else if (!mediaURI.equals(other.mediaURI))
			return false;
		return true;
	}
}
