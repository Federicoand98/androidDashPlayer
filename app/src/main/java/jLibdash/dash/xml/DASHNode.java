package jLibdash.dash.xml;

import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

import jLibdash.dash.helpers.VectorUtils;
import jLibdash.dash.metrics.HTTPTransactionType;
import jLibdash.dash.mpd.AdaptationSet;
import jLibdash.dash.mpd.BaseUrl;
import jLibdash.dash.mpd.ContentComponent;
import jLibdash.dash.mpd.Descriptor;
import jLibdash.dash.mpd.MPD;
import jLibdash.dash.mpd.Metrics;
import jLibdash.dash.mpd.MultipleSegmentBase;
import jLibdash.dash.mpd.Period;
import jLibdash.dash.mpd.ProgramInformation;
import jLibdash.dash.mpd.Range;
import jLibdash.dash.mpd.Representation;
import jLibdash.dash.mpd.RepresentationBase;
import jLibdash.dash.mpd.SegmentBase;
import jLibdash.dash.mpd.SegmentList;
import jLibdash.dash.mpd.SegmentTemplate;
import jLibdash.dash.mpd.SegmentTimeline;
import jLibdash.dash.mpd.SegmentURL;
import jLibdash.dash.mpd.SubRepresentation;
import jLibdash.dash.mpd.Subset;
import jLibdash.dash.mpd.Timeline;
import jLibdash.dash.mpd.URLType;

public class DASHNode implements IDASHNode {
	
	private Vector<DASHNode> subNodes;
	private Map<String, String> attributes;
	private String name;
	private String text;
	private int type;
	private String mpdPath;
	
	public DASHNode() {
		subNodes = new Vector<DASHNode>();
		attributes = new HashMap<String, String>();		
	}
	
	public DASHNode(String name, String text, int type, String mpdPath) {
		super();
		this.name = name;
		this.text = text;
		this.type = type;
		this.mpdPath = mpdPath;
		this.attributes = new HashMap<String, String>();
		this.subNodes = new Vector<DASHNode>();
	}
	
	public DASHNode(DASHNode other) {
		this.name = other.getName();
		this.text = other.getText();
		this.type = other.getType();
		this.attributes = other.getAttributes();
		this.subNodes = new Vector<DASHNode>();
	}
	
	private ProgramInformation toProgramInformation() {
		
		ProgramInformation programInformation = new ProgramInformation();
		if(this.hasAttribute("lang"))
			programInformation.setLang(this.getAttributeValue("lang"));
		if(this.hasAttribute("moreInformationURL"))
			programInformation.setMoreInformationURL(this.getAttributeValue("moreInformationURL"));

		for(IDASHNode node : subNodes) {
			
			switch(node.getName()) {
			
			case "Title":
				programInformation.setTitle(node.getText());
				break;
				
			case "Source":
				programInformation.setSource(node.getText());;
				break;
				
			case "Copyright":
				programInformation.setCopyright(node.getText());
				break;
				
			default:
				programInformation.addAdditionalSubNode(node);
			}
		}
		
		programInformation.addRawAttributes(this.attributes);
		return programInformation;
	}
	
	private BaseUrl toBaseUrl() {
		
		BaseUrl baseUrl = new BaseUrl();
		
		if(this.hasAttribute("serviceLocation"))
			baseUrl.setServiceLocation(this.getAttributeValue("serviceLocation"));
		
		if(this.hasAttribute("byteRange"))
			baseUrl.setByteRange(this.getAttributeValue("byteRange"));
		
		if(this.getText().equals("./"))
			baseUrl.setUrl(this.mpdPath);
		else
			baseUrl.setUrl(this.getText());
		
		baseUrl.addRawAttributes(this.attributes);
		return baseUrl;
	}
	
