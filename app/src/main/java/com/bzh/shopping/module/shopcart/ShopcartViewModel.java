package com.bzh.shopping.module.shopcart;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.util.SparseArray;
import android.util.SparseIntArray;

import com.bzh.shopping.modle.GoodsInfo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import timber.log.Timber;

public class ShopcartViewModel extends ViewModel {
    // TODO: Implement the ViewModel
    private MutableLiveData<SparseArray<GoodsInfo>> goodsInf0Maps = new MutableLiveData<>(); //购物车存放商品信息

    LiveData<SparseArray<GoodsInfo>> getGoodsInf0Maps() {
        return goodsInf0Maps;
    }

    void setGoodsInf0Maps(GoodsInfo goodsInfs,State state) {
        SparseArray<GoodsInfo> map = this.goodsInf0Maps.getValue();
        if(map == null){
            map = new SparseArray<>();
        }
        if(state == State.add){
            map.put(goodsInfs.getId(),goodsInfs);
        }else if(state == State.remove) {
            map.delete(goodsInfs.getId());
        }
        this.goodsInf0Maps.setValue(map);
    }

    /**
     * 定义两种状态
     */
    enum State{
        add,
        remove,
        modify
    }
}
