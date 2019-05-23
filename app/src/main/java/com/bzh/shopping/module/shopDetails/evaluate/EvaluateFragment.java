package com.bzh.shopping.module.shopDetails.evaluate;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bzh.shopping.R;
import com.bzh.shopping.base.fragment.BaseFragment;

import butterknife.BindView;
import timber.log.Timber;

public class EvaluateFragment extends BaseFragment {

    private EvaluateViewModel mViewModel;

    @BindView(R.id.fe_tx)
    TextView mTextView;

    public static EvaluateFragment newInstance(String name) {
        EvaluateFragment fragment = new EvaluateFragment();
        Bundle bundle = new Bundle();
        bundle.putString("name",name);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.fragment_evaluate;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        Bundle bundle = getArguments();
        String name = bundle.getString("name","没有数据");
        mTextView.setText(name);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(EvaluateViewModel.class);
        // TODO: Use the ViewModel
    }

}
