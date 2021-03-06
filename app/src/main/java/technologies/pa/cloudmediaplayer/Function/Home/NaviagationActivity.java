package technologies.pa.cloudmediaplayer.Function.Home;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import technologies.pa.cloudmediaplayer.Function.FolderDirectory.FolderExplorerActivity;
import technologies.pa.cloudmediaplayer.Function.Home.TabPlaylist.HomePlaylistFragment;
import technologies.pa.cloudmediaplayer.Function.Home.TabSong.HomeSongFragment;
import technologies.pa.cloudmediaplayer.Function.Player.PlayingActivity;
import technologies.pa.cloudmediaplayer.Function.Home.TabAlbum.HomeAlbumFragment;
import technologies.pa.cloudmediaplayer.Function.Home.TabArtist.HomeArtistFragment;
import technologies.pa.cloudmediaplayer.Pattern.FragmentPattern;
import technologies.pa.cloudmediaplayer.Pattern.ViewPagerAdapter;
import technologies.pa.cloudmediaplayer.R;

public class NaviagationActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private String TAG = "Home";
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    private ActionBarDrawerToggle toggle;

    @BindView(R.id.nav_view)
    NavigationView navigationView;

    @BindView(R.id.smallplaying)
    LinearLayout smallPlaying;
    @BindView(R.id.tabLayout)
    TabLayout tabLayout;
    @BindView(R.id.viewPager)
    ViewPager viewPager;
    @BindView(R.id.drawer_layout)
    DrawerLayout drawer;
    ViewPagerAdapter viewPagerAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_naviagation);
        ButterKnife.bind(this);
        initActionBar();
        setTitle(TAG);
        smallPlaying.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(NaviagationActivity.this, PlayingActivity.class);
                startActivity(it);
            }
        });
        setUpTabAdapter();
    }
    private void setUpTabAdapter(){
        String[] TabTitle = {"Songs","Albums","Artist","PlayList"};
        FragmentPattern[] FragmentList = {new HomeSongFragment(),new HomeAlbumFragment(),new HomeArtistFragment(),new HomePlaylistFragment()};
        viewPagerAdapter= new ViewPagerAdapter(getSupportFragmentManager(),TabTitle,FragmentList);
        viewPager.setAdapter(viewPagerAdapter);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.setTabsFromPagerAdapter(viewPagerAdapter);
    }
public void initActionBar(){
    toolbar= (Toolbar) findViewById(R.id.toolbar);
    setSupportActionBar(toolbar);


    toggle = new ActionBarDrawerToggle(
            this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
    drawer.setDrawerListener(toggle);
    toggle.syncState();


    navigationView.setNavigationItemSelectedListener(this);
}
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.naviagation, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_share) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_song) {
        } else if(id == R.id.nav_folder){
            Intent it = new Intent(NaviagationActivity.this, FolderExplorerActivity.class);
            startActivity(it);
        }
        else if (id == R.id.nav_album) {

        } else if (id == R.id.nav_artist) {

        } else if (id == R.id.nav_cloud_storage) {

        } else if(id == R.id.nav_playlist){

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}