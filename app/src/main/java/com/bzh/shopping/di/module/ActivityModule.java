package com.bzh.shopping.di.module;

import com.bzh.shopping.MainActivity;
import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * @author 毕泽浩
 * @Description:
 * @time 2018/11/9 14:14
 */
@Module
public abstract class ActivityModule {

    //主页
    @ContributesAndroidInjector
    abstract MainActivity contributesMainActivity();
}
