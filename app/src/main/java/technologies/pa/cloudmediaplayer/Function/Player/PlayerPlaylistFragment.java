package technologies.pa.cloudmediaplayer.Function.Player;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import technologies.pa.cloudmediaplayer.Function.Home.TabSong.ListSongAdapter;
import technologies.pa.cloudmediaplayer.Object.Song;
import technologies.pa.cloudmediaplayer.Pattern.FragmentPattern;
import technologies.pa.cloudmediaplayer.R;
import technologies.pa.cloudmediaplayer.Tool.ArrayConvert;

/**
 * Created by Dev02 on 3/1/2017.
 */

public class PlayerPlaylistFragment extends FragmentPattern {
    private Song[] songs = {
            new Song("State of Grace","Taylor Swift",R.drawable.taylor_swift),
            new Song("Red","Taylor swift",R.drawable.taylor_swift),
            new Song("Treacherous","Taylor swift",R.drawable.taylor_swift),
            new Song("State of Grace","Taylor Swift",R.drawable.taylor_swift),
            new Song("Red","Taylor swift",R.drawable.taylor_swift),
            new Song("Treacherous","Taylor swift",R.drawable.taylor_swift),
            new Song("State of Grace","Taylor Swift",R.drawable.taylor_swift),
            new Song("Red","Taylor swift",R.drawable.taylor_swift),
            new Song("Treacherous","Taylor swift",R.drawable.taylor_swift)};
    @BindView(R.id.recycleView)
    RecyclerView recyclerView;
    ListSongAdapter listSongAdapter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v =  inflater.inflate(R.layout.fragment_playing_playlist,container,false);
        ButterKnife.bind(this,v);
        ShowSongList(ArrayConvert.toArrayList(songs));
        return v;
    }
    public void ShowSongList(ArrayList<Song> songs) {
        listSongAdapter = new ListSongAvatarAdapter(getContext(), ArrayConvert.toObjectArray(songs));
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(listSongAdapter);
    }
}
