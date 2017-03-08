package technologies.pa.cloudmediaplayer.Folder;

import java.util.ArrayList;

/**
 * Created by Dev02 on 3/6/2017.
 */

public class Folder {
    int id;
    String Path;
    String Title;
    ArrayList<File> listFile;

    public  Folder(String title,String path) {
        Path = path;
        Title = title;
        listFile = new ArrayList<File>();
    }

    public Folder(int id, String title,String path) {
        this.id = id;
        Path = path;
        Title = title;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
        if(listFile==null)
            listFile = new ArrayList<>();
        return listFile;
    }

    public void setListFile(ArrayList<File> listFile) {
        this.listFile = listFile;
    }
}
