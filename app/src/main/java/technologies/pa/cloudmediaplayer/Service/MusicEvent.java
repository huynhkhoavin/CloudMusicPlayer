package technologies.pa.cloudmediaplayer.Service;

import android.media.MediaPlayer;

/**
 * Created by Dev02 on 3/9/2017.
 */

public interface MusicEvent {
    void onStartForeground(MediaPlayer mediaPlayer);
    void onPrev(MediaPlayer mediaPlayer);
    void onPlay(MediaPlayer mediaPlayer);
    void onPause(MediaPlayer mediaPlayer);
    void onNext(MediaPlayer mediaPlayer);
    void onReplaceSong(MediaPlayer mediaPlayer);
}
