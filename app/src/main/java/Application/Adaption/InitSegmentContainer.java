package Application.Adaption;

import java.io.IOException;

import Application.Network.DataSegment;
import Application.Stream.AdaptationSetStream;
import Application.Stream.RepresentationStream;
import jLibdash.dash.mpd.IRepresentation;

public interface InitSegmentContainer {
    public void reset(AdaptationSetStream adaptationSetStream) throws IOException;
    public void reset();
    public DataSegment obtainFor(IRepresentation representation);
    public DataSegment downloadInitFor(RepresentationStream stream) throws IOException;
    public void register(IRepresentation representation, DataSegment segment);
    public DataSegment downloadInitForAndRegister(RepresentationStream stream) throws IOException;
}
