package com.bzh.shopping.module.recommend;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.bzh.shopping.MainViewModel;
import com.bzh.shopping.R;
import com.bzh.shopping.State;
import com.bzh.shopping.base.fragment.BaseFragment;
import com.bzh.shopping.modle.Goods;
import com.bzh.shopping.modle.GoodsInfo;
import com.bzh.shopping.module.shopDetails.ShopDetailsFragment;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import androidx.navigation.Navigation;
import butterknife.BindView;
import timber.log.Timber;

/**
 * 推荐
 */
public class RecommendFragment extends BaseFragment {

    @BindView(R.id.fr_rvl)
    RecyclerView mRecyclerView;

    private RecommendViewModel mViewModel;

    private MainViewModel mMainViewModel;

    @Inject
    ShopDetailsFragment mShopDetailsFragment;

    RecommendAdapter adapter;

    @Inject
    public RecommendFragment() {
    }

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.fragment_recommend;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        adapter = new RecommendAdapter(getActivity());
        adapter.setOnRecommendListener(position -> {
            if (getView() != null) {
                Navigation.findNavController(getView()).navigate(R.id.action_mainFragment_to_shopDetailsFragment);
            }
        });
        adapter.setOnPurchaseListener(position -> {
            Timber.e("购买了商品");
            Goods goods = adapter.getGoods(position);
            if (goods != null) {
                mMainViewModel.handlerGoods(goods, State.add);
            }
        });
        mRecyclerView.setAdapter(adapter);
        GridLayoutManager layoutManager = new GridLayoutManager(getActivity(), 2);
        layoutManager.setOrientation(OrientationHelper.VERTICAL);
        mRecyclerView.setLayoutManager(layoutManager);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(RecommendViewModel.class);
        if (getActivity() != null) {
            mMainViewModel = ViewModelProviders.of(getActivity()).get(MainViewModel.class);
        }
        // TODO: Use the ViewModel
        mViewModel.getGoodsData().observe(this, goods -> {
            adapter.setGoodsList(goods);
        });
        mViewModel.setGoodsData(getDatas());
    }

    private List<Goods> getDatas() {
        List<Goods> list = new ArrayList<>();
        list.add(new GoodsInfo(0, "苹果", 16.4, 1000));
        list.add(new GoodsInfo(1, "香蕉", 8.4, 60));
        list.add(new GoodsInfo(2, "草莓", 25, 10));
        list.add(new GoodsInfo(3, "牛油果", 20, 5));
        list.add(new GoodsInfo(4, "榴莲", 88.6, 20));
        list.add(new GoodsInfo(5, "香蕉", 8.4, 60));
        list.add(new GoodsInfo(6, "草莓", 25, 10));
        list.add(new GoodsInfo(7, "牛油果", 20, 5));
        list.add(new GoodsInfo(8, "榴莲", 88.6, 20));
        return list;
    }
}
