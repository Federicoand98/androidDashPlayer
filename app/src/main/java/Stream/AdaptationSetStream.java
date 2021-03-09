package Stream;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.Vector;

import Utils.MPDTimeResolver;
import Utils.StartTimeHelper;
import dash.mpd.IAdaptationSet;
import dash.mpd.IMPD;
import dash.mpd.IPeriod;
import dash.mpd.IRepresentation;
import dash.mpd.ISegmentList;
import dash.mpd.ISegmentTemplate;
import dash.mpd.ITimeline;

public class AdaptationSetStream {
    private Map<IRepresentation, RepresentationStream> representations;

    private IMPD mpd;
    private IPeriod period;
    private IAdaptationSet adaptationSet;

    private ISegmentTemplate segmentTemplate;
    private ISegmentList segmentList;
    private Vector<Integer> segmentStartTimes;

    public AdaptationSetStream(IMPD mpd, IPeriod period, IAdaptationSet adSet) {
        this.mpd = mpd;
        this.period = period;
        this.adaptationSet = adaptationSet;
        this.segmentStartTimes = null;

        this.segmentList = findSegmentList();
        if(this.segmentList != null)
            this.segmentStartTimes = StartTimeHelper.getSegmentStartTimes(segmentList);

        this.segmentTemplate = findSegmentTemplate();
        if(this.segmentTemplate != null)
            this.segmentStartTimes = StartTimeHelper.getSegmentStartTimes(segmentTemplate,
                    MPDTimeResolver.parseISO8601Duration(
                            this.mpd.getMediaPresentationDuration())
                            .getSeconds());

        this.representations = new HashMap<IRepresentation, RepresentationStream>();
        init();
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void AdaptationSetStream(IMPD mpd, IPeriod period,
                                    IAdaptationSet adaptationSet) {
        this.mpd = mpd;
        this.period = period;
        this.adaptationSet = adaptationSet;
        this.segmentStartTimes = null;

        this.segmentList = findSegmentList();
        if(this.segmentList != null)
            this.segmentStartTimes = StartTimeHelper.getSegmentStartTimes(segmentList);

        this.segmentTemplate = findSegmentTemplate();
        if(this.segmentTemplate != null)
            this.segmentStartTimes = StartTimeHelper.getSegmentStartTimes(segmentTemplate,
                    MPDTimeResolver.parseISO8601Duration(
                            this.mpd.getMediaPresentationDuration())
                            .getSeconds());

        this.representations = new HashMap<IRepresentation, RepresentationStream>();
        init();
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void init() {
        Vector<IRepresentation> representations = adaptationSet.getRepresentation();

        for(IRepresentation r : representations) {
            this.representations.put(r,
                    RepresentationStreamFactory
                            .create(determinateRepresentationStreamType(r),
                                    mpd, period, adaptationSet, r,
                                    Optional.ofNullable(segmentList),
                                    Optional.ofNullable(segmentTemplate),
                                    Optional.ofNullable(segmentStartTimes)));
        }
    }

    private RepresentationStreamType determinateRepresentationStreamType
            (IRepresentation representation) {

        if(representation.getSegmentList() != null)
            return RepresentationStreamType.SEGMENT_LIST;

        if(representation.getSegmentTemplate() != null)
            return RepresentationStreamType.SEGMENT_TEMPLATE;

        if(representation.getSegmentBase()!= null || representation.getBaseURLs().size() > 0)
            return RepresentationStreamType.SINGLE_SEGMENT;

        if(this.segmentTemplate != null)
            return RepresentationStreamType.SEGMENT_TEMPLATE;

        if(this.segmentList != null)
            return RepresentationStreamType.SEGMENT_LIST;

        if(adaptationSet.getSegmentBase() != null)
            return RepresentationStreamType.SINGLE_SEGMENT;

        if(period.getSegmentBase() != null)
            return RepresentationStreamType.SINGLE_SEGMENT;

        return RepresentationStreamType.UNDEFINED;

    }

    public RepresentationStream getRepresentationStream(IRepresentation representation) {
        return this.representations.get(representation);
    }

    public Set<IRepresentation> availableRepresentations() {
        return representations.keySet();
    }

    public int getTimescale() {
        if(this.segmentTemplate != null)
            return segmentTemplate.getTimescale();

        if(this.segmentList != null)
            return segmentList.getTimescale();

        return -1;
    }

    public int getDuration() {
        if(this.segmentTemplate != null)
            return segmentTemplate.getDuration();

        if(this.segmentList != null)
            return segmentList.getDuration();

        return -1;
    }

    public List<Integer> getSegmentStartTimes() {
        return Collections.unmodifiableList(this.segmentStartTimes);
    }

    public int getSegmentStartTime(int segmentNumber) {
        if(segmentNumber > this.segmentStartTimes.size())
            return -1;

        return this.segmentStartTimes.get(segmentNumber);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public int getSegmentDuration(int segmentNumber) {
        if(segmentNumber > this.segmentStartTimes.size())
            return -1;

        if(segmentList != null)
            return segmentList.getDuration();

        if(segmentTemplate != null)
            if(segmentTemplate.getSegmentTimeline() != null) {
                Vector<ITimeline> timelines =
                        this.segmentTemplate.getSegmentTimeline().getTimelines();
                ITimeline timeline = null;

                int index = 0;
                for(int i=0; i<segmentNumber; index++) {
                    timeline = timelines.get(index);
                    if(timeline.getRepeatCount() > 0)
                        i += timeline.getRepeatCount();
                    else
                        i++;
                }
                index--;

                return this.segmentTemplate.getSegmentTimeline()
                        .getTimelines().get(index).getDuration();
            } else {
                if(segmentNumber == segmentStartTimes.size() - 1)
                    return (int) ((MPDTimeResolver.parseISO8601Duration(
                            mpd.getMediaPresentationDuration()).getSeconds()*
                            segmentTemplate.getTimescale()) -
                            segmentStartTimes.lastElement());
                return segmentTemplate.getDuration();
            }

        return -1;
    }

    public int getSize() {
        return this.segmentStartTimes.size();
    }

    private ISegmentTemplate findSegmentTemplate() {
        if(adaptationSet.getSegmentTemplate() != null)
            return adaptationSet.getSegmentTemplate();

        if(period.getSegmentTemplate() != null)
            return period.getSegmentTemplate();

        return null;
    }

    private ISegmentList findSegmentList() {
        if(this.adaptationSet.getSegmentList() != null)
            return this.adaptationSet.getSegmentList();

        if(this.period.getSegmentList() != null)
            return this.period.getSegmentList();

        return null;
    }
}
