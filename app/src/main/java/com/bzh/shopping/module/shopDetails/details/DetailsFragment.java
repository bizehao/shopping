package com.bzh.shopping.module.shopDetails.details;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.NestedScrollView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bzh.shopping.R;
import com.bzh.shopping.base.fragment.BaseFragment;
import com.bzh.shopping.module.shopDetails.evaluate.EvaluateFragment;
import com.bzh.widgets.banner.BannerItemBean;
import com.bzh.widgets.banner.BannerViewPager;
import com.bzh.widgets.banner.transformer.ParallaxTransformer;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import timber.log.Timber;

/**
 * 商品展示
 */
public class DetailsFragment extends BaseFragment {

    private DetailsViewModel mViewModel;
    @BindView(R.id.fd_scroll)
    NestedScrollView mScrollView;
    @BindView(R.id.fd_tx)
    Button mButton;
    @BindView(R.id.fsd_bvp)
    BannerViewPager mBannerViewPager;
    private final int[] mData = {R.drawable.img0, R.drawable.img1, R.drawable.img2, R.drawable.img3, R.drawable.img4};

    public static DetailsFragment newInstance(String name) {
        DetailsFragment fragment = new DetailsFragment();
        Bundle bundle = new Bundle();
        bundle.putString("name",name);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.fragment_details;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        Bundle bundle = getArguments();
        String name = bundle.getString("name","没有数据");
        mButton.setText(name);

        //轮播图
        mBannerViewPager.setData(getData(), (context, imgPath, imageView) -> Glide.with(context).load(imgPath).into(imageView))
                .setPageTransformer(new ParallaxTransformer())//设置切换效果
                .setAutoPlay(false)
                .setPageSpeed(2000)   // 切换速度
                .setOnBannerItemClickListener((view, currentItem) -> Toast.makeText(getActivity(), "" + currentItem, Toast.LENGTH_SHORT).show())
                .setHaveTitle(true);//设置是否显示标题


        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Timber.e(String.valueOf(mScrollView.getY()));
            }
        });
        mScrollView.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
            @Override
            public void onScrollChange(NestedScrollView nestedScrollView, int i, int i1, int i2, int i3) {
                Timber.e("滚动啊");
            }
        });
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(DetailsViewModel.class);
        // TODO: Use the ViewModel
    }

    //轮播图
    private List<BannerItemBean> getData() {
        List<BannerItemBean> list = new ArrayList<>(mData.length);
        for (int i = 0; i < mData.length; i++) {
            list.add(new BannerItemBean(mData[i], "波司登羽绒服打折热卖了"));
        }
        return list;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Timber.e("被销毁了");
    }
}
