package ui;

import util.FontUtils;

import javax.swing.*;
import java.awt.*;

public class ProgressBar extends JPanel {
    private int currentSeconds = 0;
    private int totalSeconds = 0;

    public ProgressBar() {
        setPreferredSize(new Dimension(500, 50));
        setOpaque(false);
    }

    public void setProgress(int current, int total) {
        this.currentSeconds = current;
        this.totalSeconds = total;
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        int barHeight = 6;
        int knobSize = 12;
        int topMargin = 10;
        int barY = topMargin + 20;
        int barX = 0;
        int barWidth = getWidth();

        // Timers above bar
        g2.setFont(FontUtils.loadCustomFont("/fonts/Montserrat-Medium.ttf", 12f));
        g2.setColor(new Color(110, 110, 110));
        g2.drawString(formatTime(currentSeconds), barX + 4, topMargin);
        g2.drawString(formatTime(totalSeconds), barX + barWidth - 40, topMargin);

        // Background bar
        g2.setColor(new Color(60, 60, 60));
        g2.fillRoundRect(barX, barY, barWidth, barHeight, barHeight, barHeight);

        // Filled portion
        int filledWidth = totalSeconds > 0 ? (int) ((double) currentSeconds / totalSeconds * barWidth) : 0;
        g2.setColor(Color.WHITE);
        g2.fillRoundRect(barX, barY, filledWidth, barHeight, barHeight, barHeight);

        // Knob
        int knobX = filledWidth - knobSize / 2;
        g2.fillOval(knobX, barY - 3, knobSize, knobSize);

        g2.dispose();
    }

    private String formatTime(int sec) {
        int m = sec / 60;
        int s = sec % 60;
        return String.format("%02d:%02d", m, s);
    }
}