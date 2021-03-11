package Application.Stream;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Vector;

import Application.Utils.BaseUrlResolver;
import Application.Utils.MPDTimeResolver;
import Application.Utils.StartTimeHelper;
import jLibdash.dash.mpd.IAdaptationSet;
import jLibdash.dash.mpd.IMPD;
import jLibdash.dash.mpd.IPeriod;
import jLibdash.dash.mpd.IRepresentation;
import jLibdash.dash.mpd.ISegment;
import jLibdash.dash.mpd.ISegmentTemplate;

public class SegmentTemplateStream extends AbstractRepresentationStream {
    @RequiresApi(api = Build.VERSION_CODES.N)
    public static SegmentTemplateStream newSegmentTemplateStream(IMPD mpd, IPeriod period,
                                                                 IAdaptationSet adaptationSet, IRepresentation representation,
                                                                 Optional<ISegmentTemplate> inheritedSegmentTemplate,
                                                                 Optional<Vector<Integer>> segmentStartTimes) {

        if(inheritedSegmentTemplate.isPresent()) {
            ISegmentTemplate segmentTemplate = inheritedSegmentTemplate.get();
            if(segmentTemplate.getSegmentTimeline() != null)
                return new TimeBasedSegmentTemplateStream(mpd, period,
                        adaptationSet, representation,
                        segmentTemplate, segmentStartTimes);
            return new NumberBasedSegmentTemplateStream(mpd, period,
                    adaptationSet, representation,
                    segmentTemplate, segmentStartTimes);
        }

        ISegmentTemplate segmentTemplate = findSegmentTemplate(period, adaptationSet,
                representation);

        if(segmentTemplate.getSegmentTimeline() != null)
            return new TimeBasedSegmentTemplateStream(mpd, period, adaptationSet,
                    representation, segmentTemplate, Optional.empty());

        return new NumberBasedSegmentTemplateStream(mpd, period, adaptationSet,
                representation, segmentTemplate, Optional.empty());
    }

    private static ISegmentTemplate findSegmentTemplate(IPeriod period,
                                                        IAdaptationSet adaptationSet, IRepresentation representation) {
        if(representation.getSegmentTemplate() != null)
            return representation.getSegmentTemplate();

        return null;
    }

    protected ISegmentTemplate segmentTemplate;
    protected Vector<Integer> segmentStartTimes;

    @RequiresApi(api = Build.VERSION_CODES.O)
    protected SegmentTemplateStream(IMPD mpd, IPeriod period, IAdaptationSet adaptationSet,
                                    IRepresentation representation, ISegmentTemplate segmentTemplate,
                                    Optional<Vector<Integer>> segmentStartTimes) {
        super(mpd, period, adaptationSet, representation);
        this.segmentTemplate = segmentTemplate;
        this.baseUrls = BaseUrlResolver.resolveBaseUrl(mpd, period, adaptationSet, 0, 0, 0);
        this.segmentStartTimes = new Vector<Integer>();

        this.segmentStartTimes = segmentStartTimes.orElse(StartTimeHelper.getSegmentStartTimes(segmentTemplate,
                MPDTimeResolver.parseISO8601Duration(
                        this.mpd.getMediaPresentationDuration())
                        .getSeconds()));
    }

    @Override
    public ISegment getInitializationSegment() {
        if(this.segmentTemplate.getInitialization() != null)
            return this.segmentTemplate.getInitialization().toSegment(baseUrls);

        return segmentTemplate.toInitializationSegment(baseUrls, representation.getId(), representation.getBandwidth());
    }

    @Override
    public ISegment getIndexSegment(int segmentNumber) {
        return null;
    }

    @Override
    public ISegment getMediaSegment(int segmentNumber) {
        return null;
    }

    @Override
    public ISegment getBitstreamSwitchingSegment() {
        if(this.segmentTemplate.getBitstreamSwitching() != null)
            return this.segmentTemplate.getBitstreamSwitching().toSegment(baseUrls);

        return this.segmentTemplate.toBitstreamSwitchingSegment(baseUrls, representation.getId(), representation.getBandwidth());
    }

    @Override
    public RepresentationStreamType getStreamType() {
        return RepresentationStreamType.SEGMENT_TEMPLATE;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public int getSize() {
        if(!segmentStartTimes.isEmpty())
            return this.segmentStartTimes.size();

        double mediaPresentationDuration = 0;

        if(this.mpd.getType().equals("static")) {
            mediaPresentationDuration = MPDTimeResolver.parseISO8601Duration(this.mpd.getMediaPresentationDuration()).getSeconds();
            return (int) Math.ceil(mediaPresentationDuration / (this.segmentTemplate.getDuration() / (double)this.segmentTemplate.getTimescale()));
        }

        return 0;
    }

    @Override
    public List<Integer> getSegmentStartTimes() {
        return Collections.unmodifiableList(this.segmentStartTimes);
    }

    @Override
    public int getSegmentDuration(int segmentNumber) {
        return 0;
    }

    @Override
    public int getTimescale() {
        return segmentTemplate.getTimescale();
    }

}
