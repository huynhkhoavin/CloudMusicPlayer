package technologies.pa.cloudmediaplayer.Home.TabSong;

import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import technologies.pa.cloudmediaplayer.Pattern.ViewHolderPattern;
import technologies.pa.cloudmediaplayer.R;

/**
 * Created by Dev02 on 2/28/2017.
 */

public class SongViewHolder extends ViewHolderPattern {
    @BindView(R.id.song_name)
    TextView tv_songName;
    @BindView(R.id.song_singer)
    TextView tv_songSinger;
    @BindView(R.id.three_dots)
    ImageView threedotsButton;
    public SongViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this,itemView);
    }

}