	private Descriptor toDescriptor() {
		
		Descriptor descriptor = new Descriptor();
		Vector<DASHNode> subNodes = this.getSubNodes();
		
		if(this.hasAttribute("schemeIdUri"))
			descriptor.setSchemeIdUri(this.getAttributeValue("schemeIdUri"));
		
		if(this.hasAttribute("value"))
			descriptor.setValue(this.getAttributeValue("value"));
		
		for(DASHNode node : subNodes)
			descriptor.addAdditionalSubNode(node);
		
		descriptor.addRawAttributes(this.attributes);
		return descriptor;
	}
	
	private ContentComponent toContentComponent() {
		
		ContentComponent contentComponent = new ContentComponent();
		Vector<DASHNode> subNodes = this.getSubNodes();
		
		if(this.hasAttribute("id"))
			contentComponent.setId(Integer.parseInt(this.getAttributeValue("id")));
		
		if(this.hasAttribute("lang"))
			contentComponent.setLang(this.getAttributeValue("lang"));
		
		if(this.hasAttribute("contentType"))
			contentComponent.setContentType(this.getAttributeValue("contentType"));
		
		if(this.hasAttribute("par"))
			contentComponent.setPar(this.getAttributeValue("par"));
		
		for(DASHNode node : subNodes) {
			
			switch(node.getName()) {
			
			case "Accessibility":
				contentComponent.addAccessibility(node.toDescriptor());
				break;
				
			case "Role":
				contentComponent.addRole(node.toDescriptor());
				break;
				
			case "Rating":
				contentComponent.addRating(node.toDescriptor());
				break;
				
			case "Viewpoint":
				contentComponent.addViewpoint(node.toDescriptor());
				break;
				
			default:
				contentComponent.addAdditionalSubNode(node);
			}
			
		}
		
		contentComponent.addRawAttributes(this.attributes);
		return contentComponent;
	}
	
	private URLType toURLType(HTTPTransactionType type) {
		
		URLType urlType = new URLType();
		
		if(this.hasAttribute("sourceURL"))
			urlType.setSourceURL(this.getAttributeValue("sourceURL"));
		
		if(this.hasAttribute("range"))
			urlType.setRange(this.getAttributeValue("range"));
		
		for(DASHNode node : this.subNodes)
			urlType.addAdditionalSubNode(node);
		
		urlType.setType(type);
		urlType.addRawAttributes(this.attributes);
		return urlType;
	}
	
	private SegmentBase toSegmentBase() {
		
		SegmentBase segmentBase = new SegmentBase();
		Vector<DASHNode> subNodes = this.getSubNodes();
		this.setCommonValuesForSeg(segmentBase);
		
		for(DASHNode node : subNodes) {
			if(!node.getName().equals("Initialization") && !node.getName().equals("RepresentationIndex"))
				segmentBase.addAdditionalSubNode(node);
		}
		
		segmentBase.addRawAttributes(this.attributes);
		return segmentBase;
		
	}
	
	private Timeline toTimeline() {
		
		Timeline timeline = new Timeline();
		
		if(this.hasAttribute("t"))
			timeline.setStartTime(Integer.parseInt(this.getAttributeValue("t")));
		
		if(this.hasAttribute("d"))
			timeline.setDuration(Integer.parseInt(this.getAttributeValue("d")));
		
		if(this.hasAttribute("r"))
			timeline.setRepeatCount(Integer.parseInt(this.getAttributeValue("r")));
		
		timeline.addRawAttributes(this.attributes);
		return timeline;
		
	}
	
	private SegmentTimeline toSegmentTimeline() {
		
		SegmentTimeline segmentTimeline = new SegmentTimeline();
		Vector<DASHNode> subNodes = this.getSubNodes();
		
		for(DASHNode node : subNodes) {
			if(node.getName().equals("S"))
				segmentTimeline.addTimeline(node.toTimeline());
			else
				segmentTimeline.addAdditionalSubNode(node);
		}
		
		segmentTimeline.addRawAttributes(this.attributes);
		return segmentTimeline;
		
	}
	
