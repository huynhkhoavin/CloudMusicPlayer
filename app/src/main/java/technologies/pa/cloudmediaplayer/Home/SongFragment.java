package technologies.pa.cloudmediaplayer.Home;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.BindView;
import technologies.pa.cloudmediaplayer.Pattern.FragmentPattern;
import technologies.pa.cloudmediaplayer.R;

/**
 * Created by Dev02 on 2/28/2017.
 */

public class SongFragment extends FragmentPattern {
    @BindView(R.id.recycleView)
    RecyclerView recyclerView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v =  inflater.inflate(R.layout.fragment_songs,container,false);

        return v;

    }
}
