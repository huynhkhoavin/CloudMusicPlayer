package technologies.pa.cloudmediaplayer.Object;

/**
 * Created by Dev02 on 2/28/2017.
 */

public class Song {
    String SongName;
    String SingerName;
    int Url;
    public Song(String songName, String singerName) {
        SongName = songName;
        SingerName = singerName;
    }

    public Song(String songName, String singerName, int url) {
        SongName = songName;
        SingerName = singerName;
        Url = url;
    }

    public int getUrl() {
        return Url;
    }

    public void setUrl(int url) {
        Url = url;
    }

    public String getSongName() {
        return SongName;
    }

    public void setSongName(String songName) {
        SongName = songName;
    }

    public String getSingerName() {
        return SingerName;
    }

    public void setSingerName(String singerName) {
        SingerName = singerName;
    }


}
