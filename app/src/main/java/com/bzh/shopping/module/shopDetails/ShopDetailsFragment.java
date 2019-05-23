package com.bzh.shopping.module.shopDetails;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bzh.shopping.MainViewModel;
import com.bzh.shopping.R;
import com.bzh.shopping.base.fragment.BaseFragment;
import com.bzh.shopping.module.shopDetails.details.DetailsFragment;
import com.bzh.shopping.module.shopDetails.evaluate.EvaluateFragment;
import com.bzh.widgets.banner.BannerItemBean;
import com.bzh.widgets.banner.BannerViewPager;
import com.bzh.widgets.banner.transformer.ParallaxTransformer;
import com.bzh.widgets.banner.transformer.ZoomOutTranformer;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import androidx.navigation.Navigation;
import butterknife.BindView;
import butterknife.OnClick;
import timber.log.Timber;

/**
 * 商品详情
 */
public class ShopDetailsFragment extends BaseFragment {

    private ShopDetailsViewModel mViewModel;
    private MainViewModel mMainViewModel;

    @BindView(R.id.fsd_tab)
    TabLayout mTabLayout;
    @BindView(R.id.fsd_pager)
    ViewPager mViewPager;
    @BindView(R.id.fsd_toolbar)
    Toolbar mToolbar;

    private CommFilte mCommFilte;

    @Inject
    public ShopDetailsFragment() {
    }

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.fragment_shop_details;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        mToolbar.setNavigationIcon(R.drawable.ic_chevron_left_black);
        mCommFilte = CommFilte.getCommFilte();
        CommFilte.getCommFilte().getFloatLiveData().observeForever(aFloat -> {
            if(mToolbar != null){
                mToolbar.setAlpha(aFloat);
                Timber.e("亮度："+aFloat);
            }
        });
        Fragment[] fragments = {DetailsFragment.newInstance("商品"), EvaluateFragment.newInstance("详情"),
                EvaluateFragment.newInstance("评价")};
        String[] titles = {"商品", "详情", "评价"};
        mViewPager .setOffscreenPageLimit(3);
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
        mTabLayout.setupWithViewPager(mViewPager);
        mViewPager.setOnScrollChangeListener((v, scrollX, scrollY, oldScrollX, oldScrollY) -> {
            if(scrollX > oldScrollX && mCommFilte.getFloatLiveData().getValue() < 1f){
                CommFilte.getCommFilte().setFloatLiveData(scrollX/1080f);
            }else if(scrollX < oldScrollX && scrollX < 1080) {
                if(mCommFilte.getFloatLiveData().getValue() > 1-mCommFilte.getScrollViewHeight()/mCommFilte.getScrollViewY()){
                    CommFilte.getCommFilte().setFloatLiveData(scrollX/1080f);
                }
            }
        });
    }

    @Override
    protected void initViewAfter() {
        super.initViewAfter();
        mMainViewModel.setIsToolbarShow(false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mMainViewModel = ViewModelProviders.of(getActivity()).get(MainViewModel.class);
        mViewModel = ViewModelProviders.of(this).get(ShopDetailsViewModel.class);
        // TODO: Use the ViewModel
    }

    /*@OnClick({R.id.fsd_return})
    public void backClick(View view){
        Navigation.findNavController(getView()).popBackStack(R.id.mainFragment,false);
        //Navigation.findNavController(getView()).navigate(R.id.action_shopDetailsFragment_to_mainFragment);
    }*/

    @Override
    public void onStop() {
        super.onStop();
        mMainViewModel.setIsToolbarShow(true);
    }
}
