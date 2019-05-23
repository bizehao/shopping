package com.bzh.shopping.base.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import dagger.android.support.DaggerFragment;

public abstract class BaseFragment extends DaggerFragment {

    private Unbinder mUnbinder;

    /**
     * 获取布局ID
     */
    protected abstract int getContentViewLayoutID();

    /**
     * 界面初始化
     */
    protected abstract void initView(Bundle savedInstanceState);

    /**
     * 执行操作
     */
    protected void initViewAfter(){};

    protected void liveHandler(Bundle savedInstanceState){};

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (getContentViewLayoutID() != 0) {
            return inflater.inflate(getContentViewLayoutID(), container, false);
        } else {
            return super.onCreateView(inflater, container, savedInstanceState);
        }
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mUnbinder = ButterKnife.bind(this, view);
        initView(savedInstanceState);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        liveHandler(savedInstanceState);
    }

    @Override
    public void onStart() {
        super.onStart();
        initViewAfter();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (mUnbinder != null && mUnbinder != Unbinder.EMPTY) {
            mUnbinder.unbind();
            mUnbinder = null;
        }
    }
}
