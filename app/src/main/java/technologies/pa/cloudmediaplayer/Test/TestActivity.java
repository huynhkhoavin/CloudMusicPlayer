package technologies.pa.cloudmediaplayer.Test;

import android.Manifest;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import java.util.ArrayList;

import technologies.pa.cloudmediaplayer.Object.Song;
import technologies.pa.cloudmediaplayer.R;
import technologies.pa.cloudmediaplayer.Sqlite.DatabaseHelper;

/**
 * Created by Dev02 on 3/2/2017.
 */

public class TestActivity extends AppCompatActivity {

    private static final String TAG = "TestActivity";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.music);
        isStoragePermissionGranted();

    }

    public  boolean isStoragePermissionGranted() {
        if (Build.VERSION.SDK_INT >= 23) {
            if (checkSelfPermission(android.Manifest.permission.READ_EXTERNAL_STORAGE)== PackageManager.PERMISSION_GRANTED &&checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)==PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE,Manifest.permission.WRITE_EXTERNAL_STORAGE},PackageManager.PERMISSION_GRANTED);
                return true;
            } else {

                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE,Manifest.permission.WRITE_EXTERNAL_STORAGE},1);
                return false;
            }
        }
        else { //permission is automatically granted on sdk<23 upon installation

            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE,Manifest.permission.WRITE_EXTERNAL_STORAGE},PackageManager.PERMISSION_GRANTED);
            return true;
        }
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(grantResults[0]== PackageManager.PERMISSION_GRANTED){
            DatabaseHelper db = new DatabaseHelper(this);
            //db.addSong(new Song("Hương Tóc Mạ Non","Quang Lê"));
            ArrayList<Song> listSong = getSongInfo();
            for (Song song: listSong){
                //db.addSong(song);
            }
            db.getSong(10);
        }
    }

    private ArrayList<Song> getSongInfo(){
        Uri[] uris = {MediaStore.Audio.Media.EXTERNAL_CONTENT_URI};
        Log.e(TAG,MediaStore.Audio.Media.EXTERNAL_CONTENT_URI.toString());
        final Cursor mCursor = getContentResolver().query(
                MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,
                new String[] { MediaStore.Audio.Media.DISPLAY_NAME, MediaStore.Audio.Media.DATA,MediaStore.Audio.Media.ARTIST }, null, null,
                "LOWER(" + MediaStore.Audio.Media.TITLE + ") ASC");
        int count = mCursor.getCount();
        ArrayList<Song> listSong = new ArrayList<>();
        if (mCursor.moveToFirst()) {
            do {
                Song s = new Song();
                s.setSongTitle(mCursor.getString(mCursor.getColumnIndexOrThrow(MediaStore.Audio.Media.DISPLAY_NAME)));
                s.setPath(mCursor.getString(mCursor.getColumnIndexOrThrow(MediaStore.Audio.Media.DATA)));
                s.setSingerName(mCursor.getString(mCursor.getColumnIndexOrThrow(MediaStore.Audio.Media.ARTIST)));
                listSong.add(s);
            } while (mCursor.moveToNext());
        }

        mCursor.close();

        return listSong;
    }


}
