package player;

import javazoom.jlgui.basicplayer.BasicPlayer;
import javazoom.jlgui.basicplayer.BasicPlayerListener;
import javazoom.jlgui.basicplayer.BasicPlayerEvent;
import javazoom.jlgui.basicplayer.BasicController;

import java.io.File;
import java.util.Map;

public class AudioPlayer implements BasicPlayerListener {
    private final Playlist playlist;
    private int currentIndex = 0;
    private BasicPlayer player = new BasicPlayer();

    private int currentSec = 0;
    private int totalSec = 0;
    private boolean playing = false;

    public AudioPlayer(Playlist playlist) {
        this.playlist = playlist;
        player.addBasicPlayerListener(this);
    }

    public void play(int index) {
        stop();
        currentIndex = index;
        play();
    }

    public void play() {
        if (playlist.isEmpty()) return;
        try {
            File file = new File(playlist.get(currentIndex));
            player.open(file);
            player.play();
            playing = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void pause() {
        try {
            player.pause();
            playing = false;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void resume() {
        try {
            player.resume();
            playing = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void stop() {
        try {
            player.stop();
            playing = false;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void next() {
        currentIndex = (currentIndex + 1) % playlist.size();
        play();
    }

    public void previous() {
        currentIndex = (currentIndex - 1 + playlist.size()) % playlist.size();
        play();
    }

    public boolean isPlaying() {
        return playing;
    }

    public int getCurrentSeconds() {
        return currentSec;
    }

    public int getTotalSeconds() {
        return totalSec;
    }

    @Override
    public void progress(int bytesread, long microseconds, byte[] pcmdata, Map properties) {
        currentSec = (int) (microseconds / 1_000_000);

        if (properties.containsKey("mp3.frame.bitrate")) {
            int bitrate = (Integer) properties.get("mp3.frame.bitrate"); // бітрейт у біт/с
            File file = new File(playlist.get(currentIndex));
            long fileSizeBytes = file.length();

            // тривалість у секундах = (розмір файлу * 8) / бітрейт
            totalSec = (int) ((fileSizeBytes * 8L) / bitrate);
        }
    }

    @Override
    public void opened(Object stream, Map properties) {
        // можна використати properties для отримання метаданих (title, author, length)
    }

    @Override
    public void stateUpdated(BasicPlayerEvent event) {
        // реагуємо на зміни стану (PLAYING, STOPPED, PAUSED)
        // наприклад:
        // System.out.println("State updated: " + event.getCode());
    }

    @Override
    public void setController(BasicController controller) {
        // можна зберегти контролер, якщо треба керувати ззовні
    }
}