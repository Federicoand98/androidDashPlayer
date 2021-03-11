package jLibdash.dash.mpd;

import java.util.Vector;

import jLibdash.dash.metrics.HTTPTransactionType;

public class BaseUrl extends AbstractMPDElement implements IBaseUrl {

	private String url;
	private String serviceLocation;
	private String byteRange;
	
	public BaseUrl() {
		super();
	}
	
	public String getUrl() {
		return this.url;
	}
	
	public void setUrl(String url) {
		this.url = url;
	}

	public String getServiceLocation() {
		return this.serviceLocation;
	}
	
	public void setServiceLocation(String serviceLocation) {
		this.serviceLocation = serviceLocation;
	}

	public String getByteRange() {
		return this.byteRange;
	}
	
	public void setByteRange(String byteRange) {
		this.byteRange = byteRange;
	}

	public ISegment toMediaSegment(Vector<IBaseUrl> baseurls) {
		Segment seg = new Segment();
		
		if(seg.init(baseurls, this.url, this.byteRange, HTTPTransactionType.MediaSegment))
			return seg;
		
		return null;
	}

}
