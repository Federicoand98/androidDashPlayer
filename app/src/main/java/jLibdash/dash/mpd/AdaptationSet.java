package jLibdash.dash.mpd;

import java.util.Vector;

public class AdaptationSet extends RepresentationBase implements IAdaptationSet {
	
	private Vector<IDescriptor> accessibility;
	private Vector<IDescriptor> role;
	private Vector<IDescriptor> rating;
	private Vector<IDescriptor> viewpoint;
	private Vector<IContentComponent> contentComponent;
	private Vector<IBaseUrl> baseURLs;
	private ISegmentBase segmentBase;
	private ISegmentList segmentList;
	private ISegmentTemplate segmentTemplate;
	private Vector<IRepresentation> representation;
	private String xlinkHref;
	private String xlinkActuate;
	private int id;
	private int group;
	private String lang;
	private String contentType;
	private String par;
	private int minBandwidth;
	private int maxBandwidth;
	private int minWidth;
	private int maxWidth;
	private int minHeight;
	private int maxHeight;
	private String minFramerate;
	private String maxFramerate;
	private boolean segmentAlignmentIsBool;
	private boolean subSegmentAlignmentIsBool;
	private boolean usesSegmentAlignment;
	private boolean usesSubsegmentAlignment;
	private int segmentAlignment;
	private int subsegmentAlignment;
	private byte subsegmentStartsWithSAP;
	private boolean isBitstreamSwitching;
	
	public AdaptationSet() {
		super();
		this.accessibility = new Vector<IDescriptor>();
		this.role = new Vector<IDescriptor>();
		this.rating = new Vector<IDescriptor>();
		this.viewpoint = new Vector<IDescriptor>();
		this.contentComponent = new Vector<IContentComponent>();
		this.baseURLs = new Vector<IBaseUrl>();
		this.representation = new Vector<IRepresentation>();
		this.xlinkActuate = "onRequest";
		this.id = 0;
		this.minBandwidth = 0;
		this.maxBandwidth = 0;
		this.minWidth = 0;
		this.maxWidth = 0;
		this.minHeight = 0;
		this.maxHeight = 0;
		this.segmentAlignmentIsBool = true;
		this.subSegmentAlignmentIsBool = true;
		this.usesSegmentAlignment = false;
		this.usesSubsegmentAlignment = false;
		this.segmentAlignment = 0;
		this.subsegmentAlignment = 0;
		this.isBitstreamSwitching = false;
	}

	public Vector<IDescriptor> getAccessibility() {
		return this.accessibility;
	}
	
	public void addAccessibility(IDescriptor accessibility) {
		this.accessibility.add(accessibility);
	}

	public Vector<IDescriptor> getRole() {
		return this.role;
	}
	
	public void addRole(IDescriptor role) {
		this.role.add(role);
	}

	public Vector<IDescriptor> getRating() {
		return this.rating;
	}
	
	public void addRating(IDescriptor rating) {
		this.rating.add(rating);
	}

	public Vector<IDescriptor> getViewpoint() {
		return this.viewpoint;
	}
	
	public void addViewpoint(IDescriptor viewPoint) {
		this.viewpoint.add(viewPoint);
	}

	public Vector<IContentComponent> getContentComponent() {
		return this.contentComponent;
	}
	
	public void addContentComponent(IContentComponent contentComponent) {
		this.contentComponent.add(contentComponent);
	}

	public Vector<IBaseUrl> getBaseURLs() {
		return this.baseURLs;
	}
	
	public void addBaseUrl(IBaseUrl baseUrl) {
		this.baseURLs.add(baseUrl);
	}

	public ISegmentBase getSegmentBase() {
		return this.segmentBase;
	}
	
	public void setSegmentBase(SegmentBase segmentBase) {
		this.segmentBase = segmentBase;
	}

	public ISegmentList getSegmentList() {
		return this.segmentList;
	}
	
	public void setSegmentList(SegmentList segmentList) {
		this.segmentList = segmentList;
	}

	public ISegmentTemplate getSegmentTemplate() {
		return this.segmentTemplate;
	}
	
	public void setSegmentTemplate(SegmentTemplate segmentTemplate) {
		this.segmentTemplate = segmentTemplate;
	}

	public Vector<IRepresentation> getRepresentation() {
		return this.representation;
	}
	
	public void addRepresentation(IRepresentation representation) {
		this.representation.add(representation);
	}

	public String getXlinkHref() {
		return this.xlinkHref;
	}
	
