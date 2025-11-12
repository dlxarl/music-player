package util;

import com.kitfox.svg.SVGDiagram;
import com.kitfox.svg.SVGUniverse;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.InputStream;
import java.net.URI;

public class IconLoader {
    private static final SVGUniverse svgUniverse = new SVGUniverse();

    /**
     * Завантажує SVG-іконку з ресурсів і повертає її як ImageIcon заданого розміру.
     *
     * @param resourcePath Шлях до SVG-файлу у ресурсах (наприклад, "/icons/svg/folder.svg")
     * @param size         Бажаний розмір іконки (ширина та висота)
     * @return ImageIcon або null, якщо не вдалося завантажити
     */
    public static ImageIcon loadSvgIcon(String resourcePath, int size) {
        try (InputStream is = IconLoader.class.getResourceAsStream(resourcePath)) {
            if (is == null) {
                System.err.println("❌ SVG Resource not found: " + resourcePath);
                return null;
            }

            // Завантаження SVG у SVGUniverse
            URI uri = svgUniverse.loadSVG(is, resourcePath);
            SVGDiagram diagram = svgUniverse.getDiagram(uri);

            if (diagram == null) {
                System.err.println("❌ Could not create SVG diagram from: " + resourcePath);
                return null;
            }

            // Встановлення розміру вʼюпорту
            diagram.setDeviceViewport(new Rectangle(0, 0, size, size));

            // Рендеринг у BufferedImage
            BufferedImage image = new BufferedImage(size, size, BufferedImage.TYPE_INT_ARGB);
            Graphics2D g = image.createGraphics();
            g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
            g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BICUBIC);

            diagram.render(g);
            g.dispose();

            return new ImageIcon(image);

        } catch (Exception e) {
            System.err.println("❌ Error loading SVG icon: " + resourcePath);
            e.printStackTrace();
            return null;
        }
    }
}