package technologies.pa.cloudmediaplayer.Folder;

/**
 * Created by Dev02 on 3/9/2017.
 */

public class CurrentFile {
    int folderId;
    int filePos;

    public CurrentFile(int folderId, int filePos) {
        this.folderId = folderId;
        this.filePos = filePos;
    }

    public CurrentFile() {

    }

    public int getFolderId() {
        return folderId;
    }

    public void setFolderId(int folderId) {
        this.folderId = folderId;
    }

    public int getFilePos() {
        return filePos;
    }

    public void setFilePos(int filePos) {
        this.filePos = filePos;
    }
}
