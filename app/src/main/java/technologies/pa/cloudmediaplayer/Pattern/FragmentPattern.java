package technologies.pa.cloudmediaplayer.Pattern;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import technologies.pa.cloudmediaplayer.R;


//Tab Fragment Pattern
public class FragmentPattern extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState){
        View v =  inflater.inflate(R.layout.fragment_default,container,false);
        return v;
    }
}
