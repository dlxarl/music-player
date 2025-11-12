package util;

import java.awt.*;
import java.io.InputStream;

public class FontUtils {
    public static Font loadCustomFont(String path, float size) {
        try {
            InputStream stream = FontUtils.class.getResourceAsStream(path);
            Font font = Font.createFont(Font.TRUETYPE_FONT, stream);
            return font.deriveFont(size);
        } catch (Exception e) {
            System.out.println("Unable to load font: " + e.getMessage());
            return new Font("SansSerif", Font.PLAIN, (int) size);
        }
    }
}