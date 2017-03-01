package technologies.pa.cloudmediaplayer.Player;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.BindView;
import butterknife.ButterKnife;
import technologies.pa.cloudmediaplayer.Home.TabSong.ListSongAdapter;
import technologies.pa.cloudmediaplayer.Object.Song;
import technologies.pa.cloudmediaplayer.Pattern.FragmentPattern;
import technologies.pa.cloudmediaplayer.R;

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
        ShowSongList(songs);
        return v;
    }
    public void ShowSongList(Song[] songs) {
        listSongAdapter = new ListSongAvatarAdapter(getContext(),songs);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(listSongAdapter);
    }
}
