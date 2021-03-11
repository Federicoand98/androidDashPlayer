package jLibdash.dash.mpd;

import java.util.Vector;

public interface IContentComponent extends IMPDElement {
	
	public Vector<IDescriptor> getAccessibility();
	public Vector<IDescriptor> getRole();
	public Vector<IDescriptor> getRating();
	public Vector<IDescriptor> getViewpoint();
	public int getId();
	public String getLang();
	public String getContentType();
	public String getPar();

}
