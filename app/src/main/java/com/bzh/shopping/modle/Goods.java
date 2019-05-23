package com.bzh.shopping.modle;

import com.bzh.shopping.R;

/**
 * @author 毕泽浩
 * @Description: 货物商品
 * @time 2018/12/7 11:53
 */
public class Goods {
    private int id; //商品id
    private String name; //商品名称
    private int image = R.drawable.tta; //商品图片
    private double price; //商品单价
    private int totalCount; //商品总数量
    private String descript = "暂无该商品描述"; //商品描述

    public Goods() {}

    public Goods(int id, String name, double price, int totalCount) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.totalCount = totalCount;
    }


    public Goods(Goods goods) {
        if(goods != null){
            this.id = goods.id;
            this.name = goods.name;
            this.image = goods.image;
            this.price = goods.price;
            this.totalCount = goods.totalCount;
            this.descript = goods.descript;
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public String getDescript() {
        return descript;
    }

    public void setDescript(String descript) {
        this.descript = descript;
    }

    @Override
    public String toString() {
        return "Goods{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", image=" + image +
                ", price=" + price +
                ", totalCount=" + totalCount +
                ", descript='" + descript + '\'' +
                '}';
    }
}
