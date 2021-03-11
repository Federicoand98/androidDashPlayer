package jLibdash.dash.network;


import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

import jLibdash.dash.helpers.Block;


public class DownloadThread extends Thread{
	
	private final int BLOCKSIZE = 32768;
	
	private AbstractChunk chunk;
	private DownloadThreadType type;
	
	public DownloadThread(DownloadThreadType type, AbstractChunk chunk) {
		this.type = type;
		this.chunk = chunk;
	}
	
	
	public void run() {
		
		if(type == DownloadThreadType.InternalDownload)
			this.downloadInternalConnection();
		
		if(type == DownloadThreadType.ExternalDownload)
			this.downloadExternalConnection();
		
	}
	
	private void downloadExternalConnection() {
				
		byte[] blockData = new byte[BLOCKSIZE];
		byte[] streamBlockData;
		int ret = 0;
		
		do {
			try {
				ret = this.chunk.getConnection().read(blockData, blockData.length, chunk);
			} catch (IOException e) {
				chunk.getDownloadStateManager().setState(DownloadState.REQUEST_ABORT);
				e.printStackTrace();
			}
			if(ret > 0) {
				streamBlockData = new byte[ret];
				for(int i=0; i<ret; i++)
					streamBlockData[i] = blockData[i];
				try {
					chunk.getDownloadStateManager().setState(DownloadState.ABORTED);
					chunk.getSyncedBlockStream().pushBack(new Block(streamBlockData));
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				chunk.addByteDownloaded(ret);
				chunk.notifyDownloadRateChanged();
			}
			if(chunk.getDownloadStateManager().getState() == DownloadState.REQUEST_ABORT)
				ret = 0;
		} while (ret > 0);
		
		if(chunk.getDownloadStateManager().getState() == DownloadState.REQUEST_ABORT)
			chunk.getDownloadStateManager().setState(DownloadState.ABORTED);
		else
			chunk.getDownloadStateManager().setState(DownloadState.COMPLETED);
		
		try {
			chunk.getSyncedBlockStream().setEOS(true);
		} catch (InterruptedException e) {
			System.err.println("Unable to set SyncedBlockStreamEOS");
			e.printStackTrace();
		}		
	}
	
	private void downloadInternalConnection() {
		URLConnection connection = null;
		InputStream inputStream = null;
		try {
			connection = (new URL(this.chunk.getAbsoluteURI())).openConnection();
			inputStream = connection.getInputStream();
		} catch (IOException e) {
			chunk.getDownloadStateManager().setState(DownloadState.ABORTED);
			e.printStackTrace();
		}		
		
		byte[] receivedData;
		
		if(chunk.hasByteRange() && Integer.parseInt(chunk.getRange()) < BLOCKSIZE)
			receivedData = new byte[Integer.parseInt(chunk.getRange())];
		else
			receivedData = new byte[BLOCKSIZE];
		
		byte[] streamBlockData;
		int received = 0;
		
		do {
			try {
				received = inputStream.read(receivedData);
			} catch (IOException e) {
				chunk.getDownloadStateManager().setState(DownloadState.ABORTED);
				e.printStackTrace();
			}
			if(received > 0) {
				streamBlockData = new byte[received];
				for(int i=0; i<received; i++)
					streamBlockData[i] = receivedData[i];
				try {
					chunk.getSyncedBlockStream().pushBack(new Block(streamBlockData));
				} catch (InterruptedException e) {
					chunk.getDownloadStateManager().setState(DownloadState.ABORTED);
					e.printStackTrace();
				}
				chunk.addByteDownloaded(received);
				chunk.notifyDownloadRateChanged();
			}
			if(chunk.getDownloadStateManager().getState() == DownloadState.REQUEST_ABORT)
				received = 0;
		} while(received > 0);
		
		if(chunk.getDownloadStateManager().getState() == DownloadState.REQUEST_ABORT)
			chunk.getDownloadStateManager().setState(DownloadState.ABORTED);
		else
			chunk.getDownloadStateManager().setState(DownloadState.COMPLETED);
		
		try {
			chunk.getSyncedBlockStream().setEOS(true);
		} catch (InterruptedException e) {
			System.err.println("Unable to set SyncedBlockStreamEOS");
			e.printStackTrace();
		}	
		
	}

}
