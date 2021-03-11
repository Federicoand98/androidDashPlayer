package Application.Stream;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Vector;

import Application.Utils.BaseUrlResolver;
import Application.Utils.StartTimeHelper;
import jLibdash.dash.mpd.IAdaptationSet;
import jLibdash.dash.mpd.IMPD;
import jLibdash.dash.mpd.IPeriod;
import jLibdash.dash.mpd.IRepresentation;
import jLibdash.dash.mpd.ISegment;
import jLibdash.dash.mpd.ISegmentList;

public class SegmentListStream extends AbstractRepresentationStream {
    private ISegmentList segmentList;
    private Vector<Integer> segmentStartTimes;

    @RequiresApi(api = Build.VERSION_CODES.N)
    public SegmentListStream(IMPD mpd, IPeriod period, IAdaptationSet adaptationSet,
                             IRepresentation representation, Optional<ISegmentList> segmentList,
                             Optional<Vector<Integer>> segmentStartTimes) {
        super(mpd, period, adaptationSet, representation);

        this.baseUrls = BaseUrlResolver.resolveBaseUrl(mpd, period, adaptationSet, 0, 0, 0);
        this.segmentList = segmentList.orElse(this.representation.getSegmentList());
        this.segmentStartTimes = segmentStartTimes.orElse(
                StartTimeHelper.getSegmentStartTimes(this.segmentList));
    }

    @Override
    public ISegment getInitializationSegment() {
        if(this.segmentList.getInitialization() != null)
            return this.segmentList.getInitialization().toSegment(baseUrls);

        return null;
    }

    @Override
    public ISegment getIndexSegment(int segmentNumber) {
        if(this.segmentList.getSegmentURLs().size() > segmentNumber)
            return this.segmentList.getSegmentURLs().get(segmentNumber)
                    .toIndexSegment(baseUrls);

        return null;
    }

    @Override
    public ISegment getMediaSegment(int segmentNumber) {
        if(this.segmentList.getSegmentURLs().size() > segmentNumber)
            return this.segmentList.getSegmentURLs().get(segmentNumber)
                    .toMediaSegment(baseUrls);

        return null;
    }

    @Override
    public ISegment getBitstreamSwitchingSegment() {
        if(this.segmentList.getBitstreamSwitching() != null)
            return this.segmentList.getBitstreamSwitching().toSegment(baseUrls);

        return null;
    }

    @Override
    public RepresentationStreamType getStreamType() {
        return RepresentationStreamType.SEGMENT_LIST;
    }

    @Override
    public int getSize() {
        return segmentList.getSegmentURLs().size();
    }

    @Override
    public int getAverageSegmentDuration() {
        return this.segmentList.getDuration();
    }

    @Override
    public List<Integer> getSegmentStartTimes() {
        if(segmentStartTimes.isEmpty()) {
            int size = getSize();
            int startTime = 0;
            for(int i=0; i<size; i++, startTime += segmentList.getDuration())
                segmentStartTimes.add(startTime);
        }

        return Collections.unmodifiableList(segmentStartTimes);
    }

    @Override
    public int getTimescale() {
        return segmentList.getTimescale();
    }

    @Override
    public int getSegmentDuration(int segmentNumber) {
        return this.segmentList.getDuration();
    }
}
