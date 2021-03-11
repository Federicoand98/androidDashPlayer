package jLibdash.dash.mpd;

import java.util.Vector;

import jLibdash.dash.metrics.HTTPTransactionType;

public class URLType extends AbstractMPDElement implements IURLType {

	private String sourceURL;
	private String range;
	private HTTPTransactionType type;
	
	public URLType() {
		super();
	}
	
	public String getSourceURL() {
		return this.sourceURL;
	}
	
	public void setSourceURL(String sourceURL) {
		this.sourceURL = sourceURL;
	}

	public String getRange() {
		return this.range;
	}
	
	public void setRange(String range) {
		this.range = range;
	}
	
	public void setType(HTTPTransactionType type) {
		this.type = type;
	}
	
	public HTTPTransactionType getType() {
		return this.type;
	}

	public ISegment toSegment(Vector<IBaseUrl> baseurls) {
		Segment seg = new Segment();
		
		if(seg.init(baseurls, this.sourceURL, this.range, this.type))
			return seg;
		
		return null;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((range == null) ? 0 : range.hashCode());
		result = prime * result + ((sourceURL == null) ? 0 : sourceURL.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
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
		URLType other = (URLType) obj;
		if (range == null) {
			if (other.range != null)
				return false;
		} else if (!range.equals(other.range))
			return false;
		if (sourceURL == null) {
			if (other.sourceURL != null)
				return false;
		} else if (!sourceURL.equals(other.sourceURL))
			return false;
		if (type != other.type)
			return false;
		return true;
	}

}