	private SegmentURL toSegmentURL() {
		
		SegmentURL segmentUrl = new SegmentURL();
		
		if(this.hasAttribute("media"))
			segmentUrl.setMediaURI(this.getAttributeValue("media"));
		
		if(this.hasAttribute("mediaRange"))
			segmentUrl.setMediaRange(this.getAttributeValue("mediaRange"));
		
		if(this.hasAttribute("index"))
			segmentUrl.setIndexURI(this.getAttributeValue("index"));
		
		if(this.hasAttribute("indexRange"))
			segmentUrl.setIndexRange(this.getAttributeValue("indexRange"));
		
		for(DASHNode node : subNodes)
			segmentUrl.addAdditionalSubNode(node);
		
		segmentUrl.addRawAttributes(this.attributes);
		return segmentUrl;
	}
	
	private SegmentList toSegmentList() {
		
		SegmentList segmentList = new SegmentList();
		Vector<DASHNode> subNodes = this.getSubNodes();
		
		this.setCommonValuesForMSeg(segmentList);
		
		if(this.hasAttribute("xlink:href"))
			segmentList.setXlinkHref(this.getAttributeValue("xlink:href"));
		
		if(this.hasAttribute("xlink:actuate"))
			segmentList.setXlinkActuate(this.getAttributeValue("xlink:actuate"));
		
		for(DASHNode node : subNodes) {
			
			if(node.getName().equals("SegmentURL"))
				segmentList.addSegmentURL(node.toSegmentURL());
			else if(!node.getName().equals("SegmentTimeline") && !node.getName().equals("BitstreamSwitching")
					&& !node.getName().equals("Initialization") && !node.getName().equals("RepresentationIndex"))
				segmentList.addAdditionalSubNode(node);
		}
		
		segmentList.addRawAttributes(this.attributes);
		return segmentList;
			
	}
	
	private SegmentTemplate toSegmentTemplate() {
		
		SegmentTemplate segmentTemplate = new SegmentTemplate();
		Vector<DASHNode> subNodes = this.getSubNodes();
		
		this.setCommonValuesForMSeg(segmentTemplate);
		
		if(this.hasAttribute("media"))
			segmentTemplate.setMedia(this.getAttributeValue("media"));
		
		if(this.hasAttribute("index"))
			segmentTemplate.setMedia(this.getAttributeValue("index"));
		
		if(this.hasAttribute("initialization"))
			segmentTemplate.setSegmentTemplateInitialization(this.getAttributeValue("initialization"));
		
		if(this.hasAttribute("bitstreamSwitching"))
			segmentTemplate.setSegmentTemplateBitstreamSwitching(this.getAttributeValue("bitstreamSwitching"));
		
		for(DASHNode node : subNodes)
			if (!node.getName().equals("SegmentTimeline") && !node.getName().equals("BitstreamSwitching") &&
		            !node.getName().equals("Initialization") && !node.getName().equals("RepresentationIndex"))
		            segmentTemplate.addAdditionalSubNode(node);
		
		segmentTemplate.addRawAttributes(this.attributes);
		return segmentTemplate;
	}
	
	private SubRepresentation toSubRepresentation() {
		
		SubRepresentation subRepresentation = new SubRepresentation();
		Vector<DASHNode> subNodes = this.getSubNodes();
		
		this.setCommonValuesForRep(subRepresentation);
		
		if(this.hasAttribute("level"))
			subRepresentation.setLevel(Integer.parseInt(this.getAttributeValue("level")));
		
		if(this.hasAttribute("dependencyLevel"))
			subRepresentation.setDependencyLevel(this.getAttributeValue("dependencyLevel"));
		
		if(this.hasAttribute("bandwidth"))
			subRepresentation.setBandWidth(Integer.parseInt(this.getAttributeValue("bandwidth")));
		
		if(this.hasAttribute("contentComponent"))
			subRepresentation.setContentComponent(this.getAttributeValue("contentComponent"));
		
		for(DASHNode node : subNodes) 
			if (!node.getName().equals("FramePacking") && !node.getName().equals("AudioChannelConfiguration")
					&& !node.getName().equals("ContentProtection"))
	            subRepresentation.addAdditionalSubNode(node);
		
		subRepresentation.addRawAttributes(this.attributes);
		return subRepresentation;
	}
	
