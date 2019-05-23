package com.bzh.widgets.linkageMenus;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bzh.widgets.R;

import java.util.List;

/**
 * @author 毕泽浩
 * @Description:
 * @time 2018/11/11 19:11
 */
public class LinkageRightInnerAdapter extends RecyclerView.Adapter<LinkageRightInnerAdapter.ViewHolder> {

    private List<LinkBean.InnerType> list;

    LinkageRightInnerAdapter(List<LinkBean.InnerType> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_linkage_rignt_inner, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        LinkBean.InnerType innerType = list.get(i);
        viewHolder.mTextView.setText(innerType.getInnerTypeName());
        viewHolder.mImageView.setImageResource(innerType.getImage());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView mImageView;
        private TextView mTextView;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            mImageView = itemView.findViewById(R.id.ilri_img);
            mTextView = itemView.findViewById(R.id.ilri_tx);
        }
    }
}