	public void setXlinkHref(String xlinkHref) {
		this.xlinkHref = xlinkHref;
	}

	public String getXlinkActuate() {
		return this.xlinkActuate;
	}
	
	public void setXlinkActuate(String xlinkActuate) {
		this.xlinkActuate = xlinkActuate;
	}

	public int getId() {
		return this.id;
	}
	
	public void setId(int id) {
		this.id = id;
	}

	public int getGroup() {
		return this.group;
	}
	
	public void setGroup(int group) {
		this.group = group;
	}

	public String getLang() {
		return this.lang;
	}
	
	public void setLang(String lang) {
		this.lang = lang;
	}

	public String getContentType() {
		return this.contentType;
	}
	
	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	public String getPar() {
		return this.par;
	}
	
	public void setPar(String par) {
		this.par = par;
	}

	public int getMinBandwidth() {
		return this.minBandwidth;
	}
	
	public void setMinBandwidth(int minBandwidth) {
		this.minBandwidth = minBandwidth; 
	}

	public int getMaxBandwidth() {
		return this.maxBandwidth;
	}
	
	public void setMaxBandwidth(int maxBandwidth) {
		this.maxBandwidth = maxBandwidth;
	}

	public int getMinWidth() {
		return this.minWidth;
	}
	
	public void setMinWidth(int minWidth) {
		this.minWidth = minWidth;
	}

	public int getMaxWidth() {
		return this.maxWidth;
	}
	
	public void setMaxWidth(int maxWidth) {
		this.maxWidth = maxWidth;
	}

	public int getMinHeight() {
		return this.minHeight;
	}
	
	public void setMinHeight(int minHeight) {
		this.minHeight = minHeight;
	}

	public int getMaxHeight() {
		return this.maxHeight;
	}
	
	public void setMaxHeight(int maxHeight) {
		this.maxHeight = maxHeight;
	}

	public String getMinFramerate() {
		return this.minFramerate;
	}
	
	public void setMinFramerate(String minFramerate) {
		this.minFramerate = minFramerate;
	}

	public String getMaxFramerate() {
		return this.maxFramerate;
	}
	
	public void setMaxFramerate(String maxFramerate) {
		this.maxFramerate = maxFramerate;
	}

	public boolean segmentAlignmentIsBoolValue() {
		return this.segmentAlignmentIsBool;
	}

	public boolean hasSegmentAlignment() {
		return this.usesSegmentAlignment;
	}

	public int getSegmentAligment() {
		return this.segmentAlignment;
	}
	
	public void setSegmentAlignment(String segmentAlignment) {
		
		if(segmentAlignment.equalsIgnoreCase("true")) {
			this.segmentAlignmentIsBool = true;
			this.usesSegmentAlignment = true;
			return;
		}
		
		if(segmentAlignment.equalsIgnoreCase("false")) {
			this.segmentAlignmentIsBool = true;
			this.usesSegmentAlignment = false;
			return;
		}
		
		this.segmentAlignmentIsBool = false;
		this.segmentAlignment = Integer.parseInt(segmentAlignment);
	}
	
	public void setSubsegmentAlignment(String subsegmentAlignment) {
		
		if(subsegmentAlignment.equalsIgnoreCase("true")) {
			this.subSegmentAlignmentIsBool = true;
			this.usesSubsegmentAlignment = true;
			return;
		}
		
		if(subsegmentAlignment.equalsIgnoreCase("false")) {
			this.subSegmentAlignmentIsBool = true;
			this.usesSubsegmentAlignment = false;
			return;
		}
		
		this.subSegmentAlignmentIsBool = false;
		this.subsegmentAlignment = Integer.parseInt(subsegmentAlignment);
	}

	public boolean hasSubsegmentAlignment() {
		return this.usesSubsegmentAlignment;
	}

	public int getSubsegmentAlignment() {
		return this.subsegmentAlignment;
	}

	public byte getSubsegmentStartsWithSAP() {
		return this.subsegmentStartsWithSAP;
	}
	
	public void setSubSegmentStartsWithSAP(byte subsegmentStartsWithSAP) {
		this.subsegmentStartsWithSAP = subsegmentStartsWithSAP;
	}

	public boolean getBitstreamSwitching() {
		return this.isBitstreamSwitching;
	}
	
	public void setBitstreamSwitching(boolean bitstreamSwitching) {
		this.isBitstreamSwitching = bitstreamSwitching;
	}

	public boolean subsegmentAlignmentIsBoolValue() {
		return this.subSegmentAlignmentIsBool;
	}

}
