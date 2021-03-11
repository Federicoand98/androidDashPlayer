package jLibdash.dash.xml;

import java.util.Map;
import java.util.Vector;

public interface IDASHNode {
	
	public Vector<DASHNode> getNodes();
	public Vector<String> getAttributeKeys();
	public String getName();
	public String getText();
	public Map<String, String> getAttributes();
	public int getType();
	public String getAttributeValue(String key);
	public boolean hasAttribute(String name);
	public boolean hasText();
	public String getMPDPath();
	
	public boolean equals(IDASHNode node);
	

}
