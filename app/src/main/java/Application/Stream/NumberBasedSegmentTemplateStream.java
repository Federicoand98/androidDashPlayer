package Application.Stream;

import java.util.Optional;
import java.util.Vector;

import jLibdash.dash.mpd.IAdaptationSet;
import jLibdash.dash.mpd.IMPD;
import jLibdash.dash.mpd.IPeriod;
import jLibdash.dash.mpd.IRepresentation;
import jLibdash.dash.mpd.ISegment;
import jLibdash.dash.mpd.ISegmentTemplate;


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
