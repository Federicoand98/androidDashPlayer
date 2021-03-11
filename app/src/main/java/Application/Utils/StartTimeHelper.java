package Application.Utils;

import java.util.Vector;

import jLibdash.dash.mpd.ISegmentList;
import jLibdash.dash.mpd.ISegmentTemplate;
import jLibdash.dash.mpd.ISegmentTimeline;
import jLibdash.dash.mpd.ITimeline;

public interface StartTimeHelper {

    public static Vector<Integer> getSegmentStartTimes(
            ISegmentTemplate segmentTemplate, long mediaPresentationDuration) {

        Vector<Integer> res = new Vector<Integer>();
        ISegmentTimeline segmentTimeline = segmentTemplate.getSegmentTimeline();

        if(segmentTimeline != null) {
            int segStartTime = 0;
            int segDuration = 0;
            int repeatCount = 0;

            Vector<ITimeline> timelines = segmentTimeline.getTimelines();
            for(ITimeline t : timelines) {
                repeatCount = t.getRepeatCount();
                segStartTime = t.getStartTime();
                segDuration = t.getDuration();

                if(repeatCount > 0) {
                    for(int i=0; i<=repeatCount; i++)
                        if(segStartTime > 0)
                            res.add(segStartTime + segDuration * i);
                        else
                            throw new IllegalArgumentException("SegmentTemplate.SegmentTimeline.S@t is not specified!");
                } else
                    res.add(segStartTime);
            }
        }
        else {
            int segments = (int) Math.ceil(mediaPresentationDuration / (segmentTemplate.getDuration() / (double)segmentTemplate.getTimescale()));
            int startTime = 0;
            for(int i=0; i<segments; i++, startTime += segmentTemplate.getDuration())
                res.add(startTime);
        }

        return res;

    }

    public static Vector<Integer> getSegmentStartTimes(
            ISegmentList segmentList) {

        Vector<Integer> res = new Vector<Integer>();
        int size = segmentList.getSegmentURLs().size();
        int startTime = 0;
        for(int i=0; i<size; i++, startTime += segmentList.getDuration())
            res.add(startTime);

        return res;
    }
}
