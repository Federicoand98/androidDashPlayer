package jLibdash.dash.network;

import java.util.Vector;

import jLibdash.dash.helpers.SyncedBlockStream;
import jLibdash.dash.metrics.HTTPTransactionType;
import jLibdash.dash.metrics.IHTTPTransaction;
import jLibdash.dash.metrics.ITCPConnection;

public abstract class AbstractChunk implements IDownloadableChunk {

	private Vector<IDownloadObserver> observers;
	private DownloadThread dlThread;
	private IConnection connection;
	private SyncedBlockStream blockStream;
	private long bytesDownload;
	private DownloadStateManager stateManager;
	
	private Vector<ITCPConnection> tcpConnections;
	private Vector<IHTTPTransaction> httpTransaction;

	public abstract String getAbsoluteURI();
	public abstract String getHost();
	public abstract int getPort();
	public abstract String getPath();
	public abstract String getRange();
	public abstract long getStartByte();
	public abstract long getEndByte();
	public abstract boolean hasByteRange();
	public abstract HTTPTransactionType getType();
	
	public AbstractChunk() {
		this.bytesDownload = 0;
		this.tcpConnections = new Vector<ITCPConnection>();
		this.httpTransaction = new Vector<IHTTPTransaction>();
	}
	
	public IConnection getConnection() {
		return this.connection;
	}
	
	public DownloadStateManager getDownloadStateManager() {
		return this.stateManager;
	}
	
	public SyncedBlockStream getSyncedBlockStream() {
		return this.blockStream;
	}
	
	public void addByteDownloaded(int increment) {
		this.bytesDownload += increment;
	}
	
	public void abortDownload() {
		this.stateManager.checkAndSet(DownloadState.IN_PROGRESS, DownloadState.REQUEST_ABORT);
		this.stateManager.checkAndWait(DownloadState.REQUEST_ABORT, DownloadState.ABORTED);
	}
	
	public boolean startDownload() {
		
		if(!this.stateManager.getState().equals(DownloadState.NOT_STARTED))
			return false;
		
		this.dlThread = new DownloadThread(DownloadThreadType.InternalDownload, this);
		this.dlThread.start();
		
		this.stateManager.setState(DownloadState.IN_PROGRESS);
		return true;

	}
	
	public boolean startDownload(IConnection connection) {
		
		if(this.stateManager.getState().equals(DownloadState.NOT_STARTED))
			return false;
		
		this.connection = connection;
		this.dlThread = new DownloadThread(DownloadThreadType.ExternalDownload, this);
		this.dlThread.start();
		
		this.stateManager.setState(DownloadState.IN_PROGRESS);
		return true;
	}
	
	public int read(byte[] data, int len) {
		int toReturn = -1;
		try {
			toReturn = this.blockStream.getBytes(data, len);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return toReturn;
	}
	
	public int peek(byte[] data, int len) {
		int toReturn = -1;
		try {
			toReturn = this.blockStream.peekBytes(data, len);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return toReturn;
	}
	
	public int peek(byte[] data, int len, int offset) {
		int toReturn = -1;
		try {
			toReturn = this.blockStream.peekBytes(data, len, offset);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return toReturn;
	}
	
	public void attachDownloadObserver(IDownloadObserver observer) {
		this.observers.add(observer);
		this.stateManager.attach(observer);
	}
	
	public void detachDownloadObserver(IDownloadObserver observer) {
		
		this.observers.remove(observer);
		this.stateManager.detach(observer);
	}
	
	public void notifyDownloadRateChanged() {
		for(int i = 0; i < this.observers.size(); i++)
			this.observers.get(i).onDownloadRateChanged(this.bytesDownload);
	}

	public Vector<ITCPConnection> getTCPConnectionList() {
		return this.tcpConnections;
	}

	public Vector<IHTTPTransaction> getHTTPTransactionList() {
		return this.httpTransaction;
	}
}
