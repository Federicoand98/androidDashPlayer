package Application.Utils;

public class TesterDownloader {

    public TesterDownloader() {

    }

    /*
    @RequiresApi(api = Build.VERSION_CODES.O)
    public void get(String av) throws IOException {
        IMPD mpd = DASHManager.newDASHManager().open(
                "https://bitmovin-a.akamaihd.net/content/MI201109210084_1/mpds/f08e80da-bf1d-4e3d-8899-f0f6155f6efa.mpd");
        IPeriod period = mpd.getPeriods().get(0);

        if(av.equals("audio")) {

            IAdaptationSet adSet = AdaptationSetHelper.getAudioAdaptationSets(period).get(0);
            IRepresentation rep = adSet.getRepresentation().get(0);
            AdaptationSetStream adStream = new AdaptationSetStream(mpd, period, adSet);
            System.out.println("Chosen representation: " + rep);
            ISegment init = adStream
                    .getRepresentationStream(rep)
                    .getInitializationSegment();
            ISegment seg0 = adStream.getRepresentationStream(rep).getMediaSegment(0);
            ISegment seg = null;
            URLConnection conn = null;
            InputStream in = null;
            byte[] buf = null;
            Path f_out = null;
            for(int i=0; i<2; i++) {
                seg = adStream.getRepresentationStream(rep).getMediaSegment(i);
                conn  = new URL(seg.getAbsoluteURI()).openConnection();
                in = conn.getInputStream();
                f_out = Paths.get("src/main/res/seg/seg_audio_" + (i+1));
                Files.deleteIfExists(f_out);
                Files.createFile(f_out);
                Files.write(f_out, IOUtils.toByteArray(in));
                System.out.println("Downloaded Segment " + i);
            }
            conn = new URL(init.getAbsoluteURI()).openConnection();
            in = conn.getInputStream();
            f_out = Paths.get("src/main/res/seg/seg_audio_0");
            Files.createFile(f_out);
            Files.write(f_out, IOUtils.toByteArray(in));
        } else if(av.equals("video")) {

            IAdaptationSet adSet = AdaptationSetHelper.getVideoAdaptationSets(period).get(0);
            IRepresentation rep = adSet.getRepresentation().get(0);
            AdaptationSetStream adStream = new AdaptationSetStream(mpd, period, adSet);
            System.out.println("Chosen representation: " + rep);
            ISegment init = adStream.getRepresentationStream(rep).getInitializationSegment();
            ISegment seg0 = adStream.getRepresentationStream(rep).getMediaSegment(0);
            ISegment seg = null;
            URLConnection conn = null;
            InputStream in = null;
            byte[] buf = null;
            Path f_out = null;
            for(int i=0; i<2; i++) {
                seg = adStream.getRepresentationStream(rep).getMediaSegment(i);
                conn  = new URL(seg.getAbsoluteURI()).openConnection();
                in = conn.getInputStream();
                f_out = Paths.get("src/main/res/seg/seg_video_" + (i+1));
                Files.deleteIfExists(f_out);
                Files.createFile(f_out);
                Files.write(f_out, IOUtils.toByteArray(in));
                System.out.println("Downloaded Segment " + i);
            }
            conn = new URL(init.getAbsoluteURI()).openConnection();
            in = conn.getInputStream();
            f_out = Paths.get("src/main/res/seg/seg_video_0");
            Files.createFile(f_out);
            Files.write(f_out, IOUtils.toByteArray(in));
        }
    }

     */
}
