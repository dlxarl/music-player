package player;

import java.util.ArrayList;
import java.util.List;

public class Playlist {
    private final List<String> tracks = new ArrayList<>();

    public void add(String path) {
        tracks.add(path);
    }

    public String get(int index) {
        return tracks.get(index);
    }

    public int size() {
        return tracks.size();
    }

    public boolean isEmpty() {
        return tracks.isEmpty();
    }
}