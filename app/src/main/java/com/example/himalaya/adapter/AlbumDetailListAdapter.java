package com.example.himalaya.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.himalaya.R;
import com.ximalaya.ting.android.opensdk.model.track.Track;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class AlbumDetailListAdapter extends RecyclerView.Adapter<AlbumDetailListAdapter.InnerHolder> {

    private List<Track> mDetailData = new ArrayList<>();
    //格式化时间
    private SimpleDateFormat mUpdateTimeFormat = new SimpleDateFormat("yyyy-MM-dd");
    private SimpleDateFormat mDurationFormat = new SimpleDateFormat("mm:ss");
    private onItemClickListener mItemClick;

    @NonNull
    @Override
    public InnerHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_album_detail, parent, false);
        return new InnerHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull InnerHolder holder, int position) {
        View itemView = holder.itemView;
        itemView.setTag(position);

        TextView orderTv = itemView.findViewById(R.id.order_text);
        TextView titleTv = itemView.findViewById(R.id.detail_item_title);
        TextView playCount = itemView.findViewById(R.id.detail_item_play_count);
        TextView duration = itemView.findViewById(R.id.detail_item_duration);
        TextView updateTime = itemView.findViewById(R.id.detail_item_update_time);

        Track track = mDetailData.get(position);

        orderTv.setText(position + "");
        titleTv.setText(track.getTrackTitle());
        playCount.setText(track.getPlayCount() + "");
        int durationMills = track.getDuration() * 1000;
        String durationText = mDurationFormat.format(durationMills);
        duration.setText(durationText + "");
        String updateTimeText = mUpdateTimeFormat.format(track.getUpdatedAt());
        updateTime.setText(updateTimeText);

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mItemClick != null) {
                    mItemClick.onItemClick();
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mDetailData.size();
    }

    public void setData(List<Track> tracks) {
        //清除原来的数据
        mDetailData.clear();
        //添加新的数据
        mDetailData.addAll(tracks);
        //更新UI
        notifyDataSetChanged();
    }

    public static class InnerHolder extends RecyclerView.ViewHolder {
        public InnerHolder(@NonNull View itemView) {
            super(itemView);
        }
    }

    public void setonItemClickListener(onItemClickListener listener) {
        this.mItemClick = listener;
    }

    public interface onItemClickListener {
        void onItemClick();
    }

}
