package technologies.pa.cloudmediaplayer.Object;

/**
 * Created by Dev02 on 2/28/2017.
 */

public class Song {
    String SongTitle;
    String SingerName;
    String Path;
    int Url;
    public Song(){}
    public Song(String songTitle, String singerName) {
        SongTitle = songTitle;
        SingerName = singerName;
    }

    public Song(String songTitle, String singerName, int url) {
        SongTitle = songTitle;
        SingerName = singerName;
        Url = url;
    }

    public Song(String songTitle, String singerName, String path) {
        SongTitle = songTitle;
        SingerName = singerName;
        Path = path;
    }

    public String getPath() {
        return Path;
    }

    public void setPath(String path) {
        Path = path;
    }

    public int getUrl() {
        return Url;
    }

    public void setUrl(int url) {
        Url = url;
    }

    public String getSongTitle() {
        return SongTitle;
    }

    public void setSongTitle(String songTitle) {
        SongTitle = songTitle;
    }

    public String getSingerName() {
        return SingerName;
    }

    public void setSingerName(String singerName) {
        SingerName = singerName;
    }


}
