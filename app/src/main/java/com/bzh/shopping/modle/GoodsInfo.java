package com.bzh.shopping.modle;

import com.bzh.shopping.R;

/**
 * @author 毕泽浩
 * @Description: 购物车商品信息
 * @time 2018/12/4 12:48
 */
public class GoodsInfo extends Goods{
    private int count = 1; //商品数量
    private boolean checkState = true; //物品选中状态

    public GoodsInfo(int id, String name, double price, int totalCount) {
        super(id, name, price, totalCount);
    }

    public GoodsInfo(Goods goods) {
        super(goods);
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public boolean getCheckState() {
        return checkState;
    }

    public void setCheckState(boolean checkState) {
        this.checkState = checkState;
    }

    @Override
    public String toString() {
        return "GoodsInfo{" +
                "count=" + count +
                ", checkState=" + checkState +
                "} " + super.toString();
    }
}
