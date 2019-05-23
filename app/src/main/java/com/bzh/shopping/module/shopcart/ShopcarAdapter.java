package com.bzh.shopping.module.shopcart;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bzh.shopping.R;
import com.bzh.shopping.modle.GoodsInfo;
import com.bzh.widgets.Amount.AmountView;

import java.sql.Time;
import java.util.List;

import timber.log.Timber;

/**
 * @author 毕泽浩
 * @Description:
 * @time 2018/11/12 21:22
 */
public class ShopcarAdapter extends RecyclerView.Adapter<ShopcarAdapter.ViewHolder> {

    private SparseArray<GoodsInfo> goodsInfos;
    private Context mContext;
    private GoodsEventListener listener;

    public ShopcarAdapter(Context mContext) {
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_goods, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        GoodsInfo goodsInfo = goodsInfos.valueAt(i);
        viewHolder.mName.setText(goodsInfo.getName());
        viewHolder.mDescript.setText(goodsInfo.getDescript());
        Glide.with(mContext).load(goodsInfo.getImage()).into(viewHolder.mImageView);
        viewHolder.mPrice.setText(String.valueOf(goodsInfo.getPrice()));
        viewHolder.mAmountView.setGoods_storage(goodsInfo.getTotalCount());
        viewHolder.mAmountView.setAmount(goodsInfo.getCount());
        viewHolder.mAmountView.setEditText(false);
        viewHolder.mCheckBox.setChecked(goodsInfo.getCheckState());
        if(listener != null ){
            viewHolder.mCheckBox.setOnClickListener((buttonView) -> {
                if (listener.mCheckBoxEventListener != null) {
                    listener.mCheckBoxEventListener.onClickListener(goodsInfo,viewHolder.mCheckBox.isChecked());
                }
            });

            viewHolder.mShare.setOnClickListener(v -> {
                if (listener.mShareEventListener != null) {
                    listener.mShareEventListener.onClickListener();
                }
            });

            viewHolder.mDelete.setOnClickListener(v -> {
                if (listener.mDeleteEventListener != null) {
                    listener.mDeleteEventListener.onClickListener(goodsInfo);
                }
            });

            viewHolder.mAmountView.setOnAmountChangeListener((view, amount) -> {
                if (listener.mNumChangeEventListener != null) {
                    listener.mNumChangeEventListener.onClickListener(view, amount, goodsInfo);
                }
            });

            viewHolder.mAmountView.setInputClickListener(val -> {
                if (listener.mNumClickEventListener != null) {
                    listener.mNumClickEventListener.onClickListener(goodsInfo);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        if(goodsInfos != null){
            return goodsInfos.size();
        }
        return 0;
    }

    /**
     * 获取数据信息
     *
     * @return
     */
    SparseArray<GoodsInfo> getGoodsInfos() {
        return goodsInfos;
    }

    /**
     * 设置购物车数据
     * @param goodsInfos
     */
    public void setGoodsInfos(SparseArray<GoodsInfo> goodsInfos) {
        this.goodsInfos = goodsInfos;
        notifyDataSetChanged();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        CheckBox mCheckBox; //单选框
        ImageView mImageView; //商品图片
        TextView mDescript; //商品描述
        TextView mName; //商品名称
        Button mPrice; //商品价格
        AmountView mAmountView; //商品数量
        ImageView mShare; //分享商品
        ImageView mDelete; //移除商品


        ViewHolder(@NonNull View itemView) {
            super(itemView);
            mCheckBox = itemView.findViewById(R.id.ig_check_box);
            mImageView = itemView.findViewById(R.id.ig_img);
            mDescript = itemView.findViewById(R.id.ig_descript);
            mName = itemView.findViewById(R.id.ig_name);
            mPrice = itemView.findViewById(R.id.ig_collage_price);
            mAmountView = itemView.findViewById(R.id.ig_original_price);
            mShare = itemView.findViewById(R.id.ig_share);
            mDelete = itemView.findViewById(R.id.ig_delete);
        }
    }

    void setOnCheckBoxEventListener(CheckBoxEventListener listener) {
        getListener().mCheckBoxEventListener = listener;
    }

    void setOnShareEventListener(ShareEventListener listener) {
        getListener().mShareEventListener = listener;
    }

    void setOnDeleteEventListener(DeleteEventListener listener) {
        getListener().mDeleteEventListener = listener;
    }

    void setOnNumChangeEventListener(NumChangeEventListener listener) {
        getListener().mNumChangeEventListener = listener;
    }

    void setOnmNumClickEventListener(NumClickEventListener listener) {
        getListener().mNumClickEventListener = listener;
    }

    //事件集合类
    private static class GoodsEventListener {
        CheckBoxEventListener mCheckBoxEventListener;
        ShareEventListener mShareEventListener;
        DeleteEventListener mDeleteEventListener;
        NumChangeEventListener mNumChangeEventListener;
        NumClickEventListener mNumClickEventListener;
    }

    private GoodsEventListener getListener() {
        if (listener != null) {
            return listener;
        }
        listener = new GoodsEventListener();
        return listener;
    }

    //checkBox选中事件
    interface CheckBoxEventListener {
        void onClickListener(GoodsInfo goodsInfo,boolean state);
    }

    //分享按钮的点击事件
    interface ShareEventListener {
        void onClickListener();
    }

    //移除按钮的移除事件
    interface DeleteEventListener {
        void onClickListener(GoodsInfo goodsInfo);
    }

    //数量加减框的点击事件
    interface NumChangeEventListener {
        void onClickListener(View view, int amount, GoodsInfo goodsInfo);
    }

    //数字加减框的点击输入事件
    interface NumClickEventListener {
        void onClickListener(GoodsInfo goodsInfo);
    }

}
