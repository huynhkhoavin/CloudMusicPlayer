package technologies.pa.cloudmediaplayer.Folder;

import java.util.ArrayList;

/**
 * Created by Dev02 on 3/6/2017.
 */

public class Folder {
    String Path;
    String Title;
    ArrayList<Song> listSong;

    public Folder(String path, String title) {
        Path = path;
        Title = title;
        listSong = new ArrayList<Song>();
    }
    public void AddSong(Song song){
        listSong.add(song);
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

    public ArrayList<Song> getListSong() {
        return listSong;
    }

    public void setListSong(ArrayList<Song> listSong) {
        this.listSong = listSong;
    }
}
