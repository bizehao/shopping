package com.bzh.shopping.module.test;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.bzh.shopping.R;
import com.bzh.shopping.base.fragment.BaseFragment;
import com.bzh.shopping.module.shopDetails.details.DetailsFragment;
import com.bzh.shopping.module.shopDetails.evaluate.EvaluateFragment;

import butterknife.BindView;
import timber.log.Timber;

/**
 * @author 毕泽浩
 * @Description:
 * @time 2018/11/19 10:58
 */
public class TestFragment extends BaseFragment {

    @BindView(R.id.ft_tx)
    ViewPager mViewPager;
    @BindView(R.id.ft_tab)
    TabLayout mTabLayout;
    @BindView(R.id.ft_but1)
    MyButtonOne myButtonOne;

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.fragment_test;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        Fragment[] fragments = {EvaluateFragment.newInstance("商品"), EvaluateFragment.newInstance("详情"),
                EvaluateFragment.newInstance("评价")};
        String[] titles = {"商品", "详情", "评价"};
        mViewPager.setAdapter(new FragmentPagerAdapter(getChildFragmentManager()) {
            @Override
            public Fragment getItem(int i) {
                return fragments[i];
            }

            @Override
            public int getCount() {
                return fragments.length;
            }

            @Nullable
            @Override
            public CharSequence getPageTitle(int position) {
                return titles[position];
            }
        });
        //mTabLayout.setupWithViewPager(mViewPager);
        mTabLayout.addTab(mTabLayout.newTab().setText("Tab 1"));
        mTabLayout.addTab(mTabLayout.newTab().setText("Tab 2"));
        mTabLayout.addTab(mTabLayout.newTab().setText("Tab 3"));

        myButtonOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Timber.e("点击了");
            }
        });
    }
}
