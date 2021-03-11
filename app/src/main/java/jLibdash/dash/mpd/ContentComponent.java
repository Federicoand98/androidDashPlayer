package jLibdash.dash.mpd;
import java.util.Vector;

public class ContentComponent extends AbstractMPDElement implements IContentComponent {
	
	private Vector<IDescriptor> accessibility;
	private Vector<IDescriptor> role;
	private Vector<IDescriptor> rating;
	private Vector<IDescriptor> viewpoint;
	private int id;
	private String lang;
	private String contentType;
	private String par;
	
	public ContentComponent() {
		this.accessibility = new Vector<IDescriptor>();
		this.role = new Vector<IDescriptor>();
		this.rating = new Vector<IDescriptor>();
		this.viewpoint = new Vector<IDescriptor>();
		this.id = 0;
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

	public int getId() {
		return this.id;
	}
	
	public void setId(int id) {
		this.id = id;
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

}
