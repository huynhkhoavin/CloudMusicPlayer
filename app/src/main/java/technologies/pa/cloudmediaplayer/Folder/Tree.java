package technologies.pa.cloudmediaplayer.Folder;

import java.util.ArrayList;

/**
 * Created by Dev02 on 3/6/2017.
 */

public class Tree {

    ArrayList<Folder> listFolder = new ArrayList<>();

    public ArrayList<Folder> getListFolder() {
        return listFolder;
    }

    public int checkFolderIsExist(String newPath){
        if(listFolder.size()==0)
            return -1;
        else
            for (int i = 0; i<listFolder.size();i++) {
                if (listFolder.get(i).getPath().equals(newPath)) return i;
            }
        return -1;
    }
    public void addTree(ArrayList<ArrayList<String>> listAfterSplit) {
        int check;
        for (ArrayList<String> folderPath : listAfterSplit) {

            check = checkFolderIsExist(folderPath.get(0));

            //If folder is exitst
            if (check > -1) {
                listFolder.get(check).listSong.add(new Song(folderPath.get(0) + folderPath.get(1), folderPath.get(1)));
            }
            else if (check == -1) {
                String folderTitle = splitPath(folderPath.get(0)).get(1);
                listFolder.add(new Folder(folderPath.get(0),folderTitle));
                listFolder.get(listFolder.size()-1).listSong.add(new Song(folderPath.get(0) + folderPath.get(1), folderPath.get(1)));
            }
        }
    }
    public ArrayList<ArrayList<String>> convertListPath(ArrayList<String> listPath){
        ArrayList<ArrayList<String>> newList = new ArrayList<>();
        for (String s: listPath){
            newList.add(splitPath(s));
        }
        return newList;
    }
    // "a1/b2/c3/d4" => Arraylist {"c3","d4"}
    public ArrayList<String> splitPath(String s){
        String s1 = s.substring(1);
        String[] arr = s1.split("/");
        ArrayList<String> tem = new ArrayList<String>();
        String s2 = "/";
        for (int i = 0; i<arr.length-1;i++){
            s2+=arr[i];
            s2+="/";
        }
        tem.add(s2);
        tem.add(arr[arr.length-1]);
        return tem;
    }
}
