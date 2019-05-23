package com.bzh.shopping;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.FrameLayout;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import com.bzh.shopping.base.activity.BaseActivity;
import com.bzh.shopping.modle.Goods;
import com.bzh.shopping.modle.GoodsInfo;
import com.bzh.shopping.module.home.HomeFragment;
import com.bzh.shopping.module.me.MeFragment;
import com.bzh.shopping.module.recommend.RecommendFragment;
import com.bzh.shopping.module.search.SearchFragment;
import com.bzh.shopping.module.shopcart.ShopcartFragment;
import com.bzh.shopping.module.shopcart.ShopcartViewModel;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import androidx.navigation.NavController;
import androidx.navigation.NavDestination;
import androidx.navigation.NavGraph;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;
import butterknife.BindView;

public class MainActivity extends BaseActivity {

    @BindView(R.id.main_toolbar)
    Toolbar mToolbar;

    MainViewModel mMainViewModel;

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        setSupportActionBar(mToolbar);
        FragmentManager fragmentManager = getSupportFragmentManager();
        NavHostFragment navHostFragment = (NavHostFragment) fragmentManager.findFragmentById(R.id.main_fragment);
        NavController navController = navHostFragment.getNavController();
        NavigationUI.setupActionBarWithNavController(MainActivity.this, navController);
        mMainViewModel = ViewModelProviders.of(this).get(MainViewModel.class);
        mMainViewModel.getIsToolbarShow().observe(this, aBoolean -> {
            if(!aBoolean){
                if(mToolbar.getVisibility() == View.VISIBLE) mToolbar.setVisibility(View.GONE);
            }else {
                if(mToolbar.getVisibility() == View.GONE) mToolbar.setVisibility(View.VISIBLE);
            }
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        return Navigation.findNavController(this, R.id.main_fragment).navigateUp();
    }
}
