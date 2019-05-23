package com.bzh.shopping.module.me;

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

import javax.inject.Inject;

public class MeFragment extends BaseFragment {

    private MeViewModel mViewModel;

    @Inject
    public MeFragment(){};

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.fragment_me;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(MeViewModel.class);
        // TODO: Use the ViewModel
    }

}
