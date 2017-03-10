package technologies.pa.cloudmediaplayer.Service;

import android.app.Notification;
import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.RemoteViews;
import android.widget.Toast;

import com.squareup.otto.Subscribe;

import java.io.IOException;

import technologies.pa.cloudmediaplayer.Application.MusicPlayerApp;
import technologies.pa.cloudmediaplayer.DaggerDI.MusicService.Implement.CurrentPlay;
import technologies.pa.cloudmediaplayer.Tool.PauseMusic;

/**
 * Created by Dev02 on 3/6/2017.
 */

public class BackgroundMusicService extends Service {
    private static final int NOTIFICATION_ID = 0;
    Notification status;
    private final String LOG_TAG = "BackgroundMusicService";
    private boolean isPlaying = false;
    private int lenght = 0;
    private final IBinder mBinder = new LocalBinder();
    private NotificationControl notificationControl;
    public class LocalBinder extends Binder{
        public BackgroundMusicService getServiceInstance(){
            return BackgroundMusicService.this;
        }
    }
    MusicEventControl musicEventControl = new MusicEventControl() {
        @Override
        public void Start() {
            startForeground();
        }

        @Override
        public void Prev() {
            prev();
        }

        @Override
        public void Play() {
            play();
        }

        @Override
        public void Next() {
            next();
        }

        @Override
        public void Stop() {
            stop();
        }
    };
    RemoteViews views;
    RemoteViews bigViews;
    private MediaPlayer mMediaPlayer = null;
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }
    public void release(){
        mMediaPlayer.release();
        mMediaPlayer = null;
        isPlaying = false;
    }
    public void startForeground(){
        notificationControl.showNotification();
        if (mMediaPlayer==null){
            mMediaPlayer = new MediaPlayer();
            try {
                mMediaPlayer.setDataSource(CurrentPlay.getInstance().GetCurrentSong().getPathForPlayer());
                mMediaPlayer.prepare();
                mMediaPlayer.start();
                notificationControl.UpdateSongInfo();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else
        {
            mMediaPlayer.reset();
            mMediaPlayer.pause();

            try {
                mMediaPlayer.setDataSource(CurrentPlay.getInstance().GetCurrentSong().getPathForPlayer());
                mMediaPlayer.prepare();
                mMediaPlayer.start();
                notificationControl.UpdateSongInfo();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        isPlaying = true;
        if (GlobalEvent.getInstance().getMusicPlayingEvent()!=null)
        {
            GlobalEvent.getInstance().getMusicPlayingEvent().onPlaying(isPlaying);
        }
        notificationControl.onPlaying(isPlaying);
    }
    public void play(){
        if (mMediaPlayer.isPlaying()==true){
            isPlaying =false;
            mMediaPlayer.pause();
            lenght = mMediaPlayer.getCurrentPosition();
        }
        else
        {
            isPlaying = true;
            mMediaPlayer.seekTo(lenght);
            mMediaPlayer.start();
        }
        if (GlobalEvent.getInstance().getMusicPlayingEvent()!=null)
        {
            GlobalEvent.getInstance().getMusicPlayingEvent().onPlaying(isPlaying);
        }
        notificationControl.onPlaying(isPlaying);
    }
    public void prev(){

        CurrentPlay.getInstance().Prev();
        try {
            mMediaPlayer.reset();
            mMediaPlayer.setDataSource(CurrentPlay.getInstance().GetCurrentSong().getPathForPlayer());
            mMediaPlayer.prepare();
            mMediaPlayer.start();
            isPlaying = true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (GlobalEvent.getInstance().getMusicPlayingEvent()!=null)
        {
            GlobalEvent.getInstance().getMusicPlayingEvent().onPlaying(isPlaying);
        }
        notificationControl.onPlaying(isPlaying);
    }
    public void next(){
        CurrentPlay.getInstance().Next();
        try {
            mMediaPlayer.reset();
            mMediaPlayer.setDataSource(CurrentPlay.getInstance().GetCurrentSong().getPathForPlayer());
            mMediaPlayer.prepare();
            mMediaPlayer.start();
            isPlaying = true;

        } catch (IOException e) {
            e.printStackTrace();
        }
        if (GlobalEvent.getInstance().getMusicPlayingEvent()!=null)
        {
            GlobalEvent.getInstance().getMusicPlayingEvent().onPlaying(isPlaying);
        }
        notificationControl.onPlaying(isPlaying);
    }
    public void stop(){

            Log.i(LOG_TAG, "Received Stop Foreground Intent");
            Toast.makeText(this, "Service Stoped", Toast.LENGTH_SHORT).show();
            release();
            notificationControl.CancelAll();
        if (GlobalEvent.getInstance().getMusicPlayingEvent()!=null)
        {
            GlobalEvent.getInstance().getMusicPlayingEvent().onPlaying(isPlaying);
        }
        notificationControl.onPlaying(isPlaying);
    }
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if (notificationControl==null)
        {
            notificationControl = new NotificationControl(getApplicationContext());
            GlobalEvent.getInstance().setMusicEventControl(this.musicEventControl);
        }

        if (intent.getAction().equals(Constants.ACTION.STARTFOREGROUND_ACTION)) {
            startForeground();
        } else if (intent.getAction().equals(Constants.ACTION.PREV_ACTION)) {
            prev();
        } else if (intent.getAction().equals(Constants.ACTION.PLAY_ACTION)) {
            play();
        } else if (intent.getAction().equals(Constants.ACTION.NEXT_ACTION)) {
            next();
        } else if (intent.getAction().equals(Constants.ACTION.STOPFOREGROUND_ACTION)) {
            stop();
        }


        return START_STICKY;
    }
}
