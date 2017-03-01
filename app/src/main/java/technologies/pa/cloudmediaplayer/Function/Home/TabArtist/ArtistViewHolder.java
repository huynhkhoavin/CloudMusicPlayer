package technologies.pa.cloudmediaplayer.Function.Home.TabArtist;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import technologies.pa.cloudmediaplayer.Pattern.ViewHolderPattern;
import technologies.pa.cloudmediaplayer.R;

/**
 * Created by Dev02 on 3/1/2017.
 */

public class ArtistViewHolder extends ViewHolderPattern {


    @BindView(R.id.artist_avatar)
    public ImageView artistAvatar;
    @BindView(R.id.artistname)
    public TextView artistName;
    public ArtistViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this,itemView);
    }
}
