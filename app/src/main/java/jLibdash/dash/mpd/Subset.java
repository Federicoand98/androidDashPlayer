package jLibdash.dash.mpd;

import java.util.Vector;

import jLibdash.dash.helpers.VectorUtils;


public class Subset extends AbstractMPDElement implements ISubset {

	private Vector<Integer> subset;
	
	public Subset() {
		super();
	}
	
	public Vector<Integer> contains() {
		return this.subset;
	}
	
	public void setSubset(String subset) {
		this.subset = VectorUtils.toIntegerVector(VectorUtils.toVector(subset.split(" ")));
	}

}
