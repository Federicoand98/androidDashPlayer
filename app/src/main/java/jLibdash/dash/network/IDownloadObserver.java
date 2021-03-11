package jLibdash.dash.network;

public interface IDownloadObserver {
	
	public void onDownloadRateChanged(long bytesDownloaded);
	public void onDownloadStateChanged(DownloadState state);

}
