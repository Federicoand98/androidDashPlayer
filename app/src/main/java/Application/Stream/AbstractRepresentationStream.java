package Application.Stream;

import java.util.Vector;

import jLibdash.dash.mpd.IAdaptationSet;
import jLibdash.dash.mpd.IBaseUrl;
import jLibdash.dash.mpd.IMPD;
import jLibdash.dash.mpd.IPeriod;
import jLibdash.dash.mpd.IRepresentation;


public abstract class AbstractRepresentationStream implements RepresentationStream {
    protected Vector<IBaseUrl> baseUrls;
    protected IMPD mpd;
    protected IPeriod period;
    protected IAdaptationSet adaptationSet;
    protected IRepresentation representation;

    public AbstractRepresentationStream(IMPD mpd, IPeriod period, IAdaptationSet adaptationSet,
                                        IRepresentation representation) {
        this.mpd = mpd;
        this.period = period;
        this.adaptationSet = adaptationSet;
        this.representation = representation;
    }

    protected void setBaseUrls(Vector<IBaseUrl> baseUrls) {
        this.baseUrls = baseUrls;
    }

    @Override
    public int getFirstSegmentNumber() {
        if(this.mpd.getType().equals("dynamic"))
            throw new IllegalArgumentException("Live streaming not available yet!");

        return 0;
    }

    @Override
    public int getCurrentSegmentNumber() {
        if(this.mpd.getType().equals("dynamic"))
            throw new IllegalArgumentException("Live streaming not available yet!");

        return 0;
    }

    @Override
    public int getLastSegmentNumber() {
        if(this.mpd.getType().equals("dynamic"))
            throw new IllegalArgumentException("Live streaming not available yet!");

        return 0;
    }

    @Override
    public int getSize() {
        return Integer.MAX_VALUE - 1;
    }

    @Override
    public int getAverageSegmentDuration() {
        return 1;
    }

    @Override
    public IRepresentation getSourceRepresentation() {
        return this.representation;
    }
}
