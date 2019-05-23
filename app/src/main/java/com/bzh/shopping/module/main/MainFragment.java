package com.bzh.shopping.module.main;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import com.bzh.shopping.R;
import com.bzh.shopping.base.fragment.BaseFragment;
import com.bzh.shopping.module.home.HomeFragment;
import com.bzh.shopping.module.me.MeFragment;
import com.bzh.shopping.module.recommend.RecommendFragment;
import com.bzh.shopping.module.search.SearchFragment;
import com.bzh.shopping.module.shopcart.ShopcartFragment;

import javax.inject.Inject;

import androidx.navigation.Navigation;
import butterknife.BindView;
import butterknife.OnClick;

public class MainFragment extends BaseFragment {

    @BindView(R.id.main_bottom_bar)
    BottomNavigationBar mBottomNavigationBar;
    @Inject
    HomeFragment mHomeFragment;
    @Inject
    MeFragment mMeFragment;
    @Inject
    RecommendFragment mRecommendFragment;
    @Inject
    SearchFragment mSearchFragment;
    @Inject
    ShopcartFragment mShopcartFragment;

    private Fragment[] fragments;
    private boolean firstInit = true; //首次初始化
    private int nowItem = 0; //当前底部菜单的指向
    private MainViewModel mViewModel;

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.fragment_main;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        fragments = new Fragment[]{mHomeFragment,mRecommendFragment,mSearchFragment,mShopcartFragment,mMeFragment};
        mBottomNavigationBar.setMode(BottomNavigationBar.MODE_FIXED).setBackgroundStyle(BottomNavigationBar.BACKGROUND_STYLE_STATIC)
                .setActiveColor(R.color.colorRed)//选中颜色 图标和文字
                .setInActiveColor(R.color.colorGray)//默认未选择颜色
                .addItem(new BottomNavigationItem(R.drawable.home, "首页"))
                .addItem(new BottomNavigationItem(R.drawable.recommend, "推荐"))
                .addItem(new BottomNavigationItem(R.drawable.search, "搜索")) //.setBadgeItem(numberBadgeItem)
                .addItem(new BottomNavigationItem(R.drawable.shopcart, "购物车"))
                .addItem(new BottomNavigationItem(R.drawable.me, "我的"))
                .setFirstSelectedPosition(nowItem).initialise();
        if(firstInit){
            getChildFragmentManager().beginTransaction().add(R.id.fm_main,fragments[0]).commitAllowingStateLoss();
            firstInit = false;
        }

        mBottomNavigationBar.setTabSelectedListener(new BottomNavigationBar.OnTabSelectedListener() {
            @Override
            public void onTabSelected(int position) {
                FragmentManager fragmentManager = getChildFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                Fragment newFragment = fragments[position];
                if (newFragment.isAdded()) {
                    fragmentTransaction.show(newFragment);
                }else {
                    fragmentTransaction.add(R.id.fm_main,newFragment);
                }
                fragmentTransaction.commitAllowingStateLoss();
                nowItem = position;
            }

            @Override
            public void onTabUnselected(int position) {
                FragmentManager fragmentManager = getChildFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                Fragment usedFragment = fragments[position];
                fragmentTransaction.hide(usedFragment).commitAllowingStateLoss();
            }

            @Override
            public void onTabReselected(int position) {

            }
        });
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(MainViewModel.class);
        // TODO: Use the ViewModel
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        System.out.println("被銷毀哦了");
    }
}
