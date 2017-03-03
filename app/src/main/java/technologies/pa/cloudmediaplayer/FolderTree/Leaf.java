package technologies.pa.cloudmediaplayer.FolderTree;

/**
 * Created by Dev02 on 3/3/2017.
 */

public class Leaf implements INode {
    String currentTitle;
    String currentPath;

    public Leaf(String currentTitle, String parentPath) {
        this.currentTitle = currentTitle;
        this.currentPath = parentPath+"/"+currentTitle;
    }
    @Override
    public NODE_TYPE getNodeType(){
        return
                 NODE_TYPE.LEAF;
    }

    @Override
    public int getLeafCount() {
        return 0;
    }

    public String getCurrentTitle() {
        return currentTitle;
    }

    public void setCurrentTitle(String currentTitle) {
        this.currentTitle = currentTitle;
    }

    public String getCurrentPath() {
        return currentPath;
    }

    public void setCurrentPath(String currentPath) {
        this.currentPath = currentPath;
    }
}
