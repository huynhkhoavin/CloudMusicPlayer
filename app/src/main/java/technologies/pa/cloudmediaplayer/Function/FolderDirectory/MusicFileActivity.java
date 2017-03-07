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

import butterknife.BindView;
import butterknife.ButterKnife;
import technologies.pa.cloudmediaplayer.Folder.File;
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
    boolean mBounder;
    NotificationService notificationService;
    Intent intent;
    ServiceConnection mConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            Toast.makeText(MusicFileActivity.this,"Service Is Connected",Toast.LENGTH_SHORT).show();
            mBounder = true;
            NotificationService.LocalBinder mLocalBinder = (NotificationService.LocalBinder)service;
            notificationService = mLocalBinder.getServiceInstance();

        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            Toast.makeText(MusicFileActivity.this,"Service Is Disconnected!",Toast.LENGTH_SHORT).show();
            mBounder = false;
            notificationService = null;
        }
    };

    @Override
    protected void onStart() {
        super.onStart();
        intent = new Intent(MusicFileActivity.this,NotificationService.class);
        bindService(intent,mConnection,BIND_AUTO_CREATE);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_list_music);
        ButterKnife.bind(this);
        showListFile();
    }

    public void fileOnClick(String path){
        intent.setAction(Constants.ACTION.STARTFOREGROUND_ACTION);
        notificationService.setMusicPath(path);
        startService(intent);
        intent.setAction(Constants.ACTION.PLAY_ACTION);

    }

    public void showListFile(){
        ClickedPosition = Integer.parseInt(getIntent().getStringExtra("position"));
        int x = (Directory.getInstance().getListFolder().get(ClickedPosition)).getListFile().size();
        File[] files = new File[x];
        (Directory.getInstance().getListFolder().get(ClickedPosition)).getListFile().toArray(files);
        listFileAdapter = new ListFileAdapter(this, files);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(listFileAdapter);
        recyclerView.addOnItemTouchListener(new RecyclerItemClickListener(this, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
//                //Play
                String path = "/storage"+Directory.getInstance().getListFolder().get(ClickedPosition).getListFile().get(position).getPath();
//                Intent intent = new Intent( getApplicationContext(), NotificationService.class );
//                intent.setAction( Constants.ACTION.STARTFOREGROUND_ACTION );
//                startService( intent );
//                int x = 0;
//                try {
//                    mMediaPlayer.setDataSource(path);
//                    mMediaPlayer.prepare();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//                mMediaPlayer.start();
                fileOnClick(path);
            }
        }));
    }
}
