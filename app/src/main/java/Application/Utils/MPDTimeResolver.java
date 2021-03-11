package Application.Utils;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.time.Duration;
import java.time.Period;

public interface MPDTimeResolver {

    @RequiresApi(api = Build.VERSION_CODES.O)
    public static Duration parseISO8601Duration(String time) {
        if(time == null)
            return null;

        if(time.startsWith("PT"))
            return Duration.parse(time);

        return Duration.parse("PT" + time.split("T")[1]);

    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public static Period parseISO8601Period(String time) {
        if(time == null)
            return null;

        if(!time.contains("T"))
            return Period.parse(time);

        return Period.parse(time.split("T")[0]);

    }
}
