package technologies.pa.cloudmediaplayer.Function.Home.TabAlbum;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import technologies.pa.cloudmediaplayer.Object.Album;
import technologies.pa.cloudmediaplayer.Pattern.RecycleViewAdapterPattern;
import technologies.pa.cloudmediaplayer.R;
import technologies.pa.cloudmediaplayer.Tool.ArrayConvert;

/**
 * Created by Dev02 on 3/1/2017.
 */

public class ListAlbumAdapter extends RecycleViewAdapterPattern {

    public ListAlbumAdapter(Context mContext, ArrayList<Object> dataSource) {
        super(mContext, dataSource);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = mLayoutInflater.inflate(R.layout.single_album,parent,false);
        return new AlbumViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        AlbumViewHolder mViewHolder = (AlbumViewHolder) holder;
        ArrayList<Album> albums = ArrayConvert.toArrayList(getDataSource());
        mViewHolder.albumImage.setBackgroundResource(albums.get(position).getAlbumImage());
        mViewHolder.albumName.setText(albums.get(position).getAlbumName());
        mViewHolder.albumTitle.setText(albums.get(position).getAlbumTitle());
    }
}
