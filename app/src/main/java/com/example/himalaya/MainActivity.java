package com.example.himalaya;

import android.os.Bundle;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager.widget.ViewPager;

import com.example.himalaya.adapter.IndicatorAdapter;
import com.example.himalaya.adapter.ViewPagerAdapter;
import com.example.himalaya.views.UILoader;

import net.lucode.hackware.magicindicator.MagicIndicator;
import net.lucode.hackware.magicindicator.ViewPagerHelper;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.CommonNavigator;

public class MainActivity extends FragmentActivity {

    private static final String TAG = "MainActivity";
    private MagicIndicator mMagicIndicator;
    private ViewPager mViewPager;
    private UILoader mUiLoader;
    private IndicatorAdapter mIndicatorAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.main_color));
        initView();
        initEvent();
//        mUiLoader = new UILoader(this) {
//            @Override
//            protected View getSuccessView(ViewGroup container) {
//                return null;
//            }
//        };

        //不允许一个View绑定多个view
//        if (mUiLoader.getParent() instanceof ViewGroup) {
//            ((ViewGroup) mUiLoader.getParent()).removeView(mUiLoader);
//        }

    }

    private void initEvent() {
        mIndicatorAdapter.setonIndicatorTapClickListener(new IndicatorAdapter.onIndicatorTapClickListener() {
            @Override
            public void onTapClick(int index) {
                if (mViewPager != null) {
                    mViewPager.setCurrentItem(index);
                }
            }
        });
    }

    private void initView() {
        mMagicIndicator = findViewById(R.id.main_indicator);
        mMagicIndicator.setBackgroundColor(getColor(R.color.main_color));

        //viewpager
        mViewPager = findViewById(R.id.content_pager);
        FragmentManager supportFragmentManager = getSupportFragmentManager();
        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(supportFragmentManager);
        mViewPager.setAdapter(viewPagerAdapter);

        //创建适配器
        mIndicatorAdapter = new IndicatorAdapter(this);
        CommonNavigator commonNavigator = new CommonNavigator(this);
        commonNavigator.setAdjustMode(true);
        commonNavigator.setAdapter(mIndicatorAdapter);
        mMagicIndicator.setNavigator(commonNavigator);
        ViewPagerHelper.bind(mMagicIndicator, mViewPager);
    }

}