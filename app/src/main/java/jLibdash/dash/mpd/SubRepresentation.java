package jLibdash.dash.mpd;

import java.util.Vector;

import jLibdash.dash.helpers.VectorUtils;


public class SubRepresentation extends RepresentationBase implements ISubRepresentation {

	private int level;
	private Vector<Integer> dependencyLevel;
	private int bandWidth;
	private Vector<String> contentComponent;
	
	public SubRepresentation() {
		super();
		this.level = 0;
		this.bandWidth = 0;
	}

	public int getLevel() {
		return this.level;
	}
	
	public void setLevel(int level) {
		this.level = level;
	}

	public Vector<Integer> getDependencyLevel() {
		return this.dependencyLevel;
	}
	
	public void setDependencyLevel(String dependencyLevel) {
		this.dependencyLevel = VectorUtils.toIntegerVector(VectorUtils.toVector(dependencyLevel.split(" ")));
	}

	public int getBandWidth() {
		return this.bandWidth;
	}
	
	public void setBandWidth(int bandWidth) {
		this.bandWidth = bandWidth;
	}

	public Vector<String> getContentComponent() {
		return this.contentComponent;
	}
	
	public void setContentComponent(String contentComponent) {
		this.contentComponent = VectorUtils.toVector(contentComponent.split(" "));
	}

}
