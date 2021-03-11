package Application.Adaption;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import Application.Network.DataSegment;
import Application.Stream.AdaptationSetStream;
import Application.Stream.RepresentationStream;
import jLibdash.dash.mpd.IRepresentation;
import jLibdash.dash.mpd.ISegment;

public class HashInitSegmentContainer implements InitSegmentContainer {

    private static InitSegmentContainer instance = null;
    public static InitSegmentContainer getInitSegmentContainer() {
        if(instance == null)
            instance = new HashInitSegmentContainer();

        return instance;
    }

    private Map<IRepresentation, DataSegment> oldInitSegments;
    private Map<IRepresentation, DataSegment> initSegments;
    private InfoPoint infoPoint;
    //private Logger logger;

    private HashInitSegmentContainer() {
        initSegments = new HashMap<IRepresentation, DataSegment>();
        this.infoPoint = InfoPoint.getInfoPoint();
        //this.logger = LoggerManager.getLogger();
    }

    @Override
    public void reset(AdaptationSetStream adaptationSetStream) throws IOException {
        oldInitSegments = initSegments;
        initSegments = new HashMap<IRepresentation, DataSegment>();

        IRepresentation representation = null;
        URLConnection connection = null;
        DataSegment dataSegment = null;
        InputStream input = null;
        long startTime = 0, elapsed = 0;
        int bytes, readed;
        Iterator<IRepresentation> it = adaptationSetStream.availableRepresentations().iterator();

        while(it.hasNext()) {
            representation = it.next();

            dataSegment = new DataSegment(adaptationSetStream
                    .getRepresentationStream(representation)
                    .getInitializationSegment(),
                    representation, 0, 0, 0);

            connection = new URL(dataSegment.getSegment().getAbsoluteURI())
                    .openConnection();
            bytes = connection.getContentLength();
            dataSegment.alloc(bytes);
            input = connection.getInputStream();

            startTime = System.nanoTime();
            readed = input.read(dataSegment.getData());
            elapsed = System.nanoTime() - startTime;

            /*
            logger.store(LogLineFactory.istantlyCreate(EventType.INFORMATION,
                    this.getClass().getSimpleName(),
                    "Downloaded " + readed + " bytes from " + dataSegment.getSegment().getAbsoluteURI()));


            if(input.read() > 0)
                logger.store(LogLineFactory.istantlyCreate(EventType.UNEXPECTED,
                        this.getClass().getName(),
                        "Download from " + dataSegment.getSegment().getAbsoluteURI() + " not terminated."));
             */
            input.close();

            infoPoint.updateDownloadSpeed((readed * 8) / (elapsed / 1000000000.0));

            initSegments.put(representation, dataSegment);
        }

    }

    @Override
    public DataSegment obtainFor(IRepresentation representation) {
        DataSegment res = initSegments.get(representation);
        if(res == null)
            res = oldInitSegments.get(representation);

        return res;
    }

    public DataSegment downloadInitFor(RepresentationStream stream) throws IOException {
        ISegment segment = stream.getInitializationSegment();

        DataSegment res = new DataSegment(
                segment, stream.getSourceRepresentation(), 0, 0, 0);
        InputStream input = (new URL(segment.getAbsoluteURI()).openStream());

        long startTime = System.nanoTime();
        int readed = input.read(res.getData());
        long elapsed = System.nanoTime() - startTime;

        infoPoint.updateDownloadSpeed((readed * 8) / (elapsed / 1000000000.0));

        /*
        logger.store(LogLineFactory.istantlyCreate(EventType.INFORMATION,
                this.getClass().getSimpleName(),
                "Downloaded " + readed + " bytes from " + res.getSegment().getAbsoluteURI()));
         */
        return res;
    }

    @Override
    public void register(IRepresentation representation, DataSegment segment) {
        this.initSegments.put(representation, segment);
    }

    @Override
    public DataSegment downloadInitForAndRegister(RepresentationStream stream) throws IOException {
        ISegment segment = stream.getInitializationSegment();

        DataSegment res = new DataSegment(
                segment, stream.getSourceRepresentation(), 0, 0, 0);
        InputStream input = (new URL(segment.getAbsoluteURI()).openStream());

        long startTime = System.nanoTime();
        int readed = input.read(res.getData());
        long elapsed = System.nanoTime() - startTime;

        infoPoint.updateDownloadSpeed((readed * 8) / (elapsed / 1000000000.0));
        /*
        logger.store(LogLineFactory.istantlyCreate(EventType.INFORMATION,
                this.getClass().getSimpleName(),
                "Downloaded " + readed + " bytes from " + res.getSegment().getAbsoluteURI()));
         */
        this.initSegments.put(stream.getSourceRepresentation(), res);
        return res;
    }

    @Override
    public void reset() {
        oldInitSegments = initSegments;

        this.initSegments.clear();

    }
}
