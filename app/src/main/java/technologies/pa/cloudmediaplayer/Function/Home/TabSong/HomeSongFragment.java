package technologies.pa.cloudmediaplayer.Function.Home.TabSong;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import technologies.pa.cloudmediaplayer.Object.Song;
import technologies.pa.cloudmediaplayer.Pattern.FragmentPattern;
import technologies.pa.cloudmediaplayer.R;
import technologies.pa.cloudmediaplayer.Tool.ArrayConvert;

/**
 * Created by Dev02 on 2/28/2017.
 */

public class HomeSongFragment extends FragmentPattern {
    private Song[] songs = {
            new Song("State of Grace","Taylor Swift"),
            new Song("Red","Taylor swift"),
            new Song("Treacherous","Taylor swift"),
            new Song("State of Grace","Taylor Swift"),
            new Song("Red","Taylor swift"),
            new Song("Treacherous","Taylor swift"),
            new Song("State of Grace","Taylor Swift"),
            new Song("Red","Taylor swift"),
            new Song("Treacherous","Taylor swift")};
    @BindView(R.id.recycleView)
    RecyclerView recyclerView;
    ListSongAdapter listSongAdapter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v =  inflater.inflate(R.layout.fragment_home_songs,container,false);
        ButterKnife.bind(this,v);
        ShowSongList(ArrayConvert.toArrayList(songs));
        return v;
    }
    public void ShowSongList(ArrayList<Song> songs) {
        listSongAdapter = new ListSongAdapter(getContext(),ArrayConvert.toObjectArray(songs));
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(listSongAdapter);
    }
}
