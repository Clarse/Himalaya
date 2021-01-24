package com.example.himalaya.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.himalaya.R;
import com.ximalaya.ting.android.opensdk.model.album.Album;

import java.util.ArrayList;
import java.util.List;

public class RecommendListAdapter extends RecyclerView.Adapter<RecommendListAdapter.InnerHolder> {

    private List<Album> mData = new ArrayList<>();
    private onRecommendItemClickListener mClickListener;

    @NonNull
    @Override
    public InnerHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //这里加载view
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recommend, parent, false);
        return new InnerHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull InnerHolder holder, int position) {
        //这里设置数据
        holder.itemView.setTag(position);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mClickListener != null) {
                    mClickListener.onItemClick((Integer) v.getTag(), mData.get((Integer) v.getTag()));
                }
            }
        });
        holder.setData(mData.get(position));
    }

    @Override
    public int getItemCount() {
        //返回要显示的个数
        if (mData != null) {
            return mData.size();
        }
        return 0;
    }

    public void setData(List<Album> albumList) {
        if (mData != null) {
            mData.clear();
            mData.addAll(albumList);
        }
        //更新UI
        notifyDataSetChanged();
    }

    public static class InnerHolder extends RecyclerView.ViewHolder {
        public InnerHolder(@NonNull View itemView) {
            super(itemView);
        }

        public void setData(Album album) {
            //找到各个控件。设置数据
            ImageView albumCover = itemView.findViewById(R.id.album_cover);
            TextView albumTitle = itemView.findViewById(R.id.album_title_tv);
            TextView albumDescription = itemView.findViewById(R.id.album_description_tv);
            TextView albumPlayCount = itemView.findViewById(R.id.album_play_count);
            TextView albumContentSize = itemView.findViewById(R.id.album_content_size);

            albumTitle.setText(album.getAlbumTitle());
            albumDescription.setText(album.getAlbumIntro());
            albumPlayCount.setText(album.getPlayCount() + "");
            albumContentSize.setText(album.getIncludeTrackCount() + "");

            Glide.with(itemView.getContext()).load(album.getCoverUrlLarge()).into(albumCover);

        }

    }

    public void setonRecommendItemClick(onRecommendItemClickListener listener) {
        this.mClickListener = listener;
    }

    public interface onRecommendItemClickListener {
        void onItemClick(int position, Album album);
    }

}
