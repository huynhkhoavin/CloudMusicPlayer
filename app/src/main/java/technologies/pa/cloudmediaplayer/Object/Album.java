package technologies.pa.cloudmediaplayer.Object;

/**
 * Created by Dev02 on 3/1/2017.
 */

public class Album {
    int albumImage;
    String AlbumName;
    String AlbumTitle;

    public Album(){
        AlbumName = "default";
    }
    public Album(int albumImage, String albumName, String albumTitle) {
        this.albumImage = albumImage;
        AlbumName = albumName;
        AlbumTitle = albumTitle;
    }

    public int getAlbumImage() {
        return albumImage;
    }

    public void setAlbumImage(int albumImage) {
        this.albumImage = albumImage;
    }

    public String getAlbumName() {
        return AlbumName;
    }

    public void setAlbumName(String albumName) {
        AlbumName = albumName;
    }

    public String getAlbumTitle() {
        return AlbumTitle;
    }

    public void setAlbumTitle(String albumTitle) {
        AlbumTitle = albumTitle;
    }
}
