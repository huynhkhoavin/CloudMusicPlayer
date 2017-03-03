package technologies.pa.cloudmediaplayer.Object;

import technologies.pa.cloudmediaplayer.FolderTree.NODE_TYPE;

/**
 * Created by Dev02 on 3/3/2017.
 */

public class Folder {
    String folderTitle;
    NODE_TYPE folderType;
    String folderPath;
    int folderMusicCount;

    public Folder(String folderTitle, NODE_TYPE folderType, String folderPath, int folderMusicCount) {
        this.folderTitle = folderTitle;
        this.folderType = folderType;
        this.folderPath = folderPath;
        this.folderMusicCount = folderMusicCount;
    }

    public NODE_TYPE getFolderType() {
        return folderType;
    }

    public void setFolderType(NODE_TYPE folderType) {
        this.folderType = folderType;
    }

    public String getFolderTitle() {
        return folderTitle;
    }

    public void setFolderTitle(String folderTitle) {
        this.folderTitle = folderTitle;
    }

    public String getFolderPath() {
        return folderPath;
    }

    public void setFolderPath(String folderPath) {
        this.folderPath = folderPath;
    }

    public int getFolderMusicCount() {
        return folderMusicCount;
    }

    public void setFolderMusicCount(int folderMusicCount) {
        this.folderMusicCount = folderMusicCount;
    }

    public enum FOLDER_TYPE{
        FOLDER,
        FILE
    }
}

