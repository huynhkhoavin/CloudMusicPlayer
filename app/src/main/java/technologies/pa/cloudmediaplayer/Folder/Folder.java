package technologies.pa.cloudmediaplayer.Folder;

import java.util.ArrayList;

/**
 * Created by Dev02 on 3/6/2017.
 */

public class Folder {
    String Path;
    String Title;
    ArrayList<File> listFile;

    public Folder(String path, String title) {
        Path = path;
        Title = title;
        listFile = new ArrayList<File>();
    }
    public void AddSong(File file){
        listFile.add(file);
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

    public ArrayList<File> getListFile() {
        return listFile;
    }

    public void setListFile(ArrayList<File> listFile) {
        this.listFile = listFile;
    }
}
