package jLibdash.dash.network;

import jLibdash.dash.metrics.IDASHMetrics;

public interface IDownloadableChunk extends IChunk, IDASHMetrics {
	
	public boolean startDownload();
	public boolean startDownload(IConnection connection);
	public void abortDownload();
	public int read(byte[] data, int len);
	public int peek(byte[] data, int len);
	public int peek(byte[] data, int len, int offset);
	public void attachDownloadObserver(IDownloadObserver observer);

}
