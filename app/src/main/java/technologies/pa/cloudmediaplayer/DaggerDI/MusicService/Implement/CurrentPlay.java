package technologies.pa.cloudmediaplayer.DaggerDI.MusicService.Implement;

import java.util.ArrayList;

import technologies.pa.cloudmediaplayer.Folder.CurrentFile;
import technologies.pa.cloudmediaplayer.Folder.File;
import technologies.pa.cloudmediaplayer.Sqlite.DatabaseHelper;

/**
 * Created by Dev02 on 3/9/2017.
 */

public class CurrentPlay{
    private static CurrentPlay instance;
    private  ArrayList<File> playingPlaylist;
    private int CurrentPlayingSong;
    private DatabaseHelper db;

    public static CurrentPlay getInstance(){
        if (instance == null){
            instance = new CurrentPlay();
        }
        return instance;
    }
    public CurrentPlay() {
        this.playingPlaylist = new ArrayList<>();
        CurrentPlayingSong = -1;
    }

    public void SetDataHelper(DatabaseHelper db) {
        this.db = db;
    }
    public void getCurrentDataFromDB(){
        CurrentFile currF = db.getCurrentFile();
        playingPlaylist = db.getAllFileFromFolder(currF.getFolderId());
        CurrentPlayingSong = currF.getFilePos();
    }
    public void UpdateData(int newFolder, int newPosition) {

        db.UpdateCurrentPlayingFolder(newFolder,newPosition);
        playingPlaylist = db.getCurrentPlayingFileList();
        CurrentPlayingSong = newPosition;
    }

    public File GetCurrentSong(){
        return playingPlaylist.get(CurrentPlayingSong);
    }

    public void Prev() {
        if (CurrentPlayingSong>0)
        CurrentPlayingSong--;
    }

    public void Next() {
        if(CurrentPlayingSong<playingPlaylist.size()-1)
        CurrentPlayingSong++;
    }
}
