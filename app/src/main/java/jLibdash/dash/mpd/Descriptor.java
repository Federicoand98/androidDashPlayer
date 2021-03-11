package jLibdash.dash.mpd;

public class Descriptor extends AbstractMPDElement implements IDescriptor {

	private String schemeIdUri;
	private String value;
	
	public Descriptor() {
		super();
	}
	
	public String getSchemeIdUri() {
		return this.schemeIdUri;
	}

	public String getValue() {
		return this.value;
	}
	
	public void setValue(String value) {
		this.value = value;
	}
	
	public void setSchemeIdUri(String schemeIdUri) {
		this.schemeIdUri = schemeIdUri;
	}
	
}
