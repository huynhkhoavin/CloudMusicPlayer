package technologies.pa.cloudmediaplayer.Function.Home.TabAlbum;

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

public class AlbumViewHolder extends ViewHolderPattern {

    @BindView(R.id.album_image)
    public ImageView albumImage;
    @BindView(R.id.album_name)
    public TextView albumName;
    @BindView(R.id.album_title)
    public TextView albumTitle;

    public AlbumViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this,itemView);
    }
}
