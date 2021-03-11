package jLibdash.dash.helpers;

public class ObjectUrl {
	
	private String host;
	private int port;
	private String path;
	
	public ObjectUrl(String host, int port, String path){		
		this.host = host;
		this.port = port;
		this.path = path;
	}
	
	public String getHost() {
		return host;
	}
	
	public int getPort() {
		return port;
	}
	
	public String getPath() {
		return path;
	}	

}
