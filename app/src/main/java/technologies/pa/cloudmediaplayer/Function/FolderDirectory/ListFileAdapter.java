package technologies.pa.cloudmediaplayer.Function.FolderDirectory;

/**
 * Created by Dev02 on 3/6/2017.
 */

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import technologies.pa.cloudmediaplayer.Folder.File;
import technologies.pa.cloudmediaplayer.Pattern.RecycleViewAdapterPattern;
import technologies.pa.cloudmediaplayer.R;

/**
 * Created by Dev02 on 3/6/2017.
 */

public class ListFileAdapter extends RecycleViewAdapterPattern {

    private View.OnClickListener onClickListener;
    public void setOnClickListener(View.OnClickListener onClickListener){
        this.onClickListener = onClickListener;
    }
    public ListFileAdapter(Context mContext, Object[] dataSource) {
        super(mContext, dataSource);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = mLayoutInflater.inflate(R.layout.single_directory,parent,false);
        return new FolderViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        FolderViewHolder mViewHolder = (FolderViewHolder) holder;
        File[] files = (File[])getDataSource();
        //mViewHolder.linearLayout.setOnClickListener(onClickListener);
        mViewHolder.folderIcon.setBackgroundResource(R.drawable.file_icon);
        mViewHolder.folderTitle.setText(files[position].getTitle());
        //mViewHolder.folderMusicCount.setText(String.valueOf(folders[position].getListFile().size())+" Songs");
        //mViewHolder.folderPath.setText(folders[position].getPath());
    }
}
