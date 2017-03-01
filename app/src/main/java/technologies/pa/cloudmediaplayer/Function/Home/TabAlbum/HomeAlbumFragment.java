package technologies.pa.cloudmediaplayer.Function.Home.TabAlbum;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.BindView;
import butterknife.ButterKnife;
import technologies.pa.cloudmediaplayer.Object.Album;
import technologies.pa.cloudmediaplayer.Pattern.FragmentPattern;
import technologies.pa.cloudmediaplayer.R;

/**
 * Created by Dev02 on 3/1/2017.
 */

public class HomeAlbumFragment extends FragmentPattern {
    Album[] albums = {new Album(R.drawable.album,"Sky World","Two Step From Hell"),
            new Album(R.drawable.album,"Sky World","Two Step From Hell"),
            new Album(R.drawable.album,"Sky World","Two Step From Hell"),
            new Album(R.drawable.album,"Sky World","Two Step From Hell"),
            new Album(R.drawable.album,"Sky World","Two Step From Hell"),
            new Album(R.drawable.album,"Sky World","Two Step From Hell"),
            new Album(R.drawable.album,"Sky World","Two Step From Hell"),
            new Album(R.drawable.album,"Sky World","Two Step From Hell"),
            new Album(R.drawable.album,"Sky World","Two Step From Hell"),
            new Album(R.drawable.album,"Sky World","Two Step From Hell"),
            new Album(R.drawable.album,"Sky World","Two Step From Hell"),
            new Album(R.drawable.album,"Sky World","Two Step From Hell")};
    @BindView(R.id.recycleView)
    RecyclerView recyclerView;
    ListAlbumAdapter listAlbumAdapter;
    GridLayoutManager gridLayoutManager;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_home_album,container,false);
        ButterKnife.bind(this,v);
        ShowSongList(albums);
        return v;
    }
    public void ShowSongList(Album[] albums) {
        gridLayoutManager = new GridLayoutManager(getContext(),2);
        listAlbumAdapter = new ListAlbumAdapter(getContext(),albums);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setAdapter(listAlbumAdapter);
    }
}
