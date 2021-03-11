package jLibdash.dash.mpd;

import java.util.Vector;

public class SegmentTimeline extends AbstractMPDElement implements ISegmentTimeline {

	private Vector<ITimeline> timelines;
	
	public SegmentTimeline() {
		super();
		timelines = new Vector<ITimeline>();
	}

	public Vector<ITimeline> getTimelines() {
		return this.timelines;
	}
	
	public void addTimeline(Timeline timeline) {
		this.timelines.add(timeline);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((timelines == null) ? 0 : timelines.hashCode());
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
		SegmentTimeline other = (SegmentTimeline) obj;
		if (timelines == null) {
			if (other.timelines != null)
				return false;
		} else if (!timelines.equals(other.timelines))
			return false;
		return true;
	}

}
