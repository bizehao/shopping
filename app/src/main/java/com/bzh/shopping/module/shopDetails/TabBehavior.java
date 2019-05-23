package com.bzh.shopping.module.shopDetails;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;
import android.view.View;

import com.bzh.shopping.R;
import com.bzh.widgets.banner.BannerViewPager;

import timber.log.Timber;

/**
 * @author 毕泽浩
 * @Description:
 * @time 2018/11/17 21:47
 */
public class TabBehavior extends CoordinatorLayout.Behavior<View>{
    // 列表顶部和title底部重合时，列表的滑动距离。
    private float deltaY;

    public TabBehavior() {
    }

    public TabBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean layoutDependsOn(@NonNull CoordinatorLayout parent, @NonNull View child, @NonNull View dependency) {
        return dependency instanceof NestedScrollView;
    }

    @Override
    public boolean onDependentViewChanged(@NonNull CoordinatorLayout parent, @NonNull View child, @NonNull View dependency) {
        float y = dependency.getY();
        Timber.e("变化"+String.valueOf(y));
        Timber.e("不变"+String.valueOf(dependency.getHeight()));
        //Toolbar toolbar = (Toolbar) child;
        /*y = Math.abs(y);
        Timber.e(String.valueOf((int)(y/1050*255)));
        int val = (int)(y/1050*255);
        //toolbar.setBackgroundColor(Color.argb(val, 255, 255, 255));
        TabLayout tabLayout = parent.findViewById(R.id.fsd_tab);
        tabLayout.setTabTextColors(Color.argb(val, 0, 0, 0),Color.argb(val, 255, 0, 0));*/
        return true;
    }
}
