package jLibdash.dash.mpd;

import java.util.Map;
import java.util.Vector;

import jLibdash.dash.xml.IDASHNode;

public interface IMPDElement {
	
	public Vector<IDASHNode> getAdditionalSubNodes();
	public Map<String, String> getRawAttributes();

}
