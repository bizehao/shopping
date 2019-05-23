package com.bzh.shopping.module.test;

import android.content.Context;
import android.support.v7.widget.AppCompatButton;
import android.util.AttributeSet;
import android.view.MotionEvent;

import timber.log.Timber;

/**
 * @author 毕泽浩
 * @Description:
 * @time 2018/11/19 11:22
 */
public class MyButtonOne extends AppCompatButton {

    public MyButtonOne(Context context) {
        super(context);
    }

    public MyButtonOne(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Timber.e("我是button1的onTouchEvent");
        return false;
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        Timber.e("我是button1的dispatchTouchEvent");
        return super.dispatchTouchEvent(event);
    }

}
