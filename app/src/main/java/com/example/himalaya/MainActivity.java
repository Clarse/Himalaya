package com.example.himalaya;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.example.himalaya.adapter.IndicatorAdapter;
import com.example.himalaya.views.UILoader;

import net.lucode.hackware.magicindicator.MagicIndicator;
import net.lucode.hackware.magicindicator.ViewPagerHelper;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.CommonNavigator;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private MagicIndicator mMagicIndicator;
    private ViewPager mViewPager;
    private UILoader mUiLoader;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();

        mUiLoader = new UILoader(this) {
            @Override
            protected View getSuccessView(ViewGroup container) {
                return null;
            }
        };

        //不允许一个View绑定多个view
        if (mUiLoader.getParent() instanceof ViewGroup) {
            ((ViewGroup) mUiLoader.getParent()).removeView(mUiLoader);
        }

    }

    private void initView() {
        mMagicIndicator = findViewById(R.id.main_indicator);
        mViewPager = findViewById(R.id.content_pager);
        mMagicIndicator.setBackgroundColor(getColor(R.color.main_color));
        //创建适配器
        IndicatorAdapter indicatorAdapter = new IndicatorAdapter(this);
        CommonNavigator commonNavigator = new CommonNavigator(this);
        commonNavigator.setAdapter(indicatorAdapter);
        mMagicIndicator.setNavigator(commonNavigator);
        ViewPagerHelper.bind(mMagicIndicator, mViewPager);
    }

}