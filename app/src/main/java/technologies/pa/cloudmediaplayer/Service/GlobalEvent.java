package technologies.pa.cloudmediaplayer.Service;

/**
 * Created by Dev02 on 3/10/2017.
 */
public class GlobalEvent{
    private MusicPlayingEvent musicPlayingEvent;
    private MusicEventControl musicEventControl;

    private static GlobalEvent instance;
    private GlobalEvent(){

    }
    public static GlobalEvent getInstance(){
        if (instance==null){
            instance = new GlobalEvent();
        }
        return instance;
    }
    public MusicPlayingEvent getMusicPlayingEvent(){
        return musicPlayingEvent;
    }

    public void setMusicPlayingEvent(MusicPlayingEvent musicPlayingEvent) {
        this.musicPlayingEvent = musicPlayingEvent;
    }

    public MusicEventControl getMusicEventControl() {
        return musicEventControl;
    }

    public void setMusicEventControl(MusicEventControl musicEventControl) {
        this.musicEventControl = musicEventControl;
    }
}
