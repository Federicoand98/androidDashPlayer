package jLibdash.dash.mpd;

public class Range implements IRange {
	
	private String startTime;
	private String duration;

	public String getStartTime() {
		return this.startTime;
	}
	
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getDuration() {
		return this.duration;
	}
	
	public void setDuration(String duration) {
		this.duration = duration;
	}

}
