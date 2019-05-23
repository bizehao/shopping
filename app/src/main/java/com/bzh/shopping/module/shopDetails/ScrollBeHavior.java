package com.bzh.shopping.module.shopDetails;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.TabLayout;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.bzh.shopping.R;

import timber.log.Timber;

/**
 * @author 毕泽浩
 * @Description:
 * @time 2018/11/18 9:37
 */
public class ScrollBeHavior extends AppBarLayout.ScrollingViewBehavior {
    private boolean isInit = false;
    private float childY;

    public ScrollBeHavior() {
    }

    public ScrollBeHavior(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onDependentViewChanged(@NonNull CoordinatorLayout parent, @NonNull View child, @NonNull View dependency) {
        super.onDependentViewChanged(parent, child, dependency);
        float dependencyY = Math.abs(dependency.getY());
        if (!isInit) {
            isInit = true;
            childY = Math.abs(child.getY());
            CommFilte.getCommFilte().setScrollViewY(childY);
        }
        int val = (int) (dependencyY / childY * 255);
        dependency.findViewById(R.id.fsd_bvp).setAlpha((255 - val) / 255f);
        CommFilte.getCommFilte().setFloatLiveData(val / 255f);
        CommFilte.getCommFilte().setScrollViewHeight(child.getY());

        return true;
    }
}
