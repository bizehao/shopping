package com.bzh.shopping.base.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.widget.Toast;
import com.bzh.shopping.R;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import dagger.android.support.DaggerAppCompatActivity;

/**
 * 基础 activity
 *
 */
public abstract class BaseActivity extends DaggerAppCompatActivity {

    protected Unbinder mUnbinder;
    /**
     * 获取布局ID
     *
     * @return
     */
    protected abstract int getContentViewLayoutID();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        beforeInit(); //初始化之前
        if (getContentViewLayoutID() != 0) {
            setContentView(getContentViewLayoutID());
            initView(savedInstanceState); //初始化
        }
    }

    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);
        mUnbinder = ButterKnife.bind(this); //设置 ButterKnife
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mUnbinder != null && mUnbinder != Unbinder.EMPTY) {
            mUnbinder.unbind();
            mUnbinder = null;
        }
    }

    /**
     * 界面初始化前期准备
     */
    protected void beforeInit() { };

    /**
     * 初始化布局以及View控件
     */
    protected abstract void initView(Bundle savedInstanceState);
}
