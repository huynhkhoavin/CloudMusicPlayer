package technologies.pa.cloudmediaplayer.Player;

import android.view.View;
import android.widget.ImageView;

import butterknife.BindView;
import butterknife.ButterKnife;
import technologies.pa.cloudmediaplayer.Home.TabSong.SongViewHolder;
import technologies.pa.cloudmediaplayer.R;

/**
 * Created by Dev02 on 3/1/2017.
 */

public class SongAvatarViewHolder extends SongViewHolder {
    @BindView(R.id.img_avatar)
    public ImageView avatar;
    public SongAvatarViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this,itemView);
    }
}
