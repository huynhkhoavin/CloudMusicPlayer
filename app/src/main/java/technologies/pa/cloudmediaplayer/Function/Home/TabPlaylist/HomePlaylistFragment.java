package technologies.pa.cloudmediaplayer.Function.Home.TabPlaylist;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.BindView;
import butterknife.ButterKnife;
import technologies.pa.cloudmediaplayer.Object.Playlist;
import technologies.pa.cloudmediaplayer.Pattern.FragmentPattern;
import technologies.pa.cloudmediaplayer.R;
import technologies.pa.cloudmediaplayer.Tool.ArrayConvert;

/**
 * Created by Dev02 on 3/1/2017.
 */

public class HomePlaylistFragment extends FragmentPattern {
    Playlist[] listPlaylist = {new Playlist("Best Of Rock"),new Playlist("OST"),new Playlist("Slow Rock"),new Playlist("Heavy Metal")};
    @BindView(R.id.recycleView)
    RecyclerView recyclerView;
    ListPlaylistAdapter listPlaylistAdapter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_home_playlist,container,false);
        ButterKnife.bind(this,v);
        ShowPlaylist(listPlaylist);
        return v;
    }
    public void ShowPlaylist(Playlist[] listPlaylist){
        listPlaylistAdapter = new ListPlaylistAdapter(getContext(), ArrayConvert.toObjectArray(listPlaylist));
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(listPlaylistAdapter);
    }
}
