package technologies.pa.cloudmediaplayer.Function.Home.TabArtist;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.BindView;
import butterknife.ButterKnife;
import technologies.pa.cloudmediaplayer.Object.Artist;
import technologies.pa.cloudmediaplayer.Pattern.FragmentPattern;
import technologies.pa.cloudmediaplayer.R;
import technologies.pa.cloudmediaplayer.Tool.ArrayConvert;

/**
 * Created by Dev02 on 3/1/2017.
 */

public class HomeArtistFragment extends FragmentPattern {
    Artist[] artists = {new Artist("Taylor Swift",R.drawable.taylor_swift),
            new Artist("Taylor Swift",R.drawable.taylor_swift),
            new Artist("Taylor Swift",R.drawable.taylor_swift),
            new Artist("Taylor Swift",R.drawable.taylor_swift),
            new Artist("Taylor Swift",R.drawable.taylor_swift),
            new Artist("Taylor Swift",R.drawable.taylor_swift),
            new Artist("Taylor Swift",R.drawable.taylor_swift),
            new Artist("Taylor Swift",R.drawable.taylor_swift),
            new Artist("Taylor Swift",R.drawable.taylor_swift),
            new Artist("Taylor Swift",R.drawable.taylor_swift),
            new Artist("Taylor Swift",R.drawable.taylor_swift),
            new Artist("Taylor Swift",R.drawable.taylor_swift),
            new Artist("Taylor Swift",R.drawable.taylor_swift)};
    @BindView(R.id.recycleView)
    RecyclerView recyclerView;
    ListArtistAdapter listArtistAdapter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_home_artist,container,false);
        ButterKnife.bind(this,v);
        ShowSongList(artists);
        return v;
    }
    public void ShowSongList(Artist[] artists) {
        listArtistAdapter = new ListArtistAdapter(getContext(), ArrayConvert.toObjectArray(artists));
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(listArtistAdapter);
    }
}