	private Representation toRepresentation() {
		
		Representation representation = new Representation();
		Vector<DASHNode> subNodes = this.getSubNodes();
		
		this.setCommonValuesForRep(representation);
		
		if (this.hasAttribute("id"))
	        representation.setId(this.getAttributeValue("id"));
	   
	    if (this.hasAttribute("bandwidth"))
	        representation.setBandwidth(Integer.parseInt(this.getAttributeValue("bandwidth")));
	  
	    if (this.hasAttribute("qualityRanking"))
	        representation.setQualityRanking(Integer.parseInt(this.getAttributeValue("qualityRanking")));
	    
	    if (this.hasAttribute("dependencyId"))
	        representation.setDependencyId(this.getAttributeValue("dependencyId"));
	    
	    if (this.hasAttribute("mediaStreamStructureId"))
	        representation.setMediaStreamStructureId(this.getAttributeValue("mediaStreamStructureId"));
	    
	    for(DASHNode node : subNodes) {
	    	
	    	if (node.getName().equals("BaseURL"))
	            representation.addBaseUrl(node.toBaseUrl());

	    	else if (node.getName().equals("SubRepresentation"))
	            representation.addSubRepresentation(node.toSubRepresentation());
	        
	    	else if (node.getName().equals("SegmentBase"))
	            representation.setSegmentBase(node.toSegmentBase());

	    	else if (node.getName().equals("SegmentList"))
	            representation.setSegmentList(node.toSegmentList());

	    	else if (node.getName().equals("SegmentTemplate"))
	            representation.setSegmentTemplate(node.toSegmentTemplate());

	    	else if (!node.getName().equals("FramePacking") && !node.getName().equals("AudioChannelConfiguration")
	    			&& !node.getName().equals("ContentProtection"))
	        	representation.addAdditionalSubNode(node);
	    	}
	    
	    representation.addRawAttributes(this.attributes);
	    return representation;		
	}
	
