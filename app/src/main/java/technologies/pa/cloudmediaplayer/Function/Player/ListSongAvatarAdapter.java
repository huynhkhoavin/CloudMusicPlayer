package technologies.pa.cloudmediaplayer.Function.Player;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import technologies.pa.cloudmediaplayer.Function.Home.TabSong.ListSongAdapter;
import technologies.pa.cloudmediaplayer.Object.Song;
import technologies.pa.cloudmediaplayer.R;
import technologies.pa.cloudmediaplayer.Tool.ArrayConvert;

/**
 * Created by Dev02 on 3/1/2017.
 */

public class ListSongAvatarAdapter extends ListSongAdapter {
    public ListSongAvatarAdapter(Context mContext, ArrayList<Object> dataSource) {
        super(mContext, dataSource);
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = mLayoutInflater.inflate(R.layout.single_song_avatar,parent,false);
        return new SongAvatarViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        SongAvatarViewHolder mViewHolder = (SongAvatarViewHolder) holder;
        ArrayList<Song> songs = ArrayConvert.toArrayList(getDataSource());
        mViewHolder.tv_songName.setText(songs.get(position).getSongTitle());
        mViewHolder.tv_songSinger.setText(songs.get(position).getSingerName());
        mViewHolder.avatar.setBackgroundResource(songs.get(position).getUrl());
    }
}
