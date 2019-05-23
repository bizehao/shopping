package com.bzh.shopping.module.recommend;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.bzh.shopping.modle.Goods;

import java.util.List;

public class RecommendViewModel extends ViewModel {
    // TODO: Implement the ViewModel
    private final MutableLiveData<List<Goods>> goodsData = new MutableLiveData<>(); //推荐里的所有商品信息


    public LiveData<List<Goods>> getGoodsData(){
        return goodsData;
    }

    public void setGoodsData(List<Goods> list){
        goodsData.setValue(list);
    }
}