	private AdaptationSet toAdaptationSet() {
		
		AdaptationSet adaptationSet = new AdaptationSet();
		Vector<DASHNode> subNodes = this.getSubNodes();
		
		this.setCommonValuesForRep(adaptationSet);
		
		if (this.hasAttribute("xlink:href"))
	        adaptationSet.setXlinkHref(this.getAttributeValue("xlink:href"));
	    
	    if (this.hasAttribute("xlink:actuate"))
	        adaptationSet.setXlinkActuate(this.getAttributeValue("xlink:actuate"));
	    
	    if (this.hasAttribute("id"))
	        adaptationSet.setId(Integer.parseInt(this.getAttributeValue("id")));
	    
	    if (this.hasAttribute("group"))
	        adaptationSet.setGroup(Integer.parseInt(this.getAttributeValue("group")));
	    
	    if (this.hasAttribute("lang"))
	        adaptationSet.setLang(this.getAttributeValue("lang"));
	    
	    if (this.hasAttribute("contentType"))
	        adaptationSet.setContentType(this.getAttributeValue("contentType"));
	    
	    if (this.hasAttribute("par"))
	        adaptationSet.setPar(this.getAttributeValue("par"));
	    
	    if (this.hasAttribute("minBandwidth"))
	        adaptationSet.setMinBandwidth(Integer.parseInt(this.getAttributeValue("minBandwidth")));
	    
	    if (this.hasAttribute("maxBandwidth"))
	        adaptationSet.setMaxBandwidth(Integer.parseInt(this.getAttributeValue("maxBandwidth")));
	    
	    if (this.hasAttribute("minWidth"))
	        adaptationSet.setMinWidth(Integer.parseInt(this.getAttributeValue("minWidth")));
	    
	    if (this.hasAttribute("maxWidth"))
	        adaptationSet.setMaxWidth(Integer.parseInt(this.getAttributeValue("maxWidth")));
	    
	    if (this.hasAttribute("minHeight"))
	        adaptationSet.setMinHeight(Integer.parseInt(this.getAttributeValue("minHeight")));
	    
	    if (this.hasAttribute("maxHeight"))
	        adaptationSet.setMaxHeight(Integer.parseInt(this.getAttributeValue("maxHeight")));
	    
	    if (this.hasAttribute("minFrameRate"))
	        adaptationSet.setMinFramerate(this.getAttributeValue("minFrameRate"));
	    
	    if (this.hasAttribute("maxFrameRate"))
	        adaptationSet.setMaxFramerate(this.getAttributeValue("maxFrameRate"));
	    
	    if (this.hasAttribute("segmentAlignment"))
	        adaptationSet.setSegmentAlignment(this.getAttributeValue("segmentAlignment"));
	    
	    if (this.hasAttribute("subsegmentAlignment"))
	        adaptationSet.setSubsegmentAlignment(this.getAttributeValue("subsegmentAlignment"));
	    
	    if (this.hasAttribute("subsegmentStartsWithSAP"))
	        adaptationSet.setMaxHeight(Byte.parseByte(this.getAttributeValue("subsegmentStartsWithSAP")));
	    
	    if (this.hasAttribute("bitstreamSwitching"))
	        adaptationSet.setBitstreamSwitching(Boolean.parseBoolean(this.getAttributeValue("bitstreamSwitching")));
	    
	    for(DASHNode node : subNodes) {
	    	
	    	switch(node.getName()) {
	    	
	    	case "Accessibility":
	            adaptationSet.addAccessibility(node.toDescriptor());
	            break;
	       
	       case "Role":
	            adaptationSet.addRole(node.toDescriptor());
	            break;

	       case "Rating":
	            adaptationSet.addRating(node.toDescriptor());
	            break;

	       case "Viewpoint":
	            adaptationSet.addViewpoint(node.toDescriptor());
	            break;

	       case "ContentComponent":
	            adaptationSet.addContentComponent(node.toContentComponent());
	            break;

	       case "BaseURL":
	            adaptationSet.addBaseUrl(node.toBaseUrl());
	            break;

	       case "SegmentBase":
	            adaptationSet.setSegmentBase(node.toSegmentBase());
	            break;

	       case "SegmentList":
	            adaptationSet.setSegmentList(node.toSegmentList());
	            break;

	       case "SegmentTemplate":
	            adaptationSet.setSegmentTemplate(node.toSegmentTemplate());
	            break;

	       case "Representation":
	            adaptationSet.addRepresentation(node.toRepresentation());
	            break;

	    	}
	    	
	        if (!node.getName().equals("FramePacking") && !node.getName().equals("AudioChannelConfiguration")
	        		&& !node.getName().equals("ContentProtection"))
	            adaptationSet.addAdditionalSubNode(node);
	    	
	    }
	    
	    adaptationSet.addRawAttributes(this.attributes);
	    return adaptationSet;
	}
	
	private Subset toSubset() {
		
		Subset subset = new Subset();
		
		if(this.hasAttribute("contains"))
			subset.setSubset(this.getAttributeValue("contains"));
		
		subset.addRawAttributes(this.attributes);
		return subset;
	}
	
