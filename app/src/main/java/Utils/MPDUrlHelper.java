package Utils;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

public interface MPDUrlHelper {

    @RequiresApi(api = Build.VERSION_CODES.O)
    public static List<String> getAvailableMPDURL(String file) throws IOException {
        return Files.lines(Paths.get(file))
                .filter(l -> !l.trim().isEmpty() && !l.startsWith("#!"))
                .map(String::trim)
                .collect(Collectors.toList());
    }
}
