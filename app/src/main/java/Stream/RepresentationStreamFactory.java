package Stream;

import java.util.Optional;
import java.util.Vector;

import dash.mpd.IAdaptationSet;
import dash.mpd.IMPD;
import dash.mpd.IPeriod;
import dash.mpd.IRepresentation;
import dash.mpd.ISegmentList;
import dash.mpd.ISegmentTemplate;

public interface RepresentationStreamFactory {
    public static RepresentationStream create(RepresentationStreamType type,
                                              IMPD mpd, IPeriod period, IAdaptationSet adaptationSet,
                                              IRepresentation representation, Optional<ISegmentList> segmentList,
                                              Optional<ISegmentTemplate> segmentTemplate,
                                              Optional<Vector<Integer>> segmentStartTimes) {

        switch(type) {

            case SEGMENT_TEMPLATE:
                return SegmentTemplateStream.newSegmentTemplateStream(mpd,
                        period, adaptationSet, representation,
                        segmentTemplate, segmentStartTimes);

            case SEGMENT_LIST:
                return new SegmentListStream(mpd, period, adaptationSet,
                        representation, segmentList, segmentStartTimes);

            case SINGLE_SEGMENT:
                return new SingleMediaSegmentStream(mpd, period, adaptationSet,
                        representation);

            default:
                return null;
        }

    }
}
