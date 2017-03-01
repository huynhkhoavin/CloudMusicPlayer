package technologies.pa.cloudmediaplayer.Function.Player;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import technologies.pa.cloudmediaplayer.Pattern.FragmentPattern;
import technologies.pa.cloudmediaplayer.Pattern.ViewPagerAdapter;
import technologies.pa.cloudmediaplayer.R;

/**
 * Created by Dev02 on 3/1/2017.
 */

public class PlayingActivity extends AppCompatActivity {

    @BindView(R.id.viewPager)
    ViewPager viewPager;
    @BindView(R.id.tab_layout)
    TabLayout tabLayout;
    ViewPagerAdapter viewPagerAdapter;
    ViewPager.OnPageChangeListener listener;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_playing);
        ButterKnife.bind(this);
        setUpTabAdapter();
    }

    private void setUpTabAdapter(){
        FragmentPattern[] FragmentList = {new PlayerPlaylistFragment(),new PlayerLyricFragment(),new FragmentPattern()};
        viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager(),FragmentList);
        viewPager.setAdapter(viewPagerAdapter);
        tabLayout.setupWithViewPager(viewPager, true);
    }
}
