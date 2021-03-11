package jLibdash.dash.mpd;

public class SegmentBase extends AbstractMPDElement implements ISegmentBase {

	private IURLType initialization;
	private IURLType representationIndex;
	private int timescale;
	private int presentationTimeOffset;
	private String indexRange;
	private boolean indexRangeExact;
	
	public SegmentBase() {
		super();
		this.timescale = 1;
		this.presentationTimeOffset = 0;
		this.indexRangeExact = false;
	}
	
	public IURLType getInitialization() {
		return this.initialization;
	}
	
	public void setInitialization(URLType initialization) {
		this.initialization = initialization;
	}

	public IURLType getRepresentationIndex() {
		return this.representationIndex;
	}
	
	public void setRepresentationIndex(URLType representationIndex) {
		this.representationIndex = representationIndex;
	}

	public int getTimescale() {
		return this.timescale;
	}
	
	public void setTimescale(int timescale) {
		this.timescale = timescale;
	}

	public int getPresentationTimeOffset() {
		return this.presentationTimeOffset;
	}
	
	public void setPresentationTimeOffset(int presentationTimeOffset) {
		this.presentationTimeOffset = presentationTimeOffset;
	}

	public String getIndexRange() {
		return this.indexRange;
	}
	
	public void setIndexRange(String indexRange) {
		this.indexRange = indexRange;
	}

	public boolean hasIndexRangeExact() {
		return this.indexRangeExact;
	}
	
	public void setIndexRangeExact(boolean indexRangeExact) {
		this.indexRangeExact = indexRangeExact;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((indexRange == null) ? 0 : indexRange.hashCode());
		result = prime * result + (indexRangeExact ? 1231 : 1237);
		result = prime * result + ((initialization == null) ? 0 : initialization.hashCode());
		result = prime * result + presentationTimeOffset;
		result = prime * result + ((representationIndex == null) ? 0 : representationIndex.hashCode());
		result = prime * result + timescale;
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
		SegmentBase other = (SegmentBase) obj;
		if (indexRange == null) {
			if (other.indexRange != null)
				return false;
		} else if (!indexRange.equals(other.indexRange))
			return false;
		if (indexRangeExact != other.indexRangeExact)
			return false;
		if (initialization == null) {
			if (other.initialization != null)
				return false;
		} else if (!initialization.equals(other.initialization))
			return false;
		if (presentationTimeOffset != other.presentationTimeOffset)
			return false;
		if (representationIndex == null) {
			if (other.representationIndex != null)
				return false;
		} else if (!representationIndex.equals(other.representationIndex))
			return false;
		if (timescale != other.timescale)
			return false;
		return true;
	}

}
