package util;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.InputStream;
// Припустимо, тут є імпорти для SVG-бібліотеки, наприклад:
// import com.kitfox.svg.SVGDiagram;
// import com.kitfox.svg.SVGUniverse;

public class ImageLoader {

    // Припустимо, тут є логіка для SVG (loadSvgIcon)
    // private static final SVGUniverse svgUniverse = new SVGUniverse();

    // public static ImageIcon loadSvgIcon(String resourcePath, int size) { ... }

    /**
     * Завантажує растрове зображення (PNG, JPG) з ресурсів і масштабує його.
     * @param resourcePath Шлях до зображення в ресурсах (наприклад, "/images/cover.png")
     * @param size Бажаний розмір (ширина та висота)
     * @return Масштабований ImageIcon
     */
    public static ImageIcon loadRasterImage(String resourcePath, int size) {
        try {
            // Використовуємо getClass().getResource() від самого класу ImageLoader
            ImageIcon icon = new ImageIcon(ImageLoader.class.getResource(resourcePath));

            // Масштабуємо зображення
            Image scaled = icon.getImage().getScaledInstance(size, size, Image.SCALE_SMOOTH);

            return new ImageIcon(scaled);
        } catch (Exception e) {
            System.out.println("Unable to load image: " + e.getMessage());
            return null;
        }
    }
}