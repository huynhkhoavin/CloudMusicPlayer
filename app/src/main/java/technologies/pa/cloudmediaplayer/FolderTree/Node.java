package technologies.pa.cloudmediaplayer.FolderTree;

import java.util.ArrayList;

import technologies.pa.cloudmediaplayer.Object.Folder;

/**
 * Created by Dev02 on 3/2/2017.
 */

public class Node implements INode {

    ArrayList<INode> iNodes = new ArrayList<INode>();
    //current folder Title
    String currentTitle;

    //current folder Path
    String currentPath;

    public INode getNodeFromPosition(int position){
        INode x = iNodes.get(position);
        return x;
    }

    public ArrayList<INode> getINodeList(){
        return iNodes;
    }
    //Convert to ListFolder

    public ArrayList<Folder> getListFolder(){
        ArrayList<Folder> folderArrayList = new ArrayList<Folder>();
        iNodes = getINodeList();
        for(INode node : iNodes){
            folderArrayList.add(new Folder(node.getCurrentTitle(),node.getNodeType(),node.getCurrentPath(),node.getLeafCount()));
        }
        return folderArrayList;
    }

    public Node(String currentTitle, String parentPath){
        this.currentTitle = currentTitle;
        this.currentPath  = parentPath+"/"+currentTitle;
        this.iNodes = new ArrayList<INode>();
    }

    //get Node via Node's Name
    public INode getNode(String currentUrl) {
        int t = -1;
        for (int i = 0; i<iNodes.size();i++){
            if (iNodes.get(i).getCurrentTitle().equals(currentUrl))
                t = i;
        }
        if(t>-1)
        return iNodes.get(t);
        else
             return null;
    }

    public Node(){
        this.currentTitle = "";
        this.currentPath = currentTitle;
        this.iNodes = new ArrayList<INode>();
}
    //add a NodeChild into this Node
    public boolean addChild(INode a){
        if (a.getNodeType() == NODE_TYPE.NODE)
        {
            if (iNodes.size()==0) {
                iNodes.add(a);
                return true;
            }
            else{
                //Chua co trong listNode
                if(getNode(a.getCurrentTitle())==null)
                {
                    iNodes.add(a);
                    return true;
                }
                else
                    return false;
            }
        }
        else if(a.getNodeType() == NODE_TYPE.LEAF){
            iNodes.add(a);
            return true;
        }
        else
            return false;
    }

    //get LeafCount of current Node
    @Override
    public int getLeafCount(){
        int Count = 0;
        if (iNodes.size()>0) {
            for (int i = 0; i< iNodes.size(); i++){
                if(iNodes.get(i).getNodeType()==NODE_TYPE.LEAF)
                    Count++;
                else{
                    Count+=iNodes.get(i).getLeafCount();
                }
            }
        }
        return Count;
    }

    @Override
    public String getCurrentTitle() {
        return currentTitle;
    }

    @Override
    public String getCurrentPath() {
        return currentPath;
    }

    //Add a list of Node into this root Node
    public void addListNode(ArrayList<String> list_Node){
        //Is node
        if (list_Node.size()>1){
            addChild(new Node(list_Node.get(0),currentPath));

                Node a = (Node)getNode(list_Node.get(0));
                list_Node.remove(0);
                a.addListNode(list_Node);

        }
        //Is leaf
        else if(list_Node.size()==1)
        {
            addChild(new Leaf(list_Node.get(0),currentPath));
        }
        else{
            return;
        }
    }

    @Override
    public NODE_TYPE getNodeType() {
        return NODE_TYPE.NODE;
    }

    //Node type Enum

}
