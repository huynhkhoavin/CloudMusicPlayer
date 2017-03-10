package technologies.pa.cloudmediaplayer.Function.Player;

import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import technologies.pa.cloudmediaplayer.Pattern.FragmentPattern;
import technologies.pa.cloudmediaplayer.Pattern.ViewPagerAdapter;
import technologies.pa.cloudmediaplayer.R;
import technologies.pa.cloudmediaplayer.Service.BackgroundMusicService;
import technologies.pa.cloudmediaplayer.Service.Constants;
import technologies.pa.cloudmediaplayer.Service.MusicPlayingEvent;
import technologies.pa.cloudmediaplayer.Service.NotificationControl;

/**
 * Created by Dev02 on 3/1/2017.
 */

public class PlayingActivity extends AppCompatActivity {


    //region Layout
    @BindView(R.id.viewPager)
    ViewPager viewPager;
    @BindView(R.id.tab_layout)
    TabLayout tabLayout;
    ViewPagerAdapter viewPagerAdapter;

    @BindView(R.id.btn_back)
    ImageView btn_Back;
    @BindView(R.id.tv_song_title)
    TextView tv_Song_Title;
    @BindView(R.id.tv_singer)
    TextView tv_Song_Singer;
    @BindView(R.id.btn_share)
    ImageView btn_Share;
    @BindView(R.id.tv_current_progress)
    TextView tv_CurrentProgress;
    @BindView(R.id.tv_duration)
    TextView tv_Duration;
    @BindView(R.id.progress_bar)
    ProgressBar ProgressBar;
    @BindView(R.id.btn_shuttle)
    ImageView btn_Shuttle;
    @BindView(R.id.btn_prev)
    ImageView btn_Prev;
    @BindView(R.id.btn_play)
    ImageView btn_Play;
    @BindView(R.id.btn_next)
    ImageView btn_Next;
    @BindView(R.id.btn_repeat)
    ImageView btn_Repeat;
    //endregion
    //region Service
    BackgroundMusicService backgroundMusicService;
    NotificationControl notificationControl;
    MusicPlayingEvent musicPlayingEvent;
    boolean mBounder;
    Intent serviceIntent;
    //endregion

    ServiceConnection mConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            Toast.makeText(getApplicationContext(),"Music starting...",Toast.LENGTH_SHORT).show();
            mBounder = true;
            BackgroundMusicService.LocalBinder mLocalBinder = (BackgroundMusicService.LocalBinder)service;
            backgroundMusicService = mLocalBinder.getServiceInstance();
        }
        @Override
        public void onServiceDisconnected(ComponentName name) {
            Toast.makeText(getApplicationContext(),"Music stopping...",Toast.LENGTH_LONG).show();
            mBounder = false;
            backgroundMusicService = null;
        }
    };
    View.OnClickListener OnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (v == btn_Back){

            }
            else if(v == btn_Share) {

            }
            else if(v == btn_Shuttle){

            }
            else if(v == btn_Prev){
                serviceIntent.setAction(Constants.ACTION.PREV_ACTION);
                PendingIntent ppreviousIntent = PendingIntent.getService(PlayingActivity.this, 0,
                        serviceIntent, 0);
            }
            else if(v == btn_Play){
                serviceIntent.setAction(Constants.ACTION.PLAY_ACTION);
                PendingIntent pplayIntent = PendingIntent.getService(PlayingActivity.this, 0,
                        serviceIntent, 0);
            }
            else if(v == btn_Next){
                serviceIntent.setAction(Constants.ACTION.NEXT_ACTION);
                PendingIntent pnextIntent = PendingIntent.getService(PlayingActivity.this, 0,
                        serviceIntent, 0);
            }
            else if(v == btn_Repeat) {

            }
        }
    };

    @Override
    protected void onStart() {
        super.onStart();
        String action = getIntent().getStringExtra("action");
        if (action.equals("start_music")){
            serviceIntent.setAction(Constants.ACTION.STARTFOREGROUND_ACTION);
            startService(serviceIntent);
        }
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_playing);
        ButterKnife.bind(this);
        //region
        serviceIntent = new Intent(PlayingActivity.this,BackgroundMusicService.class);
        notificationControl = new NotificationControl();
        bindService(serviceIntent,mConnection,BIND_AUTO_CREATE);
        //endregion
        setUpTabAdapter();
    }
    private void setUpTabAdapter(){
        FragmentPattern[] FragmentList = {new PlayerPlaylistFragment(),new PlayerLyricFragment(),new FragmentPattern()};
        viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager(),FragmentList);
        viewPager.setAdapter(viewPagerAdapter);
        tabLayout.setupWithViewPager(viewPager, true);
    }
}
