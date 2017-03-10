package technologies.pa.cloudmediaplayer.Service;

/**
 * Created by Dev02 on 3/10/2017.
 */

public interface MusicPlayingEvent {
    void onStart();
    void onPlaying(boolean isPlaying);
}
