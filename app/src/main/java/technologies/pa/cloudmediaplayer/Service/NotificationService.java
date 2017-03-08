package technologies.pa.cloudmediaplayer.Service;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.appwidget.AppWidgetManager;
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

import technologies.pa.cloudmediaplayer.Function.Home.NaviagationActivity;
import technologies.pa.cloudmediaplayer.R;

import static android.content.ContentValues.TAG;

/**
 * Created by Dev02 on 3/6/2017.
 */

public class NotificationService extends Service {
    private static final int NOTIFICATION_ID = 0;
    Notification status;
    private final String LOG_TAG = "NotificationService";
    private String SongPath = "";
    private boolean isPlaying = false;
    private int lenght = 0;
    private final IBinder mBinder = new LocalBinder();
    AppWidgetManager manager;
    NotificationManager mNotificationManager;
    public class LocalBinder extends Binder{
        public NotificationService getServiceInstance(){
            return NotificationService.this;
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

    @Override
    public void onDestroy() {
        super.onDestroy();

    }
    public void release(){
        SongPath = "";
        mMediaPlayer.release();
        mMediaPlayer =null;
        isPlaying = false;
    }
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if (intent.getAction().equals(Constants.ACTION.STARTFOREGROUND_ACTION)) {
            showNotification();
            Toast.makeText(this, "Service Started", Toast.LENGTH_SHORT).show();
            if (mMediaPlayer==null){
                mMediaPlayer = new MediaPlayer();
                try {
                    mMediaPlayer.setDataSource(SongPath);
                    mMediaPlayer.prepare();
                    mMediaPlayer.start();
                    views.setImageViewResource(R.id.status_bar_play,R.drawable.pause);
                    bigViews.setImageViewResource(R.id.status_bar_play,R.drawable.pause);
                    mNotificationManager.notify(TAG, Notification.FLAG_ONGOING_EVENT,status);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            else
            {
                mMediaPlayer.pause();
                mMediaPlayer.release();
                try {
                    mMediaPlayer = new MediaPlayer();
                    mMediaPlayer.setDataSource(SongPath);
                    mMediaPlayer.prepare();
                    mMediaPlayer.start();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            isPlaying =true;
            views.setImageViewResource(R.id.status_bar_play,R.drawable.pause);

        } else if (intent.getAction().equals(Constants.ACTION.PREV_ACTION)) {
            Toast.makeText(this, "Clicked Previous", Toast.LENGTH_SHORT).show();
            Log.i(LOG_TAG, "Clicked Previous");
        } else if (intent.getAction().equals(Constants.ACTION.PLAY_ACTION)) {
                if (mMediaPlayer.isPlaying()==true){
                    isPlaying =false;
                    mMediaPlayer.pause();
                    lenght = mMediaPlayer.getCurrentPosition();
                    views.setImageViewResource(R.id.status_bar_play,R.drawable.play);
                    bigViews.setImageViewResource(R.id.status_bar_play,R.drawable.play);
                    mNotificationManager.notify(TAG, Notification.FLAG_ONGOING_EVENT,status);

                }
                else
                {
                    views.setImageViewResource(R.id.status_bar_play,R.drawable.pause);
                    bigViews.setImageViewResource(R.id.status_bar_play,R.drawable.pause);
                    mNotificationManager.notify(TAG, Notification.FLAG_ONGOING_EVENT,status);
                    isPlaying = true;
                    mMediaPlayer.seekTo(lenght);
                    mMediaPlayer.start();
                }

        } else if (intent.getAction().equals(Constants.ACTION.NEXT_ACTION)) {
            Toast.makeText(this, "Clicked Next", Toast.LENGTH_SHORT).show();
            Log.i(LOG_TAG, "Clicked Next");
        } else if (intent.getAction().equals(
                Constants.ACTION.STOPFOREGROUND_ACTION)) {
            Log.i(LOG_TAG, "Received Stop Foreground Intent");
            Toast.makeText(this, "Service Stoped", Toast.LENGTH_SHORT).show();
            mNotificationManager.cancelAll();
            release();
            //stopForeground(true);
            //stopSelf();
        }
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

        Intent previousIntent = new Intent(this, NotificationService.class);
        previousIntent.setAction(Constants.ACTION.PREV_ACTION);
        PendingIntent ppreviousIntent = PendingIntent.getService(this, 0,
                previousIntent, 0);

        Intent playIntent = new Intent(this, NotificationService.class);
        playIntent.setAction(Constants.ACTION.PLAY_ACTION);
        PendingIntent pplayIntent = PendingIntent.getService(this, 0,
                playIntent, 0);

        Intent nextIntent = new Intent(this, NotificationService.class);
        nextIntent.setAction(Constants.ACTION.NEXT_ACTION);
        PendingIntent pnextIntent = PendingIntent.getService(this, 0,
                nextIntent, 0);

        Intent closeIntent = new Intent(this, NotificationService.class);
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

        views.setTextViewText(R.id.status_bar_track_name, "File Title");
        bigViews.setTextViewText(R.id.status_bar_track_name, "File Title");

        views.setTextViewText(R.id.status_bar_artist_name, "Artist Name");
        bigViews.setTextViewText(R.id.status_bar_artist_name, "Artist Name");

        bigViews.setTextViewText(R.id.status_bar_album_name, "Album Name");

        status = new Notification.Builder(this).build();
        status.contentView = views;
        status.bigContentView = bigViews;
        status.flags = Notification.FLAG_ONGOING_EVENT;
        status.icon = R.drawable.ic_launcher;
        status.contentIntent = pendingIntent;
        //startForeground(Constants.NOTIFICATION_ID.FOREGROUND_SERVICE, status);
    }
    public void updateNotifycation(){
        bigViews.setImageViewBitmap(R.id.status_bar_album_art,Constants.getDefaultAlbumArt(this));
        views.setImageViewResource(R.id.status_bar_album_art_small,R.drawable.default_album_art);
        views.setImageViewResource(R.id.status_bar_play,R.drawable.play);
        //views.setImageViewResource(R.id.status_bar_play,R.drawable.pause);
        bigViews.setImageViewResource(R.id.status_bar_play,R.drawable.play);
        //bigViews.setImageViewResource(R.id.status_bar_play,R.drawable.pause);
        mNotificationManager.notify(TAG, Notification.FLAG_ONGOING_EVENT,status);
    }
    public void setMusicPath(String Url){
        this.SongPath = Url;
    }
}
