package com.bzh.shopping.module.home.commodity;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import com.bzh.shopping.R;
import com.bzh.shopping.base.fragment.BaseFragment;

public class CommodityFragment extends BaseFragment {

    private CommodityViewModel mViewModel;

    public static CommodityFragment newInstance() {
        return new CommodityFragment();
    }

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.fragment_commodity;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(CommodityViewModel.class);
        // TODO: Use the ViewModel
    }

}
