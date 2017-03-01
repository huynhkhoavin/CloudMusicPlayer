package technologies.pa.cloudmediaplayer.Home.TabAlbum;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import technologies.pa.cloudmediaplayer.Pattern.FragmentPattern;
import technologies.pa.cloudmediaplayer.R;

/**
 * Created by Dev02 on 3/1/2017.
 */

public class HomeAlbumFragment extends FragmentPattern {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_home_album,container,false);
        return v;
    }
}
