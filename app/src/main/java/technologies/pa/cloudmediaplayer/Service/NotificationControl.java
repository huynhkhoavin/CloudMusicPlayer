package technologies.pa.cloudmediaplayer.Service;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.RemoteViews;

import technologies.pa.cloudmediaplayer.DaggerDI.MusicService.Implement.CurrentPlay;
import technologies.pa.cloudmediaplayer.Function.Home.NaviagationActivity;
import technologies.pa.cloudmediaplayer.Function.Player.PlayingActivity;
import technologies.pa.cloudmediaplayer.R;

import static android.content.ContentValues.TAG;

/**
 * Created by Dev02 on 3/10/2017.
 */

public class NotificationControl {

    private NotificationManager mNotificationManager;
    private RemoteViews views;
    private RemoteViews bigViews;
    private Notification status;
    private Context mContext;
    private MusicPlayingEvent mMusicPlayingEvent;

    public NotificationControl(Context context) {
        mContext = context;
    }
    public MusicPlayingEvent getMusicPlayingEvent() {
        return mMusicPlayingEvent;
    }

    public void onPlaying(boolean isPlaying) {
        if (isPlaying == true){
            views.setImageViewResource(R.id.status_bar_play,R.drawable.pause);
            bigViews.setImageViewResource(R.id.status_bar_play,R.drawable.pause);
        }
        else{
            views.setImageViewResource(R.id.status_bar_play,R.drawable.play);
            bigViews.setImageViewResource(R.id.status_bar_play,R.drawable.play);
        }
        mNotificationManager.notify(TAG, Notification.FLAG_ONGOING_EVENT,status);
    }

    public void UpdateSongInfo(){
        views.setTextViewText(R.id.status_bar_track_name, CurrentPlay.getInstance().GetCurrentSong().getTitle());
        bigViews.setTextViewText(R.id.status_bar_track_name, CurrentPlay.getInstance().GetCurrentSong().getTitle());

        views.setTextViewText(R.id.status_bar_artist_name, "Artist Name");
        bigViews.setTextViewText(R.id.status_bar_artist_name, "Artist Name");

        bigViews.setTextViewText(R.id.status_bar_album_name, "Album Name");
        mNotificationManager.notify(TAG, Notification.FLAG_ONGOING_EVENT,status);
    }
    public void showNotification() {
        mNotificationManager = (NotificationManager) mContext.getSystemService(Context.NOTIFICATION_SERVICE);
// Using RemoteViews to bind custom layouts into Notification
        views = new RemoteViews(mContext.getPackageName(),
                R.layout.music_status_bar);
        bigViews = new RemoteViews(mContext.getPackageName(),
                R.layout.music_status_bar_expand);
// showing default album image
        views.setViewVisibility(R.id.status_bar_icon, View.VISIBLE);
        views.setViewVisibility(R.id.status_bar_album_art, View.GONE);
        bigViews.setImageViewBitmap(R.id.status_bar_album_art,Constants.getDefaultAlbumArt(mContext));
        views.setImageViewResource(R.id.status_bar_album_art,R.drawable.default_album_art);

        Intent notificationIntent = new Intent(mContext, PlayingActivity.class);
        notificationIntent.setAction(Constants.ACTION.MAIN_ACTION);
        notificationIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        PendingIntent pendingIntent = PendingIntent.getActivity(mContext, 0,
                notificationIntent, 0);

        Intent previousIntent = new Intent(mContext, BackgroundMusicService.class);
        previousIntent.setAction(Constants.ACTION.PREV_ACTION);
        PendingIntent ppreviousIntent = PendingIntent.getService(mContext, 0,
                previousIntent, 0);

        Intent playIntent = new Intent(mContext, BackgroundMusicService.class);
        playIntent.setAction(Constants.ACTION.PLAY_ACTION);
        PendingIntent pplayIntent = PendingIntent.getService(mContext, 0,
                playIntent, 0);

        Intent nextIntent = new Intent(mContext, BackgroundMusicService.class);
        nextIntent.setAction(Constants.ACTION.NEXT_ACTION);
        PendingIntent pnextIntent = PendingIntent.getService(mContext, 0,
                nextIntent, 0);

        Intent closeIntent = new Intent(mContext, BackgroundMusicService.class);
        closeIntent.setAction(Constants.ACTION.STOPFOREGROUND_ACTION);
        PendingIntent pcloseIntent = PendingIntent.getService(mContext, 0,
                closeIntent, 0);

        views.setOnClickPendingIntent(R.id.status_bar_play, pplayIntent);
        bigViews.setOnClickPendingIntent(R.id.status_bar_play, pplayIntent);

        views.setOnClickPendingIntent(R.id.status_bar_next, pnextIntent);
        bigViews.setOnClickPendingIntent(R.id.status_bar_next, pnextIntent);

        views.setOnClickPendingIntent(R.id.status_bar_prev, ppreviousIntent);
        bigViews.setOnClickPendingIntent(R.id.status_bar_prev, ppreviousIntent);

        views.setOnClickPendingIntent(R.id.status_bar_collapse, pcloseIntent);
        bigViews.setOnClickPendingIntent(R.id.status_bar_collapse, pcloseIntent);

        status = new Notification.Builder(mContext).build();
        status.contentView = views;
        status.bigContentView = bigViews;
        status.flags = Notification.FLAG_ONGOING_EVENT;
        status.icon = R.drawable.ic_launcher;
        status.contentIntent = pendingIntent;
    }
    public void CancelAll(){
        mNotificationManager.cancelAll();
    }
}
