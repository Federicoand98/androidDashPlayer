package jLibdash.dash.manager;

import java.io.InputStream;

import org.dom4j.DocumentException;

import jLibdash.dash.mpd.IMPD;
import jLibdash.dash.mpd.MPD;
import jLibdash.dash.xml.EvoluteDOMParser;


public class DASHManager implements IDASHManager {
	
	public static IDASHManager newDASHManager() {
		return new DASHManager();
	}

	public IMPD open(String path, InputStream stream) throws DocumentException {
		
		EvoluteDOMParser parser = new EvoluteDOMParser(path, stream);
		int fetchTime = (int) System.currentTimeMillis();
		
		parser.parse();
		IMPD mpd = parser.getRootNode().toMPD();
		
		if(mpd != null)
			((MPD)mpd).setFetchTime(fetchTime);
		
		return mpd;
		
	}
	
	public IMPD open(String path) throws DocumentException {
		
		EvoluteDOMParser parser = new EvoluteDOMParser(path);
		int fetchTime = (int) System.currentTimeMillis();
		
		parser.parse();
		MPD mpd = parser.getRootNode().toMPD();
		
		if(mpd != null)
			mpd.setFetchTime(fetchTime);
		
		return mpd;
		
	}

}
