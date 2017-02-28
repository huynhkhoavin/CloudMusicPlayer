package technologies.pa.cloudmediaplayer.Home;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.BindView;
import butterknife.ButterKnife;
import technologies.pa.cloudmediaplayer.Pattern.FragmentPattern;
import technologies.pa.cloudmediaplayer.Pattern.ViewPagerAdapter;
import technologies.pa.cloudmediaplayer.R;

/**
 * Created by Dev02 on 2/28/2017.
 */

public class MyMusicFragment extends FragmentPattern {
    @BindView(R.id.tabLayout)
    TabLayout tabLayout;
    @BindView(R.id.viewPager)
    ViewPager viewPager;
    ViewPagerAdapter viewPagerAdapter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_my_music,container,false);
        ButterKnife.bind(this,v);
        setUpTabAdapter(savedInstanceState);
        return v;
    }
    private void setUpTabAdapter(Bundle savedInstanceState){
        String[] TabTitle = {"Songs","Albums","Artist","PlayList"};
        FragmentPattern[] FragmentList = {new SongFragment(),new FragmentPattern(),new FragmentPattern(),new FragmentPattern()};
        viewPagerAdapter= new ViewPagerAdapter(getActivity().getSupportFragmentManager(),TabTitle,FragmentList);
        viewPager.setAdapter(viewPagerAdapter);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.setTabsFromPagerAdapter(viewPagerAdapter);
    }
}
