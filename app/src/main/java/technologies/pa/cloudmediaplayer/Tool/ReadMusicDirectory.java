package technologies.pa.cloudmediaplayer.Tool;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;
import android.util.Log;

import java.util.ArrayList;

/**
 * Created by Dev02 on 3/10/2017.
 */

public class ReadMusicDirectory {
    public final String TAG = "Tool / Read Music Directory ";
    public static ArrayList<String> getMusicDirectory(Context context) {
        Uri[] uris = {MediaStore.Audio.Media.EXTERNAL_CONTENT_URI};
        final Cursor mCursor = context.getContentResolver().query(
                MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,
                new String[] { MediaStore.Audio.Media.DISPLAY_NAME, MediaStore.Audio.Media.DATA,MediaStore.Audio.Media.ARTIST }, null, null,
                "LOWER(" + MediaStore.Audio.Media.TITLE + ") ASC");
        int count = mCursor.getCount();
        String[] songs = new String[count];
        String[] mAudioPath = new String[count];
        int i = 0;
        if (mCursor.moveToFirst()) {
            do {
                songs[i] = mCursor.getString(0);
                mAudioPath[i] = mCursor.getString(mCursor.getColumnIndexOrThrow(MediaStore.Audio.Media.DATA));
                i++;
            } while (mCursor.moveToNext());
        }
        mCursor.close();
        ArrayList<String> listPath = new ArrayList<String>();
        for (int j = 0; j<mAudioPath.length; j++){
            String s = mAudioPath[j].substring(8,mAudioPath[j].length());
            listPath.add(s);
        }
        return listPath;
    }
}
