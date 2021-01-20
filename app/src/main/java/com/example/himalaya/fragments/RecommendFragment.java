package com.example.himalaya.fragments;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.himalaya.R;
import com.example.himalaya.adapters.RecommendListAdapter;
import com.example.himalaya.base.BaseFragment;
import com.example.himalaya.utils.Constants;
import com.ximalaya.ting.android.opensdk.constants.DTransferConstants;
import com.ximalaya.ting.android.opensdk.datatrasfer.CommonRequest;
import com.ximalaya.ting.android.opensdk.datatrasfer.IDataCallBack;
import com.ximalaya.ting.android.opensdk.model.album.Album;
import com.ximalaya.ting.android.opensdk.model.album.GussLikeAlbumList;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RecommendFragment extends BaseFragment {

    private static final String TAG = "RecommendFragment";
    private View mRootView;
    private RecyclerView mRecyclerView;
    private RecommendListAdapter mRecommendListAdapter;

    @Override
    protected View onSubViewLoaded(LayoutInflater layoutInflater, ViewGroup container) {
        //view加载完成
        mRootView = layoutInflater.inflate(R.layout.fragment_recommend, container, false);
        //1.获取控件
        mRecyclerView = mRootView.findViewById(R.id.recommend_list);
        //2.设置布局管理器
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        //3.设置适配器
        mRecommendListAdapter = new RecommendListAdapter();
        mRecyclerView.setAdapter(mRecommendListAdapter);
        //拿数据回来
        getRecommendData();

        //返回view给界面显示
        return mRootView;
    }

    /*
     * 获取推荐内容，猜你喜欢
     * */
    private void getRecommendData() {
        //封装参数
        Map<String, String> map = new HashMap<>();
        map.put(DTransferConstants.LIKE_COUNT, Constants.RECOMMEND_COUNT + "");
        CommonRequest.getGuessLikeAlbum(map, new IDataCallBack<GussLikeAlbumList>() {
            @Override
            public void onSuccess(GussLikeAlbumList gussLikeAlbumList) {
                if (gussLikeAlbumList != null) {
                    List<Album> albumList = gussLikeAlbumList.getAlbumList();
                    if (albumList != null) {
                        upRecommendUI(albumList);
                    }
                }
            }

            @Override
            public void onError(int i, String s) {
                Log.d(TAG, "error ---->" + i);
                Log.d(TAG, "errorMsg ---->" + s);
            }
        });
    }

    private void upRecommendUI(List<Album> albumList) {
        //把数据给适配器，并更新UI
        mRecommendListAdapter.setData(albumList);
    }

}
