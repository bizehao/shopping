package com.bzh.shopping.module.home.hot;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bzh.shopping.R;
import com.bzh.shopping.base.fragment.BaseFragment;
import com.bzh.widgets.banner.BannerItemBean;
import com.bzh.widgets.banner.BannerViewPager;
import com.bzh.widgets.banner.transformer.ZoomOutTranformer;
import com.bzh.widgets.lateralMenus.LaterMenusBean;
import com.bzh.widgets.lateralMenus.LateralMenus;

import java.util.ArrayList;
import java.util.List;

import androidx.navigation.Navigation;
import butterknife.BindView;

/**
 * 热门
 */
public class HotFragment extends BaseFragment {

    @BindView(R.id.hot_banner)
    BannerViewPager mBannerViewPager;
    @BindView(R.id.hot_later)
    LateralMenus mLateralMenus;
    @BindView(R.id.fh_rlv)
    RecyclerView mRecyclerView;
    @BindView(R.id.fh_xwyd)
    TextView mTextView;

    private final int[] mData = {R.drawable.img0, R.drawable.img1, R.drawable.img2, R.drawable.img3, R.drawable.img4};

    private HotViewModel mViewModel;

    public static HotFragment newInstance() {
        return new HotFragment();
    }

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.fragment_hot;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {

        //轮播图
        mBannerViewPager.setData(getData(), (context, imgPath, imageView) -> Glide.with(context).load(imgPath).into(imageView))
                .setPageTransformer(new ZoomOutTranformer())//设置切换效果
                .setAutoPlay(true)
                .setPageSpeed(2000)   // 切换速度
                .setOnBannerItemClickListener((view, currentItem) -> Toast.makeText(getActivity(), "" + currentItem, Toast.LENGTH_SHORT).show())
                .setHaveTitle(true);//设置是否显示标题
        //滑动菜单
        mLateralMenus.setData(getData1(), (context, imgPath, imageView) -> Glide.with(context).load(imgPath).into(imageView))
                .setListener((view, currentItem) -> {
                    Toast.makeText(getActivity(), "" + currentItem, Toast.LENGTH_SHORT).show();
                })
                .build();

        HotAdapter adapter = new HotAdapter(getActivity());
        mRecyclerView.setAdapter(adapter);
        LinearLayoutManager manager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(manager);

        mTextView.setOnClickListener(v -> {
            Navigation.findNavController(getView()).navigate(R.id.action_mainFragment_to_testFragment);
        });
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(HotViewModel.class);
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

    //横向菜单
    private List<LaterMenusBean> getData1() {
        List<LaterMenusBean> list = new ArrayList<>(16);
        for (int i = 0; i < 16; i++) {
            list.add(new LaterMenusBean(R.drawable.food, "热卖"));
        }
        return list;
    }
}
