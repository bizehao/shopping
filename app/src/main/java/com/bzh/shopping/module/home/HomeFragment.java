package com.bzh.shopping.module.home;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import com.bzh.shopping.R;
import com.bzh.shopping.base.fragment.BaseFragment;
import com.bzh.shopping.module.home.commodity.CommodityFragment;
import com.bzh.shopping.module.home.hot.HotFragment;
import javax.inject.Inject;
import butterknife.BindView;

public class HomeFragment extends BaseFragment {

    @BindView(R.id.home_tab)
    TabLayout mTabLayout;

    @BindView(R.id.home_page)
    ViewPager mViewPager;

    private HomeViewModel mViewModel;
    private String[] titles;
    private Fragment[] fragments;

    @Inject
    public HomeFragment() {
    }

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.fragment_home;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        initData();
        mViewPager.setAdapter(new FragmentStatePagerAdapter(getChildFragmentManager()) {
            @Override
            public Fragment getItem(int i) {
                return fragments[i];
            }

            @Override
            public int getCount() {
                return titles.length;
            }

            @Nullable
            @Override
            public CharSequence getPageTitle(int position) {
                return titles[position];
            }
        });
        mTabLayout.setupWithViewPager(mViewPager);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(HomeViewModel.class);
        // TODO: Use the ViewModel
    }

    //初始化数据
    public void initData(){
        titles = new String[]{"热门","服饰","鞋包","男装","母婴","内衣","食品","百货","手机","美妆","家纺","电器","水果","家装"};
        fragments = new Fragment[titles.length];
        for (int i=0; i<titles.length; i++){
            if(i==0){
                fragments[i] = HotFragment.newInstance();
            }else {
                fragments[i] = CommodityFragment.newInstance();
            }
        }
    }

}
