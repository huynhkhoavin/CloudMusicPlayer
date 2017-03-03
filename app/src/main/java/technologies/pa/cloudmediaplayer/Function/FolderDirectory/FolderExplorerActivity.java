package technologies.pa.cloudmediaplayer.Function.FolderDirectory;

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
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import technologies.pa.cloudmediaplayer.FolderTree.Node;
import technologies.pa.cloudmediaplayer.FolderTree.Tree;
import technologies.pa.cloudmediaplayer.Object.Folder;
import technologies.pa.cloudmediaplayer.Pattern.RecyclerItemClickListener;
import technologies.pa.cloudmediaplayer.R;

/**
 * Created by Dev02 on 3/3/2017.
 */

public class FolderExplorerActivity extends AppCompatActivity {

    private static final String TAG = "Folder Explorer";
    @BindView(R.id.recycleView)
    RecyclerView recyclerView;
    @BindView(R.id.current_directory_path)
    TextView tv_currentPath;
    ListFolderAdapter listFolderAdapter;
    private Tree directoryTree;
    String[] mMusicPath;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_folder);
        ButterKnife.bind(this);
        //Read data from Storage
        ReadData();
        ShowFolderList(directoryTree.getRootNode());
    }

    public void ReadData(){
        directoryTree = new Tree();
        directoryTree.convertStringToArray(getMusicDirectory());

    }

    public void ShowFolderList(final Node root){
        ArrayList<Folder> folderArrayList = new ArrayList<Folder>();

        folderArrayList = root.getListFolder();
        Folder[] listFolder = new Folder[folderArrayList.size()];
        folderArrayList.toArray(listFolder);
        listFolderAdapter = new ListFolderAdapter(this,listFolder);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(listFolderAdapter);
        recyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(this, new RecyclerItemClickListener.OnItemClickListener() {
                    @Override public void onItemClick(View view, int position) {
                        Log.e(TAG,String.valueOf(position));

                        ShowFolderList((Node)root.getNodeFromPosition(position));
                    }
                })
        );


    }
    public  boolean isStoragePermissionGranted() {
        if (Build.VERSION.SDK_INT >= 23) {
            if (checkSelfPermission(android.Manifest.permission.READ_EXTERNAL_STORAGE)
                    == PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},PackageManager.PERMISSION_GRANTED);
                return true;
            } else {

                Log.v(TAG,"Permission is revoked");
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 1);
                return false;
            }
        }
        else { //permission is automatically granted on sdk<23 upon installation
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},PackageManager.PERMISSION_GRANTED);
            return true;
        }
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(grantResults[0]== PackageManager.PERMISSION_GRANTED){
            getMusicDirectory();
        }
    }
    private ArrayList<String> getMusicDirectory() {
        Uri[] uris = {MediaStore.Audio.Media.EXTERNAL_CONTENT_URI};
        Log.e(TAG,MediaStore.Audio.Media.EXTERNAL_CONTENT_URI.toString());
        final Cursor mCursor = getContentResolver().query(
                MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,
                new String[] { MediaStore.Audio.Media.DISPLAY_NAME, MediaStore.Audio.Media.DATA }, null, null,
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
        mMusicPath = mAudioPath;
        ArrayList<String> listPath = new ArrayList<String>();
        for (int j = 0; j<mAudioPath.length; j++){
            String s = mAudioPath[j].substring(8,mAudioPath[j].length()-1);

            listPath.add(s);
        }
        return listPath;
    }
}
