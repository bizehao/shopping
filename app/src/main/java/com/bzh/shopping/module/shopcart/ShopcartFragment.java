package com.bzh.shopping.module.shopcart;

import android.annotation.TargetApi;
import android.app.Activity;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.graphics.Rect;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.bzh.shopping.MainViewModel;
import com.bzh.shopping.R;
import com.bzh.shopping.State;
import com.bzh.shopping.base.fragment.BaseFragment;
import com.bzh.shopping.modle.GoodsInfo;
import com.bzh.widgets.Amount.AmountView;

import java.math.BigDecimal;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import timber.log.Timber;

import static android.content.Context.INPUT_METHOD_SERVICE;

/**
 * 购物车
 */
public class ShopcartFragment extends BaseFragment {

    @BindView(R.id.fs_rcv)
    RecyclerView mRecyclerView;

    @BindView(R.id.fs_total_price)
    TextView mTextView;

    @BindView(R.id.fs_total_check)
    CheckBox mCheckBox;

    @BindView(R.id.fs_settlement)
    Button button;

    @BindView(R.id.fs_trans)
    FrameLayout transformation;

    private ShopcartViewModel mViewModel;

    private ShopcarAdapter adapter = null;

    private MainViewModel mMainViewModel;

    @Inject
    public ShopcartFragment() {
    }

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.fragment_shopcart;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        adapter = new ShopcarAdapter(getActivity());
        adapter.setOnCheckBoxEventListener((goodsInfo, state) -> {
            goodsInfo.setCheckState(state);
            mMainViewModel.handlerGoods(goodsInfo, State.modify);
        });
        adapter.setOnNumChangeEventListener((view, amount, goodsInfo) -> {
            goodsInfo.setCount(amount);
            mMainViewModel.handlerGoods(goodsInfo, State.modify);
        });
        adapter.setOnmNumClickEventListener(goodsInfo -> {
            if (getActivity() != null) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity(), R.style.myDialogTheme);
                View view = LayoutInflater.from(getActivity()).inflate(R.layout.dialog_numchange, null);
                Button sure = view.findViewById(R.id.dn_sure);
                Button cancel = view.findViewById(R.id.dn_close);
                AmountView amountView = view.findViewById(R.id.dn_original_price);
                amountView.setGoods_storage(goodsInfo.getTotalCount());
                amountView.setAmount(goodsInfo.getCount());
                builder.setView(view);
                AlertDialog dialog = builder.create();
                sure.setOnClickListener(v -> {
                    goodsInfo.setCount(amountView.getAmount());
                    mMainViewModel.handlerGoods(goodsInfo, State.modify);
                    InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(INPUT_METHOD_SERVICE);
                    if (amountView.getFocusState()) {
                        //隐藏键盘
                        imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
                    }
                    dialog.cancel();
                });
                cancel.setOnClickListener(v -> {
                    InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(INPUT_METHOD_SERVICE);
                    if (amountView.getFocusState()) {
                        //隐藏键盘
                        imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
                    }
                    dialog.cancel();
                });
                dialog.show();
            }
        });
        adapter.setOnDeleteEventListener(goodsInfo -> {
            mMainViewModel.handlerGoods(goodsInfo, State.remove);
        });
        mRecyclerView.setAdapter(adapter);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(OrientationHelper.VERTICAL);
        mRecyclerView.setLayoutManager(layoutManager);

        mCheckBox.setOnClickListener(v -> {
            SparseArray<GoodsInfo> goodsInfos = adapter.getGoodsInfos();
            for (int i = 0; i < goodsInfos.size(); i++) {
                goodsInfos.valueAt(i).setCheckState(mCheckBox.isChecked());
            }
            mMainViewModel.handlerGoods(null, State.modify);
        });
        button.setOnClickListener(v -> {
            SparseArray<GoodsInfo> goodsInfoList = mViewModel.getGoodsInf0Maps().getValue();
            if (goodsInfoList != null) {
                for (int i = 0; i < goodsInfoList.size(); i++) {
                    Timber.e(goodsInfoList.valueAt(i).toString());
                }
            }
        });
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(ShopcartViewModel.class);
        if (getActivity() != null) {
            mMainViewModel = ViewModelProviders.of(getActivity()).get(MainViewModel.class);
        }
        // TODO: Use the ViewModel
        //主界面的UI控制
        mMainViewModel.getShopCarGoodsData().observe(this, goodsInfoSparseArray -> {
            if (goodsInfoSparseArray != null && goodsInfoSparseArray.size() > 0) {
                if(transformation.getVisibility() == View.VISIBLE){
                    transformation.setVisibility(View.GONE);
                }
                if(mCheckBox.getVisibility() != View.VISIBLE){
                    mCheckBox.setVisibility(View.VISIBLE);
                }
                if(!mCheckBox.isChecked()){
                    mCheckBox.setChecked(true);
                }
                adapter.setGoodsInfos(goodsInfoSparseArray);
                BigDecimal money = new BigDecimal(Double.toString(0));
                for (int i = 0; i < goodsInfoSparseArray.size(); i++) {
                    GoodsInfo goodsInfo = goodsInfoSparseArray.valueAt(i);
                    if (goodsInfo.getCheckState()) { //是否打钩了
                        BigDecimal count = new BigDecimal(Double.toString(goodsInfo.getCount()));
                        BigDecimal price = new BigDecimal(Double.toString(goodsInfo.getPrice()));
                        money = money.add(count.multiply(price));
                    }else {
                        if(mCheckBox.isChecked()){
                            mCheckBox.setChecked(false);
                        }
                    }
                }
                mTextView.setText(String.format("￥%s", money.doubleValue()));
            }else {
                if(transformation.getVisibility() == View.GONE){
                    transformation.setVisibility(View.VISIBLE);
                }
                if(mCheckBox.getVisibility() != View.INVISIBLE){
                    mCheckBox.setVisibility(View.INVISIBLE);
                }
            }
        });
    }

}
