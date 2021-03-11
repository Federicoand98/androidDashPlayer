package Application.Network;


import jLibdash.dash.mpd.IRepresentation;
import jLibdash.dash.mpd.ISegment;

public class DataSegment {

    private ISegment segment;
    private IRepresentation representation;
    private int timescale;
    private int start;
    private int duration;
    private long millisecStart;
    private int millisecDuration;
    private byte[] data;

    public DataSegment(ISegment segment, IRepresentation representation, int timescale, int start, int duration) {
        this.segment = segment;
        this.representation = representation;
        this.timescale = timescale;
        this.start = start;
        this.duration = duration;
        this.millisecStart = (long)(start / (double)timescale) * 1000;
        this.millisecDuration = ((int)(duration / (double)timescale)) * 1000;
        data = new byte[0];
    }

    public int getSize() {
        return data.length;
    }

    public void alloc(int length) {
        data = new byte[length];
    }

    public ISegment getSegment() {
        return segment;
    }

    public IRepresentation getRepresentation() {
        return representation;
    }

    public int getTimescale() {
        return timescale;
    }

    public int getStart() {
        return start;
    }

    public int getDuration() {
        return duration;
    }

    public long getMillisecStart() {
        return millisecStart;
    }

    public int getMillisecDuration() {
        return millisecDuration;
    }

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }
}
