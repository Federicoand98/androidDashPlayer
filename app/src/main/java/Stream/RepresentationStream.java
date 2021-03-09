package Stream;

import java.util.List;

import dash.mpd.IRepresentation;
import dash.mpd.ISegment;

public interface RepresentationStream {
    public IRepresentation getSourceRepresentation();
    public ISegment getInitializationSegment();
    public ISegment getIndexSegment(int segmentNumber);
    public ISegment getMediaSegment(int segmentNumber);
    public ISegment getBitstreamSwitchingSegment();
    public List<Integer> getSegmentStartTimes();
    public int getSegmentDuration(int segmentNumber);
    public int getTimescale();
    public RepresentationStreamType getStreamType();
    public int getSize();
    public int getFirstSegmentNumber();
    public int getCurrentSegmentNumber();
    public int getLastSegmentNumber();
    public int getAverageSegmentDuration();
}
