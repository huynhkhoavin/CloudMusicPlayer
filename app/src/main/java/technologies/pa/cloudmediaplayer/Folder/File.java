package technologies.pa.cloudmediaplayer.Folder;

/**
 * Created by Dev02 on 3/6/2017.
 */

public class File {
    int FolderId;

    public int getFolderId() {
        return FolderId;
    }

    public void setFolderId(int folderId) {
        FolderId = folderId;
    }

    public File(int folderId, int id, String path, String title) {
        FolderId = folderId;
        Id = id;
        Path = path;
        Title = title;
    }

    int Id;
    String Path;
    String Title;

    public File( int Id, String path, String title) {
        this.Id = Id;
        Path = path;
        Title = title;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getPath() {
        return Path;
    }
    public String getPathForPlayer(){
        return "/storage"+Path;
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
