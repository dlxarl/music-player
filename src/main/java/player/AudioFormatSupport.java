package player;

import java.util.Arrays;
import java.util.List;

public class AudioFormatSupport {
    private static final List<String> supportedFormats = Arrays.asList("mp3", "wav", "ogg", "aac");

    public static boolean isSupported(String extension) {
        return supportedFormats.contains(extension.toLowerCase());
    }
}