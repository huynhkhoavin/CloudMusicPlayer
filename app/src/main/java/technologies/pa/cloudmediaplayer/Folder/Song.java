package technologies.pa.cloudmediaplayer.Folder;

/**
 * Created by Dev02 on 3/6/2017.
 */

public class Song {
    String Path;
    String Title;

    public Song(String path, String title) {
        Path = path;
        Title = title;
    }

    public String getPath() {
        return Path;
    }

    public void setPath(String path) {
        Path = path;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }
}
