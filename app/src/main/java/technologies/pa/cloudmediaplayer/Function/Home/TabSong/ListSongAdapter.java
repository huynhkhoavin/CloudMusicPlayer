package technologies.pa.cloudmediaplayer.Function.Home.TabSong;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import technologies.pa.cloudmediaplayer.Object.Song;
import technologies.pa.cloudmediaplayer.Pattern.RecycleViewAdapterPattern;
import technologies.pa.cloudmediaplayer.R;
import technologies.pa.cloudmediaplayer.Tool.ArrayConvert;

/**
 * Created by Dev02 on 2/28/2017.
 */

public class ListSongAdapter extends RecycleViewAdapterPattern {
    public ListSongAdapter(Context mContext, ArrayList<Object> dataSource) {
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
        ArrayList<Song> songs = ArrayConvert.toArrayList(getDataSource());
        mViewHolder.tv_songName.setText(songs.get(position).getSongTitle());
        mViewHolder.tv_songSinger.setText(songs.get(position).getSingerName());
    }
}
