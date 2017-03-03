package technologies.pa.cloudmediaplayer.FolderTree;

/**
 * Created by Dev02 on 3/3/2017.
 */

public interface INode {
    NODE_TYPE getNodeType();
    int getLeafCount();
    String getCurrentTitle();
    String getCurrentPath();
}
