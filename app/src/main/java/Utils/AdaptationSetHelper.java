package Utils;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.util.Vector;

import java.util.stream.Collectors;
import dash.mpd.IAdaptationSet;
import dash.mpd.IPeriod;
import dash.mpd.IRepresentation;

public interface AdaptationSetHelper {
    @RequiresApi(api = Build.VERSION_CODES.N)
    public static Vector<IAdaptationSet> getAudioAdaptationSets(IPeriod period) {
        return period
                .getAdaptationSets()
                .parallelStream()
                .filter(p -> isAudioAdaptationSet(p))
                .collect(Collectors.toCollection(Vector::new));
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public static Vector<IAdaptationSet> getVideoAdaptationSets(IPeriod period) {
        return period
                .getAdaptationSets()
                .parallelStream()
                .filter(a -> isVideoAdaptationSet(a))
                .collect(Collectors.toCollection(Vector::new));
    }

    public static boolean isAudioAdaptationSet(IAdaptationSet adaptationSet) {
        return isContainedInMimeType(adaptationSet, "audio");
    }

    public static boolean isVideoAdaptationSet(IAdaptationSet adaptationSet) {
        return isContainedInMimeType(adaptationSet, "video");
    }

    public static boolean isContainedInMimeType(IAdaptationSet adaptationSet, String value) {
        if(adaptationSet.getMimeType() != null && !adaptationSet.getMimeType().isEmpty())
            if(adaptationSet.getMimeType().contains(value))
                return true;

        Vector<IRepresentation> reprs = adaptationSet.getRepresentation();
        for(IRepresentation r : reprs)
            if(r.getMimeType() != null && !r.getMimeType().isEmpty())
                if(r.getMimeType().contains(value))
                    return true;

        return false;
    }

}
