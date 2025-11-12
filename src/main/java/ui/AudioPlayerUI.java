package ui;

import player.AudioPlayer;
import player.Playlist;
import util.FileUtils;
import util.FontUtils;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.io.File;

public class AudioPlayerUI extends JFrame {
    private final Playlist playlist = new Playlist();
    private final AudioPlayer player = new AudioPlayer(playlist);

    private final DefaultListModel<String> listModel = new DefaultListModel<>();
    private final JList<String> trackList = new JList<>(listModel);
    private final JProgressBar progressBar = new JProgressBar();
    private final JLabel currentTimeLabel = new JLabel("00:30");
    private final JLabel totalTimeLabel = new JLabel("03:20");

    public AudioPlayerUI() {
        setTitle("Music Player");
        setSize(400, 350);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Main panel
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(new Color(28, 28, 28));
        mainPanel.setBorder(new EmptyBorder(20, 20, 20, 20));
        add(mainPanel);

        // Top content panel (vertical layout)
        JPanel topContent = new JPanel();
        topContent.setLayout(new BoxLayout(topContent, BoxLayout.Y_AXIS));
        topContent.setOpaque(false);

        // Cover image
        JLabel coverLabel = new JLabel();
        coverLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        coverLabel.setPreferredSize(new Dimension(70, 70));
        coverLabel.setIcon(loadImage("/images/nxrt-december-256.png", 70));
        topContent.add(coverLabel);

        // Spacing
        topContent.add(Box.createVerticalStrut(8));

        // Title and artist labels
        JLabel titleLabel = new JLabel("december");
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setFont(FontUtils.loadCustomFont("/fonts/Montserrat-Medium.ttf", 18f));
        titleLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

        JLabel artistLabel = new JLabel("nxrt");
        artistLabel.setForeground(new Color(110, 110, 110));
        artistLabel.setFont(FontUtils.loadCustomFont("/fonts/Montserrat-Medium.ttf", 16f));
        artistLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

        topContent.add(Box.createVerticalStrut(15));

        topContent.add(titleLabel);
        topContent.add(Box.createVerticalStrut(4));
        topContent.add(artistLabel);

        // Icon panel (top-right)
        JPanel iconPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 0, 0));
        iconPanel.setOpaque(false);

        // Завантаження іконки з масштабуванням
        ImageIcon folderIcon = loadImage("/icons/512x512/folder.png", 20);

// Кнопка з іконкою
        JButton iconBtn = new JButton(folderIcon);
        iconBtn.setPreferredSize(new Dimension(24, 24)); // трішки більше для відступів
        iconBtn.setBackground(new Color(40, 40, 40));
        iconBtn.setBorderPainted(false);
        iconBtn.setFocusPainted(false);
        iconBtn.setContentAreaFilled(false); // щоб фон не затирав іконку

        iconPanel.add(iconBtn);

        // Wrapper panel for top section
        JPanel topWrapper = new JPanel(new BorderLayout());
        topWrapper.setOpaque(false);
        topWrapper.add(topContent, BorderLayout.WEST);
        topWrapper.add(iconPanel, BorderLayout.EAST);

        mainPanel.add(topWrapper, BorderLayout.NORTH);

        // Progress bar
        ProgressBar progressBar = new ProgressBar();
        mainPanel.add(progressBar, BorderLayout.SOUTH);

        progressBar.setProgress(player.getCurrentSeconds(), player.getTotalSeconds());

        setVisible(true);
    }

    private void openFileChooser() {
        JFileChooser chooser = new JFileChooser();
        chooser.setMultiSelectionEnabled(true);
        chooser.setFileFilter(new FileNameExtensionFilter("Audio Files", "mp3", "wav"));
        if (chooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            for (File file : chooser.getSelectedFiles()) {
                String name = file.getName().toLowerCase();
                if (name.endsWith(".mp3") || name.endsWith(".wav")) {
                    playlist.add(file.getAbsolutePath());
                    listModel.addElement(file.getName());
                } else {
                    JOptionPane.showMessageDialog(this, "Unsupported format: " + name);
                }
            }
        }
    }

    private void updateProgress() {
        if (player.isPlaying()) {
            int current = player.getCurrentSeconds();
            int total = player.getTotalSeconds();
            progressBar.setMaximum(total);
            progressBar.setValue(current);
            currentTimeLabel.setText(FileUtils.formatTime(current));
            totalTimeLabel.setText(FileUtils.formatTime(total));
        }
    }

    private ImageIcon loadImage(String resourcePath, int size) {
        try {
            ImageIcon icon = new ImageIcon(getClass().getResource(resourcePath));
            Image scaled = icon.getImage().getScaledInstance(size, size, Image.SCALE_SMOOTH);
            return new ImageIcon(scaled);
        } catch (Exception e) {
            System.out.println("Unable to load image: " + e.getMessage());
            return null;
        }
    }
}