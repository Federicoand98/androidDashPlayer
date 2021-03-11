package jLibdash.dash.mpd;

import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

import jLibdash.dash.xml.IDASHNode;


public class AbstractMPDElement implements IMPDElement {

	private Vector<IDASHNode> additionalSubNodes;
	private Map<String, String> rawAttributes;
	
	public AbstractMPDElement() {
		additionalSubNodes = new Vector<IDASHNode>();
		rawAttributes = new HashMap<String, String>();
	}
	
	public Vector<IDASHNode> getAdditionalSubNodes() {
		return this.additionalSubNodes;
	}

	public Map<String, String> getRawAttributes() {
		return this.rawAttributes;
	}
	
	public void addAdditionalSubNode(IDASHNode node) {
		this.additionalSubNodes.add(node);
	}
	
	public void addRawAttributes(Map<String, String> attributes) {
		this.rawAttributes = attributes;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((additionalSubNodes == null) ? 0 : additionalSubNodes.hashCode());
		result = prime * result + ((rawAttributes == null) ? 0 : rawAttributes.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AbstractMPDElement other = (AbstractMPDElement) obj;
		if (additionalSubNodes == null) {
			if (other.additionalSubNodes != null)
				return false;
		} else if (!additionalSubNodes.equals(other.additionalSubNodes))
			return false;
		if (rawAttributes == null) {
			if (other.rawAttributes != null)
				return false;
		} else if (!rawAttributes.equals(other.rawAttributes))
			return false;
		return true;
	}

}
