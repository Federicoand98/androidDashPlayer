package jLibdash.dash.mpd;

import java.util.Vector;

public interface ISubRepresentation extends IRepresentationBase {
	
	public int getLevel();
	public Vector<Integer> getDependencyLevel();
	public int getBandWidth();
	public Vector<String> getContentComponent();

}
