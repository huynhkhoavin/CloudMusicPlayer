package technologies.pa.cloudmediaplayer.Function.FolderDirectory;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import technologies.pa.cloudmediaplayer.Object.Folder;
import technologies.pa.cloudmediaplayer.Pattern.RecycleViewAdapterPattern;
import technologies.pa.cloudmediaplayer.R;

/**
 * Created by Dev02 on 3/3/2017.
 */

public class ListFolderAdapter extends RecycleViewAdapterPattern {
    public View.OnClickListener listener;
    public ListFolderAdapter(Context mContext, Object[] dataSource) {
        super(mContext, dataSource);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = mLayoutInflater.inflate(R.layout.single_directory,parent,false);
        return new FolderViewHolder(itemView);
    }
    public void setOnItemClickListener(View.OnClickListener listener)
    {
        this.listener = listener;
    }
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        FolderViewHolder mViewHolder = (FolderViewHolder)holder;
        Folder[] folders = (Folder[])getDataSource();
        mViewHolder.folderTitle.setText(folders[position].getFolderTitle());
        mViewHolder.folderPath.setText(folders[position].getFolderPath());
        mViewHolder.linearLayout.setOnClickListener(listener);
        switch(folders[position].getFolderType()){
            case NODE:
            {
                //mViewHolder.folderIcon.setBackgroundResource(R.drawable.music_folder_icon);
                mViewHolder.folderMusicCount.setText(folders[position].getFolderMusicCount()+" Songs");
                break;
            }
            case LEAF:
            {
                mViewHolder.folderIcon.setBackgroundResource(R.drawable.song_icon);
                mViewHolder.folderMusicCount.setText("");
            }
            default:
            {
                break;
            }
        }

    }
}
