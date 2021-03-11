package jLibdash.dash.network;


import jLibdash.dash.metrics.HTTPTransactionType;

public interface IChunk {
	
	public String getAbsoluteURI();
	public String getHost();
	public int getPort();
	public String getPath();
	public String getRange();
	public long getStartByte();
	public long getEndByte();
	public boolean hasByteRange();
	public HTTPTransactionType getType();

}
