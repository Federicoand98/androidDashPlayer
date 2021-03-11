package jLibdash.dash.mpd;

import java.util.Optional;
import java.util.Vector;

import jLibdash.dash.helpers.ObjectRange;
import jLibdash.dash.helpers.ObjectUrl;
import jLibdash.dash.helpers.PathUtils;
import jLibdash.dash.metrics.HTTPTransactionType;
import jLibdash.dash.network.AbstractChunk;


public class Segment extends AbstractChunk implements ISegment {
	
	private String absoluteUri;
	private String host;
	private int port;
	private String path;
	private String range;
	private long startByte;
	private long endByte;
	private boolean hasByteRange;
	private HTTPTransactionType type;
	
	public Segment() {
		super();
		this.port = 0;
		this.startByte = 0;
		this.endByte = 0;
		this.hasByteRange = false;
	}
	
	public boolean init(Vector<IBaseUrl> baseurls, String uri, String range, HTTPTransactionType type) {
		
		this.absoluteUri = "";
		
		for(int i=0; i < baseurls.size(); i++)
			this.absoluteUri = PathUtils.combinePath(this.absoluteUri, baseurls.get(i).getUrl());
		
		this.absoluteUri = PathUtils.combinePath(this.absoluteUri, uri);
		Optional<ObjectUrl> objUrl = PathUtils.getHostPortAndPath(this.absoluteUri);
		
		if(!uri.equals("") && objUrl.isPresent() ) {
			ObjectUrl objUrlPresent = objUrl.get();
			this.host = objUrlPresent.getHost();
			this.port = objUrlPresent.getPort();
			this.path = objUrlPresent.getPath();
			
			Optional<ObjectRange> objRange = PathUtils.getStartAndEndBytes(range);
			if(!range.equals("") && objRange.isPresent()) {
				ObjectRange objRangePresent = objRange.get();
				this.range = range;
				this.hasByteRange = true;
				this.startByte = objRangePresent.getStartByte();
				this.endByte = objRangePresent.getEndByte();
			}
			
			this.type = type;
			return true;
		}
		
		return false;
	}
	
	public String getAbsoluteURI() {
		return this.absoluteUri;
	}
	
	public String getHost() {
		return this.host;
	}
	
	public int getPort() {
		return this.port;
	}
	
	public String getPath() {
		return this.path;
	}
	
	public String getRange() {
		return this.range;
	}
	
	public long getStartByte() {
		return this.startByte;
	}
	
	public long getEndByte() {
		return this.endByte;
	}

	public void setAbsoluteURI(String uri) {
		this.absoluteUri = uri;
	}

	public void setHost(String host) {
		this.host = host;

	}

	public void setPort(int port) {
		this.port = port;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public void setRange(String range) {
		this.range = range;
	}
	
	public void setStartByte(long startByte) {
		this.startByte = startByte;
	}

	public void setEndByte(long endByte) {
		this.endByte = endByte;
	}
	
	public HTTPTransactionType getType() {
		return this.type;
	}

	public boolean setHasByteRange(boolean hasByteRange) {
		return this.hasByteRange;
	}
	
	public boolean hasByteRange() {
		return this.hasByteRange;
	}
}
