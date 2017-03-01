package technologies.pa.cloudmediaplayer.Function.Home.TabArtist;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import technologies.pa.cloudmediaplayer.Object.Artist;
import technologies.pa.cloudmediaplayer.Pattern.RecycleViewAdapterPattern;
import technologies.pa.cloudmediaplayer.R;

/**
 * Created by Dev02 on 3/1/2017.
 */

public class ListArtistAdapter extends RecycleViewAdapterPattern {
    public ListArtistAdapter(Context mContext, Object[] dataSource) {
        super(mContext, dataSource);
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = mLayoutInflater.inflate(R.layout.single_artist,parent,false);
        return new ArtistViewHolder(itemView);
    }
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ArtistViewHolder mViewHolder = (ArtistViewHolder) holder;
        Artist[] artists= (Artist[])getDataSource();

        mViewHolder.artistName.setText(artists[position].getArtistName());
        mViewHolder.artistAvatar.setBackgroundResource(artists[position].getArtistAvatar());
    }
}
