package jLibdash.dash.mpd;

public class ProgramInformation extends AbstractMPDElement implements IProgramInformation{
	
	private String title;
	private String source;
	private String copyright;
	private String lang;
	private String moreInformationURL;
	
	public ProgramInformation() {
		super();
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getCopyright() {
		return copyright;
	}

	public void setCopyright(String copyright) {
		this.copyright = copyright;
	}

	public String getLang() {
		return lang;
	}

	public void setLang(String lang) {
		this.lang = lang;
	}

	public String getMoreInformationURL() {
		return moreInformationURL;
	}

	public void setMoreInformationURL(String moreInformationURL) {
		this.moreInformationURL = moreInformationURL;
	}

}
