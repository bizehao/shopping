package com.bzh.shopping.di.module;

import com.bzh.shopping.module.home.HomeFragment;
import com.bzh.shopping.module.home.commodity.CommodityFragment;
import com.bzh.shopping.module.home.hot.HotFragment;
import com.bzh.shopping.module.main.MainFragment;
import com.bzh.shopping.module.me.MeFragment;
import com.bzh.shopping.module.recommend.RecommendFragment;
import com.bzh.shopping.module.search.SearchFragment;
import com.bzh.shopping.module.shopDetails.ShopDetailsFragment;
import com.bzh.shopping.module.shopDetails.details.DetailsFragment;
import com.bzh.shopping.module.shopDetails.evaluate.EvaluateFragment;
import com.bzh.shopping.module.shopcart.ShopcartFragment;
import com.bzh.shopping.module.test.TestFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * @author 毕泽浩
 * @Description:
 * @time 2018/11/9 22:39
 */
@Module
public abstract class FragmentModule {
    //首页框架
    @ContributesAndroidInjector
    abstract MainFragment contributeMainFragment();
    //主页
    @ContributesAndroidInjector
    abstract HomeFragment contributeHomeFragment();
    //推荐
    @ContributesAndroidInjector
    abstract RecommendFragment contributeRecommendFragment();
    //搜索
    @ContributesAndroidInjector
    abstract SearchFragment contributeSearchFragment();
    //购物车
    @ContributesAndroidInjector
    abstract ShopcartFragment contributeShopcartFragment();
    //我
    @ContributesAndroidInjector
    abstract MeFragment contributeMeFragment();
    //热门
    @ContributesAndroidInjector
    abstract HotFragment contributeHotFragment();
    //商品
    @ContributesAndroidInjector
    abstract CommodityFragment contributeCommodityFragment();
    //商品详情
    @ContributesAndroidInjector
    abstract ShopDetailsFragment contributeShopDetailsFragment();
    //商品明细
    @ContributesAndroidInjector
    abstract EvaluateFragment contributeEvaluateFragment();
    //商品介绍
    @ContributesAndroidInjector
    abstract DetailsFragment contributeDetailsFragment();
    //测试
    @ContributesAndroidInjector
    abstract TestFragment contributeTestFragment();




}
