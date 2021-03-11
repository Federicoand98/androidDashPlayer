package jLibdash.dash.mpd;

public interface ISegmentBase extends IMPDElement {
	
	public IURLType getInitialization();
	public IURLType getRepresentationIndex();
	public int getTimescale();
	public int getPresentationTimeOffset();
	public String getIndexRange();
	public boolean hasIndexRangeExact();
	
	public int hashCode();
	public boolean equals(Object obj);

}
