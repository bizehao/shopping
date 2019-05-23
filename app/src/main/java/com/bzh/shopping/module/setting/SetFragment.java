package com.bzh.shopping.module.setting;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bzh.shopping.R;
import com.bzh.shopping.base.fragment.BaseFragment;

public class SetFragment extends BaseFragment {

    private SetViewModel mViewModel;

    public static SetFragment newInstance() {
        return new SetFragment();
    }

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.fragment_set;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(SetViewModel.class);
        // TODO: Use the ViewModel
    }

}
