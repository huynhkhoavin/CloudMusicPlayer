package technologies.pa.cloudmediaplayer.Object;

/**
 * Created by Dev02 on 2/28/2017.
 */

public class Song {
    public Song(String songName, String singerName) {
        SongName = songName;
        SingerName = singerName;
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

    String SongName;
    String SingerName;
}
