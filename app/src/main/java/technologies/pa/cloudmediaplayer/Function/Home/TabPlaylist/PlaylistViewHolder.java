package technologies.pa.cloudmediaplayer.Function.Home.TabPlaylist;

import android.view.View;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import technologies.pa.cloudmediaplayer.Pattern.ViewHolderPattern;
import technologies.pa.cloudmediaplayer.R;

/**
 * Created by Dev02 on 3/1/2017.
 */

public class PlaylistViewHolder extends ViewHolderPattern {

    @BindView(R.id.playlist_name)
    TextView playlistName;
    public PlaylistViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this,itemView);
    }
}
