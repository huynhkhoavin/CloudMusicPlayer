package technologies.pa.cloudmediaplayer.Function.FolderDirectory;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import technologies.pa.cloudmediaplayer.Pattern.ViewHolderPattern;
import technologies.pa.cloudmediaplayer.R;

/**
 * Created by Dev02 on 3/3/2017.
 */

public class FolderViewHolder extends ViewHolderPattern {
    @BindView(R.id.linear_layout)
    LinearLayout linearLayout;
    @BindView(R.id.folder_icon)
    ImageView folderIcon;
    @BindView(R.id.folder_title)
    TextView folderTitle;
    @BindView(R.id.folder_path)
    TextView folderPath;
    @BindView(R.id.folder_music_count)
    TextView folderMusicCount;
    public FolderViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this,itemView);
    }

}
