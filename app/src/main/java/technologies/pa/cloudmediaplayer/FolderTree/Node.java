package technologies.pa.cloudmediaplayer.FolderTree;

import java.util.ArrayList;

/**
 * Created by Dev02 on 3/2/2017.
 */

public class Node {
    ArrayList<String> listLeaf;
    ArrayList<Node> listNode;
    String currentUrl;
public Node(String currentUrl){
    listLeaf = new ArrayList<String>();
    listNode = new ArrayList<Node>();
    this.currentUrl = currentUrl;
}
    public Node getNode(String currentUrl)
    {
        int t = -1;
        for (int i = 0; i<listNode.size();i++){
            if (listNode.get(i).currentUrl.equals(currentUrl))
                t = i;
        }
        if(t>-1)
        return listNode.get(t);
        else
             return null;
    }
public Node(){
    listLeaf = new ArrayList<String>();
    listNode = new ArrayList<Node>();
    this.currentUrl = "Root";
}
    public boolean addChild(Node a){
        if (listNode.size()==0) {
            listNode.add(a);
            return true;
        }
        else{
            //Chua co trong listNode
            if(getNode(a.currentUrl)==null)
            {
                listNode.add(a);
                return true;
            }
            else
                return false;
        }
    }
    public boolean addChild(String leaf){

            listLeaf.add(leaf);
            return true;
    }
    public int findNodeChild(String s){
        int t = -1;
        for (int i = 0; i < listNode.size(); i++){
            if (listNode.get(i).currentUrl == s)
                t = i;
            break;
        }
        return t;
    }

    public void addListNode(ArrayList<String> list_Node){
        //Is node
        if (list_Node.size()>1){
            addChild(new Node(list_Node.get(0)));

                Node a = getNode(list_Node.get(0));
                list_Node.remove(0);
                a.addListNode(list_Node);

        }
        else if(list_Node.size()==1)
        {
            addChild(list_Node.get(0));
        }
        else{
            return;
        }
    }
}
