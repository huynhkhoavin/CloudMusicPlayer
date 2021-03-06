package technologies.pa.cloudmediaplayer.Function.FolderDirectory;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import technologies.pa.cloudmediaplayer.DaggerDI.MusicService.Implement.CurrentPlay;
import technologies.pa.cloudmediaplayer.Folder.File;
import technologies.pa.cloudmediaplayer.Function.Player.PlayingActivity;
import technologies.pa.cloudmediaplayer.Pattern.RecyclerItemClickListener;
import technologies.pa.cloudmediaplayer.R;
import technologies.pa.cloudmediaplayer.Service.BackgroundMusicService;
import technologies.pa.cloudmediaplayer.Service.Constants;
import technologies.pa.cloudmediaplayer.Sqlite.DatabaseHelper;
import technologies.pa.cloudmediaplayer.Tool.ArrayConvert;

/**
 * Created by Dev02 on 3/6/2017.
 */

public class MusicFileActivity extends AppCompatActivity{
    @BindView(R.id.recycleView)
    RecyclerView recyclerView;
    ListFileAdapter listFileAdapter;
    int ClickedPosition;
    Intent intent;
    DatabaseHelper databaseHelper = new DatabaseHelper(this);

    CurrentPlay currentPlay = CurrentPlay.getInstance();

    @Override
    protected void onStart() {
        super.onStart();
        intent = new Intent(MusicFileActivity.this,BackgroundMusicService.class);
    }
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_list_music);
        ButterKnife.bind(this);
        showListFile();

    }
    public void fileOnClick(int currentFolder, int currentFileClick){
        //region DB
        CurrentPlay.getInstance().SetDataHelper(databaseHelper);
        CurrentPlay.getInstance().UpdateData(currentFolder,currentFileClick);
//        intent.setAction(Constants.ACTION.STARTFOREGROUND_ACTION);
//        startService(intent);
        Intent startPlayingIntent = new Intent(MusicFileActivity.this, PlayingActivity.class);
        startPlayingIntent.putExtra("action","start_music");
        startActivity(startPlayingIntent);
    }

    public void showListFile(){
        ClickedPosition = Integer.parseInt(getIntent().getStringExtra("position"));

        final int x = databaseHelper.getAllFileFromFolder(ClickedPosition).size();
        final File[] files = new File[x];
        databaseHelper.getAllFileFromFolder(ClickedPosition).toArray(files);
        listFileAdapter = new ListFileAdapter(this, ArrayConvert.toObjectArray(files));
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(listFileAdapter);
        recyclerView.addOnItemTouchListener(new RecyclerItemClickListener(this, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                fileOnClick(ClickedPosition,position);
            }
        }));
    }
}
