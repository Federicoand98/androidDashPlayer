package jLibdash.dash.manager;

import java.io.InputStream;

import org.dom4j.DocumentException;

import jLibdash.dash.mpd.IMPD;


public interface IDASHManager {
	
	public IMPD open(String path, InputStream stream) throws DocumentException;
	public IMPD open(String path) throws DocumentException;

}
