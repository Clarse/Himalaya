package com.example.himalaya.interfaces;

public interface IAlbumDetailPresenter {

    /*
     * 下拉刷新
     * */
    void loadMore();

    /*
     * 上拉加载更多
     * */
    void pull2Refresh();

    /*
     * 获取专辑详情
     * */
    void getAlbumDetail(int albumId, int page);

    /*
     * 注册UI通知的回调接口
     * */
    void registerViewCallback(IAlbumDetailViewCallback detailViewCallback);

    /*
     * 取消注册回调接口
     * */
    void unRegisterViewCallback(IAlbumDetailViewCallback detailViewCallback);

}
