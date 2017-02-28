package technologies.pa.cloudmediaplayer.Playing;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chabbal.slidingdotsplash.SlidingSplashView;

import butterknife.BindView;
import butterknife.ButterKnife;
import technologies.pa.cloudmediaplayer.Home.SongFragment;
import technologies.pa.cloudmediaplayer.Pattern.FragmentPattern;
import technologies.pa.cloudmediaplayer.Pattern.ViewPagerAdapter;
import technologies.pa.cloudmediaplayer.R;

/**
 * Created by Dev02 on 2/28/2017.
 */

public class PlayingFragment extends FragmentPattern {
    @BindView(R.id.viewPager)
    ViewPager viewPager;
    @BindView(R.id.tab_layout)
    TabLayout tabLayout;
    ViewPagerAdapter viewPagerAdapter;
    ViewPager.OnPageChangeListener listener;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_playing,container,false);
        ButterKnife.bind(this,v);
        setUpTabAdapter();
        return  v;
    }
    private void setUpTabAdapter(){
        String[] TabTitle = {"","",""};
        FragmentPattern[] FragmentList = {new SongFragment(),new FragmentPattern(),new FragmentPattern()};
        viewPagerAdapter = new ViewPagerAdapter(getActivity().getSupportFragmentManager(),TabTitle,FragmentList);
        viewPager.setAdapter(viewPagerAdapter);
        tabLayout.setupWithViewPager(viewPager, true);
    }
}
