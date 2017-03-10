package technologies.pa.cloudmediaplayer.Tool;

/**
 * Created by Dev02 on 3/10/2017.
 */

public class PauseMusic {
    private boolean isPlaying;

    public PauseMusic(boolean isPlaying) {
        this.isPlaying = isPlaying;
    }

    public boolean isPlaying() {
        return isPlaying;
    }

    public void setPlaying(boolean playing) {
        isPlaying = playing;
    }
}
