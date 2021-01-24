package com.example.himalaya;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.example.himalaya.base.BaseActivity;
import com.example.himalaya.interfaces.IAlbumDetailViewCallback;
import com.example.himalaya.presenters.AlbumDetailPresenter;
import com.example.himalaya.utils.ImageBlur;
import com.example.himalaya.views.RoundRectImageView;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;
import com.ximalaya.ting.android.opensdk.model.album.Album;
import com.ximalaya.ting.android.opensdk.model.track.Track;

import java.util.List;

public class DetailActivity extends BaseActivity implements IAlbumDetailViewCallback {

    private ImageView mLargeCover;
    private RoundRectImageView mSmallCover;
    private TextView mAlbumTitle;
    private TextView mAlbumAuthor;
    private AlbumDetailPresenter mAlbumDetailPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION);
        getWindow().setStatusBarColor(Color.TRANSPARENT);//设置状态栏颜色
        initView();
        mAlbumDetailPresenter = AlbumDetailPresenter.getInstance();
        mAlbumDetailPresenter.registerViewCallback(this);
    }

    private void initView() {
        mLargeCover = findViewById(R.id.iv_large_cover);
        mSmallCover = findViewById(R.id.iv_small_cover);
        mAlbumTitle = findViewById(R.id.tv_album_title);
        mAlbumAuthor = findViewById(R.id.tv_album_author);
    }

    @Override
    public void onDetailListLoaded(List<Track> tracks) {

    }

    @Override
    public void onAlbumLoaded(Album album) {
        if (mAlbumTitle != null) {
            mAlbumTitle.setText(album.getAlbumTitle());
        }
        if (mAlbumAuthor != null) {
            mAlbumAuthor.setText(album.getAnnouncer().getNickname());
        }
        if (mLargeCover != null) {
            Picasso.get().load(album.getCoverUrlLarge()).into(mLargeCover, new Callback() {
                @Override
                public void onSuccess() {
                    Drawable drawable = mLargeCover.getDrawable();
                    if (drawable != null) {
                        //对图片进行高斯模糊，到这里说明已经有图片了
                        ImageBlur.makeBlur(mLargeCover, getBaseContext());
                    }
                }

                @Override
                public void onError(Exception e) {

                }
            });
        }
        if (mSmallCover != null) {
            Glide.with(this).load(album.getCoverUrlSmall()).into(mSmallCover);
        }
    }

}