package ui;

import player.AudioPlayer;
import player.Playlist;
import util.FileUtils;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.io.File;

public class AudioPlayerUI extends JFrame {
    private final Playlist playlist = new Playlist();
    private final AudioPlayer player = new AudioPlayer(playlist);

    private final DefaultListModel<String> listModel = new DefaultListModel<>();
    private final JList<String> trackList = new JList<>(listModel);
    private final JProgressBar progressBar = new JProgressBar();
    private final JLabel currentTimeLabel = new JLabel("00:00");
    private final JLabel totalTimeLabel = new JLabel("00:00");

    public AudioPlayerUI() {
        setTitle("Music Player");
        setSize(600, 250);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Верхня панель: прогрес-бар + таймер
        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.add(progressBar, BorderLayout.NORTH);

        JPanel timePanel = new JPanel(new BorderLayout());
        timePanel.add(currentTimeLabel, BorderLayout.WEST);
        timePanel.add(totalTimeLabel, BorderLayout.EAST);
        topPanel.add(timePanel, BorderLayout.SOUTH);

        add(topPanel, BorderLayout.NORTH);

        // Плейлист + кнопка Add...
        trackList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        trackList.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                int index = trackList.getSelectedIndex();
                player.play(index);
            }
        });

        JPanel playlistPanel = new JPanel(new BorderLayout());

        JScrollPane scrollPane = new JScrollPane(trackList);
        scrollPane.setPreferredSize(new Dimension(200, 150));
        playlistPanel.add(scrollPane, BorderLayout.CENTER);

        JButton addBtn = new JButton("Add...");
        addBtn.addActionListener(e -> openFileChooser());
        playlistPanel.add(addBtn, BorderLayout.SOUTH);

        add(playlistPanel, BorderLayout.WEST);

        // Кнопки керування
        JButton playBtn = new JButton("▶");
        JButton pauseBtn = new JButton("⏸");
        JButton nextBtn = new JButton("⏭");
        JButton prevBtn = new JButton("⏮");

        playBtn.addActionListener(e -> player.resume());
        pauseBtn.addActionListener(e -> player.pause());
        nextBtn.addActionListener(e -> player.next());
        prevBtn.addActionListener(e -> player.previous());

        JPanel controlPanel = new JPanel(new FlowLayout());
        controlPanel.add(prevBtn);
        controlPanel.add(playBtn);
        controlPanel.add(pauseBtn);
        controlPanel.add(nextBtn);

        add(controlPanel, BorderLayout.CENTER);

        // Таймер оновлення
        Timer timer = new Timer(1000, e -> updateProgress());
        timer.start();

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
                    JOptionPane.showMessageDialog(this, "Непідтримуваний формат: " + name);
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
}