package jLibdash.dash.network;

import java.io.IOException;

import jLibdash.dash.metrics.IDASHMetrics;


public interface IConnection extends IDASHMetrics {
	public int read(byte[] data, int len, IChunk chunk) throws IOException;
	public int peek(byte[] data, int len, IChunk chunk) throws IOException;
	

}
