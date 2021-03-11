package jLibdash.dash.mpd;


import jLibdash.dash.network.IDownloadableChunk;

public interface ISegment extends IDownloadableChunk {
	
	public void setAbsoluteURI  (String uri);
	public void setHost(String host);
	public void setPort(int port);
	public void setPath(String path);
	public void setRange(String range);
	public void setStartByte(long startByte);
	public void setEndByte(long endByte);
	public boolean setHasByteRange(boolean hasByteRange);

}
