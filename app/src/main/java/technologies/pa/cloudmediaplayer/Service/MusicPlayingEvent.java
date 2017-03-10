package technologies.pa.cloudmediaplayer.Service;

/**
 * Created by Dev02 on 3/10/2017.
 */

public interface MusicPlayingEvent {
    void onPlaying(boolean isPlaying);
    void onPause(boolean isPause);
}
