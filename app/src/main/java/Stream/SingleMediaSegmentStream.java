package Stream;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import Utils.BaseUrlResolver;
import dash.mpd.IAdaptationSet;
import dash.mpd.IMPD;
import dash.mpd.IPeriod;
import dash.mpd.IRepresentation;
import dash.mpd.ISegment;

public class SingleMediaSegmentStream extends AbstractRepresentationStream {
    public SingleMediaSegmentStream(IMPD mpd, IPeriod period,
                                    IAdaptationSet adaptationSet, IRepresentation representation) {
        super(mpd, period, adaptationSet, representation);

        this.baseUrls = BaseUrlResolver.resolveBaseUrl(mpd, period, adaptationSet, 0, 0, 0);
    }

    @Override
    public ISegment getInitializationSegment() {
        if(this.representation.getSegmentBase() != null) {

            if(representation.getSegmentBase().getInitialization() != null)
                representation.getSegmentBase().getInitialization().toSegment(baseUrls);
        }

        return null;
    }

    @Override
    public ISegment getIndexSegment(int segmentNumber) {
        if(this.representation.getSegmentBase() != null)
            if(this.representation.getSegmentBase().getRepresentationIndex() != null)
                return this.representation.getSegmentBase().getRepresentationIndex().toSegment(baseUrls);

        return null;
    }

    @Override
    public ISegment getMediaSegment(int segmentNumber) {
        if(this.representation.getBaseURLs().size() > segmentNumber)
            return this.representation.getBaseURLs().get(segmentNumber).toMediaSegment(baseUrls);

        return this.representation.getBaseURLs().get(0).toMediaSegment(baseUrls);
    }

    @Override
    public ISegment getBitstreamSwitchingSegment() {
        return null;
    }

    @Override
    public RepresentationStreamType getStreamType() {
        return RepresentationStreamType.SINGLE_SEGMENT;
    }

    @Override
    public int getFirstSegmentNumber() {
        return 0;
    }

    @Override
    public int getCurrentSegmentNumber() {
        return 0;
    }

    @Override
    public int getLastSegmentNumber() {
        return 0;
    }

    @Override
    public List<Integer> getSegmentStartTimes() {
        Integer[] res = {0};
        return Collections.unmodifiableList(Arrays.asList(res));
    }

    @Override
    public int getTimescale() {
        return 0;
    }

    @Override
    public int getSegmentDuration(int segmentNumber) {
        return 0;
    }

}
