package com.bzh.shopping;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.support.annotation.NonNull;
import android.util.SparseArray;

import com.bzh.shopping.modle.Goods;
import com.bzh.shopping.modle.GoodsInfo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import timber.log.Timber;

/**
 * @author 毕泽浩
 * @Description:
 * @time 2018/11/17 13:03
 */
public class MainViewModel extends ViewModel {
    private final MutableLiveData<Boolean> isToolbarShow = new MutableLiveData<>(); //是否展示 fragment 的 toolbar

    private final MutableLiveData<SparseArray<GoodsInfo>> shopCarGoodsData = new MutableLiveData<>(); //购物车里的所有商品信息

    public MainViewModel() {
        this.isToolbarShow.setValue(true);
    }

    public LiveData<Boolean> getIsToolbarShow() {
        return isToolbarShow;
    }

    public void setIsToolbarShow(boolean isShow) {
        isToolbarShow.setValue(isShow);
    }


    public LiveData<SparseArray<GoodsInfo>> getShopCarGoodsData() {
        return shopCarGoodsData;
    }

    /**
     * 添加移除修改商品
     *
     * @param goods
     */
    public void handlerGoods(Goods goods, State state) {
        SparseArray<GoodsInfo> array = shopCarGoodsData.getValue();
        if (array == null) {
            array = new SparseArray<>();
        }
        switch (state) {
            case add: //添加商品
                GoodsInfo goodsInfo = new GoodsInfo(goods);
                GoodsInfo value = array.get(goodsInfo.getId());
                if (value != null) {
                    value.setCount(value.getCount() + 1);
                } else {
                    array.put(goodsInfo.getId(), goodsInfo);
                }
                break;
            case remove: //移除商品
                array.remove(goods.getId());
                break;
            case modify: //修改商品
                Timber.e("修改商品");
                break;
        }
        shopCarGoodsData.setValue(array);
    }
}
