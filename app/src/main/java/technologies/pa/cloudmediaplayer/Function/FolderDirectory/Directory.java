package technologies.pa.cloudmediaplayer.Function.FolderDirectory;

import technologies.pa.cloudmediaplayer.Folder.Tree;

/**
 * Created by Dev02 on 3/6/2017.
 */

public class Directory {
    public static Tree tree;
    private void Directory(){
        tree = new Tree();
    }
    public static Tree getInstance(){
        if (tree==null)
            tree = new Tree();
        return tree;
    }
}
