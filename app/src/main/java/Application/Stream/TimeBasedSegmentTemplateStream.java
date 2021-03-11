package Application.Stream;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.util.Optional;
import java.util.Vector;

import jLibdash.dash.mpd.IAdaptationSet;
import jLibdash.dash.mpd.IMPD;
import jLibdash.dash.mpd.IPeriod;
import jLibdash.dash.mpd.IRepresentation;
import jLibdash.dash.mpd.ISegment;
import jLibdash.dash.mpd.ISegmentTemplate;
import jLibdash.dash.mpd.ITimeline;


public class TimeBasedSegmentTemplateStream extends SegmentTemplateStream {

    public TimeBasedSegmentTemplateStream(IMPD mpd, IPeriod period, IAdaptationSet adaptationSet,
                                          IRepresentation representation, ISegmentTemplate segmentTemplate,
                                          Optional<Vector<Integer>> segmentStartTimes) {
        super(mpd, period, adaptationSet, representation, segmentTemplate,
                segmentStartTimes);
    }

    @Override
    public ISegment getIndexSegment(int segmentNumber) {
        if(this.segmentStartTimes.size() > segmentNumber)
            return this.segmentTemplate.getIndexSegmentFromTime(baseUrls,
                    representation.getId(), representation.getBandwidth(),
                    this.segmentStartTimes.get(segmentNumber));

        return null;
    }

    @Override
    public ISegment getMediaSegment(int segmentNumber) {
        if(this.segmentStartTimes.size() > segmentNumber)
            return this.segmentTemplate.getMediaSegmentFromTime(baseUrls,
                    representation.getId(), representation.getBandwidth(),
                    this.segmentStartTimes.get(segmentNumber));

        return null;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public int getAverageSegmentDuration() {
        return (int) this.segmentTemplate.getSegmentTimeline().getTimelines()
                .stream()
                .mapToInt(ITimeline::getDuration)
                .average().orElse(0);
    }

    @Override
    public int getSegmentDuration(int segmentNumber) {
        if(segmentNumber > this.segmentStartTimes.size())
            return -1;

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
    }

}
