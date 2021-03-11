package Application.Utils;

import android.os.Build;

import androidx.annotation.RequiresApi;

import org.dom4j.DocumentException;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import Application.Stream.AdaptationSetStream;
import jLibdash.dash.manager.DASHManager;
import jLibdash.dash.mpd.IAdaptationSet;
import jLibdash.dash.mpd.IMPD;
import jLibdash.dash.mpd.IPeriod;
import jLibdash.dash.mpd.IRepresentation;
import jLibdash.dash.mpd.ISegment;

public class TesterDownloader {

    public TesterDownloader(String av) {
        new Thread(new Runnable() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void run() {
                try {
                    System.out.println("Inizio...");

                    IMPD mpd = DASHManager.newDASHManager().open("https://bitmovin-a.akamaihd.net/content/MI201109210084_1/mpds/f08e80da-bf1d-4e3d-8899-f0f6155f6efa.mpd");
                    IPeriod period = mpd.getPeriods().get(0);

                    if(av.equals("audio")) {
                        System.out.println("Inside audio");

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
                        System.out.println("Inside Video");

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
                } catch (IOException | DocumentException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
