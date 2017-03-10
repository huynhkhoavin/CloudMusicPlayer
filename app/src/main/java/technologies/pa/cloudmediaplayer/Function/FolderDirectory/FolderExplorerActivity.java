package technologies.pa.cloudmediaplayer.Function.FolderDirectory;

import android.Manifest;
import android.content.Intent;
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
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import technologies.pa.cloudmediaplayer.Folder.Folder;
import technologies.pa.cloudmediaplayer.Pattern.RecyclerItemClickListener;
import technologies.pa.cloudmediaplayer.R;
import technologies.pa.cloudmediaplayer.Sqlite.DatabaseHelper;
import technologies.pa.cloudmediaplayer.Tool.ArrayConvert;
import technologies.pa.cloudmediaplayer.Tool.Permission;
import technologies.pa.cloudmediaplayer.Tool.ReadMusicDirectory;
import technologies.pa.cloudmediaplayer.Tool.StringConvert;

/**
 * Created by Dev02 on 3/3/2017.
 */

public class FolderExplorerActivity extends AppCompatActivity {

    private static final String TAG = "Folder Explorer";
    @BindView(R.id.recycleView)
    RecyclerView recyclerView;
    @BindView(R.id.current_directory_path)
    TextView tv_currentPath;
    private ListFolderAdapter listFolderAdapter;
    DatabaseHelper databaseHelper = new DatabaseHelper(this);
    @BindView(R.id.btn_refresh)
    ImageButton btn_Refresh;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_folder);
        ButterKnife.bind(this);
        Permission.isStoragePermissionGranted(this);
        btn_Refresh.setOnClickListener(refreshOnClick);
    }
    public void ReadPathAndAddToList(){
        //get raw path from directory
        ArrayList<String> listRawPath = ReadMusicDirectory.getMusicDirectory(this);
        //process and split List String
        ArrayList<ArrayList<String>> listAfterSplit = StringConvert.convertListPath(listRawPath);
        //add list after Process
        Directory.getInstance().addTree(listAfterSplit);
    }
    public void ShowListDirectory(){

        int x = databaseHelper.getAllFolder().size();
        final Folder[] folders = new Folder[x];
        databaseHelper.getAllFolder().toArray(folders);
        listFolderAdapter = new ListFolderAdapter(this, ArrayConvert.toObjectArray(ArrayConvert.toArrayList(folders)));
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(listFolderAdapter);
        recyclerView.addOnItemTouchListener(new RecyclerItemClickListener(getApplicationContext(), new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent intent = new Intent(FolderExplorerActivity.this,MusicFileActivity.class);
                Folder folder = (Folder)listFolderAdapter.getItemOnPosition(position);
                intent.putExtra("position",String.valueOf(folder.getId()));
                startActivity(intent);
            }
        }));
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(grantResults[0]== PackageManager.PERMISSION_GRANTED){
            if (databaseHelper.getAllFolder().size()==0){
                ReadPathAndAddToList();
                addDataToDB();
            }
            ShowListDirectory();
        }
    }
    public void addDataToDB(){
        ArrayList<Folder> folderArrayList = Directory.getInstance().getListFolder();

        if (folderArrayList.size()==0){ // no music
            Toast.makeText(getApplicationContext(),"Not exist any music file!",Toast.LENGTH_SHORT).show();
        }
        else  //have music
        {
            for (Folder f : folderArrayList){
                databaseHelper.addFolder(f);
                databaseHelper.addListFileToFolder(f.getListFile(),f.getId());
            }
        }
    }
    View.OnClickListener refreshOnClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if(v == btn_Refresh){
                ReadPathAndAddToList();
            }
        }
    };
}
