package technologies.pa.cloudmediaplayer.Function.Home.TabSong;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import technologies.pa.cloudmediaplayer.Object.Song;
import technologies.pa.cloudmediaplayer.Pattern.RecycleViewAdapterPattern;
import technologies.pa.cloudmediaplayer.R;

/**
 * Created by Dev02 on 2/28/2017.
 */

public class ListSongAdapter extends RecycleViewAdapterPattern {
    public ListSongAdapter(Context mContext, Object[] dataSource) {
        super(mContext, dataSource);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = mLayoutInflater.inflate(R.layout.single_song,parent,false);
        return new SongViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        SongViewHolder mViewHolder = (SongViewHolder) holder;
        Song[] songs = (Song[])getDataSource();
        mViewHolder.tv_songName.setText(songs[position].getSongName());
        mViewHolder.tv_songSinger.setText(songs[position].getSingerName());
    }
}
