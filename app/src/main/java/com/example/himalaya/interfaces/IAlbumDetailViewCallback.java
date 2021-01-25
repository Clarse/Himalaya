package com.example.himalaya.interfaces;

import com.ximalaya.ting.android.opensdk.model.album.Album;
import com.ximalaya.ting.android.opensdk.model.track.Track;

import java.util.List;

public interface IAlbumDetailViewCallback {

    /*
     * 专辑详情加载出来
     * */
    void onDetailListLoaded(List<Track> tracks);

    /*
     * 网络错误
     * */
    void onNetWorkError(int errorCode, String errorMsg);

    /*
     *吧album传给UI使用
     * */
    void onAlbumLoaded(Album album);

}