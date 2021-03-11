package jLibdash.dash.mpd;

public interface IProgramInformation extends IMPDElement {
	
	public String getTitle();
	public String getSource();
	public String getCopyright();
	public String getLang();
	public String getMoreInformationURL();

}
