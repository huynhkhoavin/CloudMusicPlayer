package technologies.pa.cloudmediaplayer.Object;

/**
 * Created by Dev02 on 3/1/2017.
 */

public class Artist {
    public Artist(String artistName, int artistAvatar) {
        this.artistName = artistName;
        this.artistAvatar = artistAvatar;
    }

    public int getArtistAvatar() {
        return artistAvatar;
    }

    public void setArtistAvatar(int artistAvatar) {
        this.artistAvatar = artistAvatar;
    }

    public String getArtistName() {
        return artistName;
    }

    public void setArtistName(String artistName) {
        this.artistName = artistName;
    }

    int artistAvatar;
    String artistName;
}
