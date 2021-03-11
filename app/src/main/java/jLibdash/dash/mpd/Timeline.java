package jLibdash.dash.mpd;

public class Timeline extends AbstractMPDElement implements ITimeline {

	private int startTime;
	private int duration;
	private int repeatCount;
	
	public Timeline() {
		super();
	}
	
	public int getStartTime() {
		return this.startTime;
	}
	
	public void setStartTime(int startTime) {
		this.startTime = startTime;
	}

	public int getDuration() {
		return this.duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}
	
	public int getRepeatCount() {
		return this.repeatCount;
	}
	
	public void setRepeatCount(int repeatCount) {
		this.repeatCount = repeatCount;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + duration;
		result = prime * result + repeatCount;
		result = prime * result + startTime;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Timeline other = (Timeline) obj;
		if (duration != other.duration)
			return false;
		if (repeatCount != other.repeatCount)
			return false;
		if (startTime != other.startTime)
			return false;
		return true;
	}

}
