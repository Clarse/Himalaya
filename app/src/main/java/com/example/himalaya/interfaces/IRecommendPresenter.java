package com.example.himalaya.interfaces;

public interface IRecommendPresenter {

    /*
     * 获取推荐内容
     * */
    void getRecommendList();

    /*
     * 用于注册Ui的回调
     * */
    void registerViewCallback(IRecommendCallback callback);

    /*
     * 用于取消注册Ui的回调
     * */
    void unRegisterViewCallback(IRecommendCallback callback);

}