	private Period toPeriod() {
		
		Period period = new Period();
		Vector<DASHNode> subNodes = this.getSubNodes();
		
		if (this.hasAttribute("xlink:href"))
	        period.setXlinkHref(this.getAttributeValue("xlink:href"));
	    
	    if (this.hasAttribute("xlink:actuate"))
	        period.setXlinkActuate(this.getAttributeValue("xlink:actuate"));
	    
	    if (this.hasAttribute("id"))
	        period.setId(this.getAttributeValue("id"));
	    
	    if (this.hasAttribute("start"))
	        period.setStart(this.getAttributeValue("start"));
	    
	    if (this.hasAttribute("duration"))
	        period.setDuration(this.getAttributeValue("duration"));
	    
	    if (this.hasAttribute("bitstreamSwitching"))
	        period.setBitstreamSwitching(Boolean.parseBoolean(this.getAttributeValue("bitstreamSwitching")));
	
	    for(DASHNode node : subNodes) {
	    	
	    	switch(node.getName()) {
	    	
	    	case "BaseURL":
	            period.addBaseUrl(node.toBaseUrl());
	            break;
	        
	        case "AdaptationSet":
	            period.addAdaptationSet(node.toAdaptationSet());
	            break;
	        
	        case "Subset":
	            period.addSubset(node.toSubset());
	            break;
	        
	        case "SegmentBase":
	            period.setSegmentBase(node.toSegmentBase());
	            break;
	        
	        case "SegmentList":
	            period.setSegmentList(node.toSegmentList());
	            break;
	        
	        case "SegmentTemplate":
	            period.setSegmentTemplate(node.toSegmentTemplate());
	            break;
	            
	        default:
	        	period.addAdditionalSubNode(node);
	        	break;	        	
	    	}
	    }
	    
	    period.addRawAttributes(this.attributes);
	    return period;	    
	}
	
	private Range toRange() {
		
		Range range = new Range();
		
		if(this.hasAttribute("starttime"))
			range.setStartTime(this.getAttributeValue("starttime"));
		
		if(this.hasAttribute("duration"))
			range.setDuration(this.getAttributeValue("duration"));
		
		return range;		
	}
	
	private Metrics toMetrics() {
		
		Metrics metrics = new Metrics();
		
		if(this.hasAttribute("metrics"))
			metrics.setMetrics(this.getAttributeValue("metrics"));
		
		for(DASHNode node : subNodes) {
			
			switch(node.getName()) {
			
			case "Reporting":
				metrics.addReporting(node.toDescriptor());
				break;
				
			case "Range":
				metrics.addRange(node.toRange());
				break;
				
			default:
				metrics.addAdditionalSubNode(node);
			}
		}
		
		metrics.addRawAttributes(this.attributes);
		return metrics;
	}
	
	public MPD toMPD() {
		
		MPD mpd = new MPD();
		Vector<DASHNode> subNodes = this.getSubNodes();
		
		if (this.hasAttribute("id"))
	        mpd.setId(this.getAttributeValue("id"));
	    
	    if (this.hasAttribute("profiles"))
	        mpd.setProfiles(this.getAttributeValue("profiles"));
	    
	    if (this.hasAttribute("type"))
	        mpd.setType(this.getAttributeValue("type"));
	    
	    if (this.hasAttribute("availabilityStartTime"))
	        mpd.setAvailabilityStarttime(this.getAttributeValue("availabilityStartTime"));
	    
	    if (this.hasAttribute("availabilityEndTime"))
	        mpd.setAvailabilityEndtime(this.getAttributeValue("availabilityEndTime"));
	    
	    if (this.hasAttribute("mediaPresentationDuration"))
	        mpd.setMediaPresentationDuration(this.getAttributeValue("mediaPresentationDuration"));
	    
	    if (this.hasAttribute("minimumUpdatePeriod"))
	        mpd.setMinimumUpdatePeriod(this.getAttributeValue("minimumUpdatePeriod"));
	    
	    if (this.hasAttribute("minBufferTime"))
	        mpd.setMinBufferTime(this.getAttributeValue("minBufferTime"));
	    
	    if (this.hasAttribute("timeShiftBufferDepth"))
	        mpd.setTimeShiftBufferDepth(this.getAttributeValue("timeShiftBufferDepth"));
	    
	    if (this.hasAttribute("suggestedPresentationDelay"))
	        mpd.setSuggestedPresentationDelay(this.getAttributeValue("suggestedPresentationDelay"));
	    
	    if (this.hasAttribute("maxSegmentDuration"))
	        mpd.setMaxSegmentDuration(this.getAttributeValue("maxSegmentDuration"));
	    
	    if (this.hasAttribute("maxSubsegmentDuration"))
	        mpd.setMaxSubsegmentDuration(this.getAttributeValue("maxSubsegmentDuration"));
	    
	    for(DASHNode node : subNodes) {
	    	
	    	switch(node.getName()) {
	    	
	    	case "ProgramInformation":
	            mpd.addProgramInformation(node.toProgramInformation());
	            break;
	        
	        case "BaseURL":
	            mpd.addBaseUrl(node.toBaseUrl());
	            break;
	        
	        case "Location":
	            mpd.addLocation(node.getText());
	            break;
	        
	        case "Period":
	            mpd.addPeriod(node.toPeriod());
	            break;
	        
	        case "Metrics":
	            mpd.addMetrics(node.toMetrics());
	            break;
	        
	        default:
	        	mpd.addAdditionalSubNode(node);
	        	break;
	    	}
	    }
	    
	    BaseUrl mpdPathBaseUrl = new BaseUrl();
	    mpdPathBaseUrl.setUrl(mpdPath);
	    mpd.setMpdPathBaseUrl(mpdPathBaseUrl);
	    
	    mpd.addRawAttributes(this.attributes);
	    return mpd;
	}
	
