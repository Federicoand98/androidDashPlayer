package Stream;

import java.util.Optional;
import java.util.Vector;

import dash.mpd.IAdaptationSet;
import dash.mpd.IMPD;
import dash.mpd.IPeriod;
import dash.mpd.IRepresentation;
import dash.mpd.ISegment;
import dash.mpd.ISegmentTemplate;

public class NumberBasedSegmentTemplateStream extends SegmentTemplateStream {

    public NumberBasedSegmentTemplateStream(IMPD mpd, IPeriod period, IAdaptationSet adaptationSet,
                                            IRepresentation representation, ISegmentTemplate segmentTemplate,
                                            Optional<Vector<Integer>> segmentStartTimes) {
        super(mpd, period, adaptationSet, representation, segmentTemplate,
                segmentStartTimes);
    }

    @Override
    public ISegment getIndexSegment(int segmentNumber) {
        return this.segmentTemplate.getIndexSegmentFromNumber(baseUrls,
                representation.getId(), representation.getBandwidth(),
                this.segmentTemplate.getStartNumber() + segmentNumber);
    }

    @Override
    public ISegment getMediaSegment(int segmentNumber) {
        return this.segmentTemplate.getMediaSegmentFromNumber(baseUrls,
                representation.getId(), representation.getBandwidth(),
                this.segmentTemplate.getStartNumber() + segmentNumber);
    }

    @Override
    public int getAverageSegmentDuration() {
        return this.segmentTemplate.getDuration();
    }

    @Override
    public int getSegmentDuration(int segmentNumber) {
        return this.segmentTemplate.getDuration();
    }
}
