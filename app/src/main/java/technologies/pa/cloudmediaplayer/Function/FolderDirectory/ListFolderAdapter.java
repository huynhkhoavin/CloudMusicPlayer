package technologies.pa.cloudmediaplayer.Function.FolderDirectory;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import technologies.pa.cloudmediaplayer.Folder.Folder;
import technologies.pa.cloudmediaplayer.Pattern.RecycleViewAdapterPattern;
import technologies.pa.cloudmediaplayer.R;
import technologies.pa.cloudmediaplayer.Tool.ArrayConvert;

/**
 * Created by Dev02 on 3/6/2017.
 */

public class ListFolderAdapter extends RecycleViewAdapterPattern {

    private View.OnClickListener onClickListener;
    public void setOnClickListener(View.OnClickListener onClickListener){
        this.onClickListener = onClickListener;
    }
    public ListFolderAdapter(Context mContext, ArrayList<Object> dataSource) {
        super(mContext, dataSource);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = mLayoutInflater.inflate(R.layout.single_directory,parent,false);
        return new FolderViewHolder(itemView);
    }
    public Object getItemOnPosition(int position){
        Object obj = getDataSource().get(position);
        return obj;
    }
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        FolderViewHolder mViewHolder = (FolderViewHolder) holder;
        ArrayList<Folder> folders = ArrayConvert.toArrayList(getDataSource());
        mViewHolder.linearLayout.setOnClickListener(onClickListener);
        mViewHolder.folderIcon.setBackgroundResource(R.drawable.directory_icon);
        mViewHolder.folderTitle.setText(folders.get(position).getTitle());
        mViewHolder.folderMusicCount.setText(String.valueOf(folders.get(position).getListFile().size())+" Songs");
        mViewHolder.folderPath.setText(folders.get(position).getPath());
    }
}
