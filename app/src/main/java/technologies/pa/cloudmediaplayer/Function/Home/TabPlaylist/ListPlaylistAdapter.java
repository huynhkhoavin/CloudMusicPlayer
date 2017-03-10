package technologies.pa.cloudmediaplayer.Function.Home.TabPlaylist;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import technologies.pa.cloudmediaplayer.Object.Playlist;
import technologies.pa.cloudmediaplayer.Pattern.RecycleViewAdapterPattern;
import technologies.pa.cloudmediaplayer.R;
import technologies.pa.cloudmediaplayer.Tool.ArrayConvert;

/**
 * Created by Dev02 on 3/1/2017.
 */

public class ListPlaylistAdapter extends RecycleViewAdapterPattern {
    public ListPlaylistAdapter(Context mContext, ArrayList<Object> dataSource) {
        super(mContext, dataSource);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = mLayoutInflater.inflate(R.layout.single_playlist,parent,false);
        return new PlaylistViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        PlaylistViewHolder playlistViewHolder = (PlaylistViewHolder)holder;
        ArrayList<Playlist> playlists = ArrayConvert.toArrayList(getDataSource());
        playlistViewHolder.playlistName.setText(playlists.get(position).getPlaylistName());
    }
}
