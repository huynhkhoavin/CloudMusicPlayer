package technologies.pa.cloudmediaplayer.Tool;

import java.util.ArrayList;

/**
 * Created by Khoavin on 3/5/2017.
 */

public class ArrayConvert<T> {
    public static <T> T[] toArray(ArrayList<T> list) {
        T[] array = (T[]) new Object[list.size()];
        for (int i = 0; i < list.size(); i++) {
            array[i] = list.get(i);
        }
        return array;
    }
    public static <T> ArrayList<T> toArrayList(ArrayList<Object> list){
        ArrayList<T> arrayList = new ArrayList<>();
        for (Object obj:list){
            T t = (T)obj;
            arrayList.add(t);
        }
        return arrayList;
    }
    public static <T> ArrayList<Object> toObjectArray(T[] ts){
        return ArrayConvert.toObjectArray(ArrayConvert.toArrayList(ts));
    }
    public static <T> ArrayList<T> toArrayList(T[] ts){
        ArrayList<T> arrayList = new ArrayList<>();
        for (T t:ts){
            arrayList.add(t);
        }
        return arrayList;
    }
    public static<T> ArrayList<Object> toObjectArray(ArrayList<T> list){
        ArrayList<Object> arrayList = new ArrayList<>();
        for (T t:list){
            Object obj = (Object)t;
            arrayList.add(t);
        }
        return arrayList;
    }
}
