package com.bzh.shopping.module.recommend;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bzh.shopping.R;
import com.bzh.shopping.modle.Goods;

import java.util.List;

/**
 * @author 毕泽浩
 * @Description:
 * @time 2018/11/13 20:57
 */
public class RecommendAdapter extends RecyclerView.Adapter<RecommendAdapter.ViewHolder> {

    private List<Goods> goodsList;
    private Context mContext;
    private EventListener eventListener;

    RecommendAdapter(Context mContext) {
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_groom, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        Goods goods = goodsList.get(i);
        viewHolder.goodsName.setText(goods.getName());
        viewHolder.goodCount.setText(String.valueOf(goods.getTotalCount()));
        viewHolder.goodsPrice.setText(String.format("￥%s", goods.getPrice()));
        Glide.with(mContext).load(goods.getImage()).into(viewHolder.goodsImg);
        if(eventListener != null ){
            if (eventListener.recommendListener != null) {
                viewHolder.itemView.setOnClickListener(v -> {
                    eventListener.recommendListener.onClickListener(i);
                });
            }

            if(eventListener.purchaseListener != null){
                viewHolder.goodsPurchase.setOnClickListener(v -> {
                    eventListener.purchaseListener.onClickListener(i);
                });
            }
        }
    }

    @Override
    public int getItemCount() {
        if(goodsList != null){
            return goodsList.size();
        }
        return 0;
    }

    public void setGoodsList(List<Goods> goodsList) {
        this.goodsList = goodsList;
        notifyDataSetChanged();
    }

    public Goods getGoods(int position) {
        if(goodsList != null){
            return goodsList.get(position);
        }else {
            return null;
        }
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView goodsImg; //商品图片
        private TextView goodsName; //商品名称
        private TextView goodsPrice; //商品价格
        private TextView goodCount; //商品数量
        private Button goodsPurchase; //购买商品


        ViewHolder(@NonNull View itemView) {
            super(itemView);
            goodsImg = itemView.findViewById(R.id.ig_img);
            goodsName = itemView.findViewById(R.id.ig_introduce);
            goodsPrice = itemView.findViewById(R.id.ig_price);
            goodCount = itemView.findViewById(R.id.ig_count);
            goodsPurchase = itemView.findViewById(R.id.ig_purchase);
        }
    }

    private static class EventListener{
        private onRecommendListener recommendListener;
        private onPurchaseListener purchaseListener;
    }

    private EventListener getEventListener(){
        if(eventListener != null){
            return eventListener;
        }
        eventListener = new EventListener();
        return eventListener;
    }

    //商品点击事件
    interface onRecommendListener {
        void onClickListener(int position);
    }

    //购买商品事件
    interface onPurchaseListener {
        void onClickListener(int position);
    }

    public void setOnRecommendListener(onRecommendListener listener) {
        getEventListener().recommendListener = listener;
    }

    public void setOnPurchaseListener(onPurchaseListener listener) {
        getEventListener().purchaseListener = listener;
    }
}