	public void setMPDPath(String path) {
		this.mpdPath = path;
	}

	public Vector<DASHNode> getNodes() {
		return this.subNodes;
	}
	
	public Vector<DASHNode> getSubNodes() {
		return this.subNodes;
	}
	
	public String getName() {
		return this.name;
	}

	public void addSubNode(DASHNode node) {
		if(node.getName() != null)
			this.subNodes.add(node);
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getAttributeValue(String key) {
		return this.attributes.get(key);
	}
	
	public boolean hasAttribute(String name) {
		return this.attributes.containsKey(name);
	}
	
	public void addAttribute(String key, String value) {
		this.attributes.put(key, value);
	}
	
	public void setAttributes(Map<String, String> attributes) {
		this.attributes = attributes;
	}

	public Vector<String> getAttributeKeys() {
		return VectorUtils.toVector(this.attributes.keySet().toArray(new String[0]));
	}
	
	public boolean hasText() {
		return false;
	}

	public String getText() {
		if(this.type == 3 || this.type == 1)
			return this.text;
		else {
			if(!this.subNodes.isEmpty())
				return this.subNodes.get(0).getText();
			else return "";
		}
	}
	
	public void setText(String text) {
		this.text = text;
	}
	
	public String toString() {
		String toReturn = this.name;
		Vector<String> keys = this.getAttributeKeys();
		
		for(String key : keys) {
			toReturn += " " + key + "=" + this.getAttributeValue(key);
		}
		
		toReturn += "\n";
		return toReturn;
	}

	public Map<String, String> getAttributes() {
		return this.attributes;
	}

	public int getType() {
		return this.type;
	}
	
	public void setType(int type) {
		this.type = type;
	}
	
	private void setCommonValuesForRep(RepresentationBase object) {
		
		Vector<DASHNode> subNodes = this.getSubNodes();
		
		if (this.hasAttribute("profiles"))
	        object.setProfiles(this.getAttributeValue("profiles"));
	    
	    if (this.hasAttribute("width"))
	        object.setWidth(Integer.parseInt(this.getAttributeValue("width")));
	    
	    if (this.hasAttribute("height"))
	        object.setHeight(Integer.parseInt(this.getAttributeValue("height")));
	    
	    if (this.hasAttribute("sar"))
	        object.setSar(this.getAttributeValue("sar"));
	    
	    if (this.hasAttribute("frameRate"))
	        object.setFrameRate(this.getAttributeValue("frameRate"));
	    
	    if (this.hasAttribute("audioSamplingRate"))
	        object.setAudioSamplingRate(this.getAttributeValue("audioSamplingRate"));
	   
	    if (this.hasAttribute("mimeType"))
	        object.setMimeType(this.getAttributeValue("mimeType"));
	    
	    if (this.hasAttribute("segmentProfiles"))
	        object.setSegmentProfiles(this.getAttributeValue("segmentProfiles"));

	    if (this.hasAttribute("codecs"))
	        object.setCodecs(this.getAttributeValue("codecs"));
	    
	    if (this.hasAttribute("maximumSAPPeriod"))
	        object.setMaximumSAPPeriod(Integer.parseInt(this.getAttributeValue("maximumSAPPeriod")));
	    
	    if (this.hasAttribute("startWithSAP"))
	        object.setStartWithSAP(Byte.parseByte(this.getAttributeValue("startWithSAP")));
	    
	    if (this.hasAttribute("maxPlayoutRate"))
	        object.setMaxPlayoutRate(Integer.parseInt(this.getAttributeValue("maxPlayoutRate")));

	    if (this.hasAttribute("codingDependency"))
	        object.setCodingDependency(Boolean.getBoolean(this.getAttributeValue("codingDependency")));
	   
	    if (this.hasAttribute("scanType"))
	        object.setScanType(this.getAttributeValue("scanType"));
	    
	    for(DASHNode node : subNodes) {
	    	
	    	switch(node.getName()) {
	    	
	    	case "FramePacking":
	    		object.addFramePacking(node.toDescriptor());
	    		break;
	    		
	    	case "AudioChannelConfiguration":
	    		object.addAudioChannelConfiguration(node.toDescriptor());
	    		break;
	    		
	    	case "ContentProtection":
	    		object.addContentProtection(node.toDescriptor());
	    		break;	    		
	    	}
	    }
	}
	
	private void setCommonValuesForSeg(SegmentBase object) {
		
		Vector<DASHNode> subNodes = this.getSubNodes();
		
		if(this.hasAttribute("timescale"))
			object.setTimescale(Integer.parseInt(this.getAttributeValue("timescale")));
		
		if(this.hasAttribute("presentationTimeOffset"))
			object.setPresentationTimeOffset(Integer.parseInt(this.getAttributeValue("presentationTimeOffset")));
		
		if(this.hasAttribute("indexRange"))
			object.setIndexRange(this.getAttributeValue("indexRange"));
		
		if(this.hasAttribute("indexRangeExact"))
			object.setIndexRangeExact(Boolean.parseBoolean(this.getAttributeValue("indexRangeExact")));
		
		for(DASHNode node : subNodes) {
			
			switch(node.getName()) {
			
			case "Initialization":
				object.setInitialization(node.toURLType(HTTPTransactionType.InitializationSegment));
				break;
				
			case "RepresentationIndex":
				object.setRepresentationIndex(node.toURLType(HTTPTransactionType.IndexSegment));
				break;
				
			}
		}
		
	}
	
	private void setCommonValuesForMSeg(MultipleSegmentBase object) {
		
		Vector<DASHNode> subNodes = this.getSubNodes();
		this.setCommonValuesForSeg(object);
		
		if(this.hasAttribute("duration"))
			object.setDuration(Integer.parseInt(this.getAttributeValue("duration")));
		
		if(this.hasAttribute("startNumber"))
			object.setStartNumber(Integer.parseInt(this.getAttributeValue("startNumber")));
		
		for(DASHNode node : subNodes) {
			
			switch(node.getName()) {
			
			case "SegmentTimeline":
				object.setSegmentTimeline(node.toSegmentTimeline());
				break;
				
			case "BitstreamSwitching":
				object.setBitStreamSwitching(node.toURLType(HTTPTransactionType.BitstreamSwitchingSegment));
				break;
				
			}	
		}
	}
	
	public String getMPDPath() {
		return this.mpdPath;
	}
	
	public boolean equals(IDASHNode node) {
		
		Vector<DASHNode> subNodes = node.getNodes();
		if(subNodes.size() != this.subNodes.size())
			return false;
		for(int i=0; i<this.subNodes.size(); i++)
			if(!subNodes.get(i).equals(this.subNodes.get(i)))
				return false;
		
		if(this.attributes.equals(node.getAttributes()) &&
				this.name.equals(node.getName()) &&
				this.text.equals(node.getText()) &&
				this.type == node.getType() &&
				this.mpdPath.equals(node.getMPDPath()))
			return true;
		
		return false;
	}

}
