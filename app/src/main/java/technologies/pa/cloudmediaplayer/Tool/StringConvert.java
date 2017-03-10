package technologies.pa.cloudmediaplayer.Tool;

import java.util.ArrayList;

/**
 * Created by Dev02 on 3/10/2017.
 */

public class StringConvert {
    public static ArrayList<ArrayList<String>> convertListPath(ArrayList<String> listPath){
        ArrayList<ArrayList<String>> newList = new ArrayList<>();
        for (String s: listPath){
            newList.add(splitPath(s));
        }
        return newList;
    }
    public static ArrayList<String> splitPath(String s){
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
