package com.bzh.shopping.di.component;

import com.bzh.shopping.App;
import com.bzh.shopping.di.module.ActivityModule;
import com.bzh.shopping.di.module.CommModule;
import com.bzh.shopping.di.module.FragmentModule;

import javax.inject.Singleton;

import dagger.Component;
import dagger.android.AndroidInjector;
import dagger.android.support.AndroidSupportInjectionModule;

@Singleton
@Component(modules = {AndroidSupportInjectionModule.class,
        CommModule.class, ActivityModule.class, FragmentModule.class})
public interface AppComponent extends AndroidInjector<App> {

    @Component.Builder
    abstract class Builder extends AndroidInjector.Builder<App> {
    }

}
