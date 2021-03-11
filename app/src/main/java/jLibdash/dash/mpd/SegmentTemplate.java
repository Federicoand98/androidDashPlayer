package jLibdash.dash.mpd;

import java.util.Vector;

import jLibdash.dash.metrics.HTTPTransactionType;


public class SegmentTemplate extends MultipleSegmentBase implements ISegmentTemplate {

	private String media;
	private String index;
	private String initialization;
	private String bitstreamSwitching;
	
	public SegmentTemplate() {
		super();
	}

	public String getMedia() {
		return this.media;
	}
	
	public void setMedia(String media) {
		this.media = media;
	}

	public String getIndex() {
		return this.index;
	}
	
	public void setIndex(String index) {
		this.index = index;
	}

	public String getSegmentTemplateInitialization() {
		return this.initialization;
	}
	
	public void setSegmentTemplateInitialization(String initialization) {
		this.initialization = initialization;
	}

	public String getSegmentTemplateBitstreamSwitching() {
		return this.bitstreamSwitching;
	}
	
	public void setSegmentTemplateBitstreamSwitching(String bitstreamSwitching) {
		this.bitstreamSwitching = bitstreamSwitching;
	}

	public ISegment toInitializationSegment(Vector<IBaseUrl> baseurls, String representationID,
			int bandwidth) {
		return toSegment(this.initialization, baseurls, representationID, bandwidth,
				HTTPTransactionType.InitializationSegment, 0, 0);
	}

	public ISegment toBitstreamSwitchingSegment(Vector<IBaseUrl> baseurls, String representationID,
			int bandwidth) {
		return toSegment(this.bitstreamSwitching, baseurls, representationID, bandwidth,
				HTTPTransactionType.BitstreamSwitchingSegment, 0, 0);
	}

	public ISegment getMediaSegmentFromNumber(Vector<IBaseUrl> baseurls, String representationID,
			int bandwidth, int number) {
		return toSegment(this.media, baseurls, representationID, bandwidth,
				HTTPTransactionType.MediaSegment, number, 0);
	}

	public ISegment getIndexSegmentFromNumber(Vector<IBaseUrl> baseurls, String representationID,
			int bandwidth, int number) {
		return toSegment(this.index, baseurls, representationID, bandwidth,
				HTTPTransactionType.IndexSegment, number, 0);
	}

	public ISegment getMediaSegmentFromTime(Vector<IBaseUrl> baseurls, String representationID,
			int bandwidth, int time) {
		return toSegment(this.media, baseurls, representationID, bandwidth,
				HTTPTransactionType.MediaSegment, 0, time);
	}

	public ISegment getIndexSegmentFromTime(Vector<IBaseUrl> baseurls, String representationID,
			int bandwidth, int time) {
		return toSegment(this.index, baseurls, representationID, bandwidth,
				HTTPTransactionType.IndexSegment, 0, time);
	}
	
	private String replaceParameters(String uri, String representationID, int bandwidth, int number, int time) {
		
		String replacedUri = "";
		String[] chunks = uri.split("\\$");
		
		if(chunks.length > 1) {
			
			for(int i=0; i<chunks.length; i++) {
				if(chunks[i].equals("RepresentationID")) {
					chunks[i] = representationID;
					continue;
				}
				
				if(chunks[i].indexOf("Bandwidth") == 0) {
					chunks[i] = formatChunk(chunks[i], bandwidth);
					continue;
				}
				
				if(chunks[i].indexOf("Number") == 0) {
					chunks[i] = formatChunk(chunks[i], number);
				}
				
				if(chunks[i].indexOf("Time") == 0) {
					chunks[i] = formatChunk(chunks[i], time);
					continue;
				}
			}
			
			for(String s : chunks)
				replacedUri += s;
			
			return replacedUri;
			
		} else {
			replacedUri = uri;
			return replacedUri;
		}
	}
	
	private String formatChunk(String uri, int number) {
		int pos = 0;
		String formatTag = "%01d";
		
		if( (pos = uri.indexOf("%0")) != -1)
			formatTag = uri.substring(pos).concat("d");
		
		return String.format(formatTag, number);
		
	}
	
	private ISegment toSegment(String uri, Vector<IBaseUrl> baseurls, String representationID,
			int bandwidth, HTTPTransactionType type, int number, int time) {
		
		Segment seg = new Segment();
		if(seg.init(baseurls, replaceParameters(uri, representationID, bandwidth, number, time), "", type))
			return seg;
		
		return null;
		
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((bitstreamSwitching == null) ? 0 : bitstreamSwitching.hashCode());
		result = prime * result + ((index == null) ? 0 : index.hashCode());
		result = prime * result + ((initialization == null) ? 0 : initialization.hashCode());
		result = prime * result + ((media == null) ? 0 : media.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		SegmentTemplate other = (SegmentTemplate) obj;
		if (bitstreamSwitching == null) {
			if (other.bitstreamSwitching != null)
				return false;
		} else if (!bitstreamSwitching.equals(other.bitstreamSwitching))
			return false;
		if (index == null) {
			if (other.index != null)
				return false;
		} else if (!index.equals(other.index))
			return false;
		if (initialization == null) {
			if (other.initialization != null)
				return false;
		} else if (!initialization.equals(other.initialization))
			return false;
		if (media == null) {
			if (other.media != null)
				return false;
		} else if (!media.equals(other.media))
			return false;
		return true;
	}

}
