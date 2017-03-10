package technologies.pa.cloudmediaplayer.Service;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.RemoteViews;
import android.widget.Toast;

import java.io.IOException;

import technologies.pa.cloudmediaplayer.DaggerDI.MusicService.Implement.CurrentPlay;
import technologies.pa.cloudmediaplayer.Function.Home.NaviagationActivity;
import technologies.pa.cloudmediaplayer.R;

import static android.content.ContentValues.TAG;

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
    private MusicPlayingEvent mMusicEvent;
    NotificationManager mNotificationManager;
    public class LocalBinder extends Binder{
        public BackgroundMusicService getServiceInstance(){
            return BackgroundMusicService.this;
        }
    }
    RemoteViews views;
    RemoteViews bigViews;
    private MediaPlayer mMediaPlayer = null;
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }

    public void SetMusicEvent(MusicEvent musicEvent){
    }
    @Override
    public void onDestroy() {
        super.onDestroy();

    }
    public void release(){
        mMediaPlayer.release();
        mMediaPlayer =null;
        isPlaying = false;
    }
    public void UpdateSongInfo(){
        views.setTextViewText(R.id.status_bar_track_name, CurrentPlay.getInstance().GetCurrentSong().getTitle());
        bigViews.setTextViewText(R.id.status_bar_track_name, CurrentPlay.getInstance().GetCurrentSong().getTitle());

        views.setTextViewText(R.id.status_bar_artist_name, "Artist Name");
        bigViews.setTextViewText(R.id.status_bar_artist_name, "Artist Name");

        bigViews.setTextViewText(R.id.status_bar_album_name, "Album Name");
        mNotificationManager.notify(TAG,Notification.FLAG_ONGOING_EVENT,status);
    }
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if (intent.getAction().equals(Constants.ACTION.STARTFOREGROUND_ACTION)) {
            showNotification();
            Toast.makeText(this, "Service Started", Toast.LENGTH_SHORT).show();
            if (mMediaPlayer==null){
                mMediaPlayer = new MediaPlayer();
                try {
                    mMediaPlayer.setDataSource(CurrentPlay.getInstance().GetCurrentSong().getPathForPlayer());
                    mMediaPlayer.prepare();
                    mMediaPlayer.start();

                    UpdateSongInfo();
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
                    UpdateSongInfo();

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            isPlaying =true;

        } else if (intent.getAction().equals(Constants.ACTION.PREV_ACTION)) {

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

        } else if (intent.getAction().equals(Constants.ACTION.PLAY_ACTION)) {
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

        } else if (intent.getAction().equals(Constants.ACTION.NEXT_ACTION)) {
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
        } else if (intent.getAction().equals(
                Constants.ACTION.STOPFOREGROUND_ACTION)) {
            Log.i(LOG_TAG, "Received Stop Foreground Intent");
            Toast.makeText(this, "Service Stoped", Toast.LENGTH_SHORT).show();
            mNotificationManager.cancelAll();
            release();
        }
        if (isPlaying==true){
            mMusicEvent.onPlaying(isPlaying);
            views.setImageViewResource(R.id.status_bar_play,R.drawable.pause);
            bigViews.setImageViewResource(R.id.status_bar_play,R.drawable.pause);
        }
        else
        {
            mMusicEvent.onPause(isPlaying);
            views.setImageViewResource(R.id.status_bar_play,R.drawable.play);
            bigViews.setImageViewResource(R.id.status_bar_play,R.drawable.play);
        }
        UpdateSongInfo();
        mNotificationManager.notify(TAG, Notification.FLAG_ONGOING_EVENT,status);
        return START_STICKY;
    }
    private void showNotification() {
        mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
// Using RemoteViews to bind custom layouts into Notification
        views = new RemoteViews(getPackageName(),
                R.layout.music_status_bar);
        bigViews = new RemoteViews(getPackageName(),
                R.layout.music_status_bar_expand);
// showing default album image
        views.setViewVisibility(R.id.status_bar_icon, View.VISIBLE);
        views.setViewVisibility(R.id.status_bar_album_art, View.GONE);
        bigViews.setImageViewBitmap(R.id.status_bar_album_art,Constants.getDefaultAlbumArt(this));
        views.setImageViewResource(R.id.status_bar_album_art,R.drawable.default_album_art);

        Intent notificationIntent = new Intent(this, NaviagationActivity.class);
        notificationIntent.setAction(Constants.ACTION.MAIN_ACTION);
        notificationIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0,
                notificationIntent, 0);

        Intent previousIntent = new Intent(this, BackgroundMusicService.class);
        previousIntent.setAction(Constants.ACTION.PREV_ACTION);
        PendingIntent ppreviousIntent = PendingIntent.getService(this, 0,
                previousIntent, 0);

        Intent playIntent = new Intent(this, BackgroundMusicService.class);
        playIntent.setAction(Constants.ACTION.PLAY_ACTION);
        PendingIntent pplayIntent = PendingIntent.getService(this, 0,
                playIntent, 0);

        Intent nextIntent = new Intent(this, BackgroundMusicService.class);
        nextIntent.setAction(Constants.ACTION.NEXT_ACTION);
        PendingIntent pnextIntent = PendingIntent.getService(this, 0,
                nextIntent, 0);

        Intent closeIntent = new Intent(this, BackgroundMusicService.class);
        closeIntent.setAction(Constants.ACTION.STOPFOREGROUND_ACTION);
        PendingIntent pcloseIntent = PendingIntent.getService(this, 0,
                closeIntent, 0);

        views.setOnClickPendingIntent(R.id.status_bar_play, pplayIntent);
        bigViews.setOnClickPendingIntent(R.id.status_bar_play, pplayIntent);

        views.setOnClickPendingIntent(R.id.status_bar_next, pnextIntent);
        bigViews.setOnClickPendingIntent(R.id.status_bar_next, pnextIntent);

        views.setOnClickPendingIntent(R.id.status_bar_prev, ppreviousIntent);
        bigViews.setOnClickPendingIntent(R.id.status_bar_prev, ppreviousIntent);

        views.setOnClickPendingIntent(R.id.status_bar_collapse, pcloseIntent);
        bigViews.setOnClickPendingIntent(R.id.status_bar_collapse, pcloseIntent);

        status = new Notification.Builder(this).build();
        status.contentView = views;
        status.bigContentView = bigViews;
        status.flags = Notification.FLAG_ONGOING_EVENT;
        status.icon = R.drawable.ic_launcher;
        status.contentIntent = pendingIntent;
        //startForeground(Constants.NOTIFICATION_ID.FOREGROUND_SERVICE, status);
    }
}
