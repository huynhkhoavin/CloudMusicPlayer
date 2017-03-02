package technologies.pa.cloudmediaplayer.FolderTree;

import java.util.ArrayList;

/**
 * Created by Dev02 on 3/2/2017.
 */

public class Tree {
    Node root;
    public Tree(){
        root = new Node();
    }
    public void convertStringToArray(ArrayList<String> listPath){
        ArrayList<ArrayList<String>> result = addTree(listPath);
        for (int i = 0; i<result.size();i++){
            root.addListNode(result.get(i));
        }
    }
    public ArrayList<ArrayList<String>>addTree(ArrayList<String> listFile){
        ArrayList<ArrayList<String>> temp = new ArrayList<ArrayList<String>>();
        for (int i = 0; i<listFile.size();i++){
            temp.add(splitPath(listFile.get(i)));
        }
        return temp;
    }

    public ArrayList<String> splitPath(String s){
        String s1 = s.substring(1);
        String[] arr = s1.split("/");
        ArrayList<String> tem = new ArrayList<String>();
        for (int i = 0; i<arr.length;i++){
            tem.add(arr[i]);
        }
        return tem;
    }
}
