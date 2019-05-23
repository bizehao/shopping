package com.bzh.shopping;

import com.bzh.shopping.di.component.DaggerAppComponent;

import dagger.android.AndroidInjector;
import dagger.android.DaggerApplication;
import timber.log.Timber;

public class App extends DaggerApplication {

    @Override
    public void onCreate() {
        super.onCreate();
        /**
         * 仅在Debug时初始化Timber
         */
        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
        }
    }

    @Override
    protected AndroidInjector<? extends DaggerApplication> applicationInjector() {
        return DaggerAppComponent.builder().create(this);
    }
}
