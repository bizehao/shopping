package com.bzh.shopping.module.search;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bzh.shopping.R;
import com.bzh.shopping.base.fragment.BaseFragment;
import com.bzh.widgets.linkageMenus.LinkBean;
import com.bzh.widgets.linkageMenus.LinkLeftBean;
import com.bzh.widgets.linkageMenus.LinkageMenus;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;

public class SearchFragment extends BaseFragment {

    @BindView(R.id.fs_link_menus)
    LinkageMenus mLinkageMenus;

    private List<LinkLeftBean> list1;
    private List<LinkBean> list2;

    private SearchViewModel mViewModel;

    @Inject
    public SearchFragment() {
    }

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.fragment_search;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        getDatas();
        getRightDatas();
        mLinkageMenus.setData(list1).setLinkBean(list2).build();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(SearchViewModel.class);
        // TODO: Use the ViewModel
    }

    public void getDatas() {
        list1 = new ArrayList<>();
        list1.add(new LinkLeftBean("男装"));
        list1.add(new LinkLeftBean("女装"));
        list1.add(new LinkLeftBean("童装"));
        list1.add(new LinkLeftBean("食物"));
        list1.add(new LinkLeftBean("数码"));
        list1.add(new LinkLeftBean("电脑"));
        list1.add(new LinkLeftBean("家电"));
        list1.add(new LinkLeftBean("鞋子"));
        list1.add(new LinkLeftBean("衣服"));
        list1.add(new LinkLeftBean("游戏"));
        list1.add(new LinkLeftBean("电影"));
        list1.add(new LinkLeftBean("歌曲"));
        list1.add(new LinkLeftBean("自行车"));
        list1.add(new LinkLeftBean("旅游"));
        list1.add(new LinkLeftBean("名表名包"));
        list1.add(new LinkLeftBean("化妆品"));
        list1.add(new LinkLeftBean("水果"));
    }

    public void getRightDatas() {
        list2 = new ArrayList<>();
        List<LinkBean.InnerType> llsts = new ArrayList<>();
        for (int i = 0; i < 9; i++) {
            llsts.add(new LinkBean.InnerType("中药", R.drawable.girl));
        }
        for (int i = 0; i < list1.size(); i++) {
            LinkBean linkBean = new LinkBean(list1.get(i).getTypeName(), llsts);
            list2.add(linkBean);
        }
    }
}
