package com.bzh.shopping.module.shopDetails;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MediatorLiveData;
import android.arch.lifecycle.MutableLiveData;
import android.support.v4.widget.NestedScrollView;
import android.widget.ScrollView;

/**
 * @author 毕泽浩
 * @Description:
 * @time 2018/11/19 16:23
 */
public class CommFilte {

    private static CommFilte commFilte;

    public static synchronized CommFilte getCommFilte() {
        if (commFilte == null) {
            commFilte = new CommFilte();
        }
        return commFilte;
    }

    private MutableLiveData<Float> floatLiveData = new MutableLiveData<>(); //toolbar透明度
    private float scrollViewHeight; //NestedScrollView距离顶部的高度
    private float scrollViewY; //初始化时NestedScrollView距离顶部的高度

    public LiveData<Float> getFloatLiveData() {
        return floatLiveData;
    }

    public void setFloatLiveData(Float value) {
        floatLiveData.setValue(value);
    }

    public float getScrollViewHeight() {
        return scrollViewHeight;
    }

    public void setScrollViewHeight(float scrollViewHeight) {
        this.scrollViewHeight = scrollViewHeight;
    }

    public float getScrollViewY() {
        return scrollViewY;
    }

    public void setScrollViewY(float scrollViewY) {
        this.scrollViewY = scrollViewY;
    }
}
