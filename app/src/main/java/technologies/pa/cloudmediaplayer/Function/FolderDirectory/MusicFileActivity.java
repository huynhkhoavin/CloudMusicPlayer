package technologies.pa.cloudmediaplayer.Function.FolderDirectory;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import technologies.pa.cloudmediaplayer.Folder.Song;
import technologies.pa.cloudmediaplayer.Pattern.RecyclerItemClickListener;
import technologies.pa.cloudmediaplayer.R;
import technologies.pa.cloudmediaplayer.Service.Constants;
import technologies.pa.cloudmediaplayer.Service.NotificationService;

/**
 * Created by Dev02 on 3/6/2017.
 */

public class MusicFileActivity extends AppCompatActivity{
    @BindView(R.id.recycleView)
    RecyclerView recyclerView;
    ListFileAdapter listFileAdapter;
    MediaPlayer mMediaPlayer = new MediaPlayer();
    int ClickedPosition;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_list_music);
        ButterKnife.bind(this);
        showListFile();
    }
    public void showListFile(){
        ClickedPosition = Integer.parseInt(getIntent().getStringExtra("position"));
        int x = (Directory.getInstance().getListFolder().get(ClickedPosition)).getListSong().size();
        Song[] songs = new Song[x];
        (Directory.getInstance().getListFolder().get(ClickedPosition)).getListSong().toArray(songs);
        listFileAdapter = new ListFileAdapter(this,songs);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(listFileAdapter);
        recyclerView.addOnItemTouchListener(new RecyclerItemClickListener(this, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                //Play
                String path = "/storage"+Directory.getInstance().getListFolder().get(ClickedPosition).getListSong().get(position).getPath();
                Intent intent = new Intent( getApplicationContext(), NotificationService.class );
                intent.setAction( Constants.ACTION.STARTFOREGROUND_ACTION );
                startService( intent );
                int x = 0;
                try {
                    mMediaPlayer.setDataSource(path);
                    mMediaPlayer.prepare();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                mMediaPlayer.start();
            }
        }));
    }
}
