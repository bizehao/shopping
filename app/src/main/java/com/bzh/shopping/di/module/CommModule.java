package com.bzh.shopping.di.module;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import com.bzh.shopping.App;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import javax.inject.Singleton;
import dagger.Binds;
import dagger.Module;
import dagger.Provides;

/**
 * @author 毕泽浩
 * @Description:
 * @time 2018/10/22 22:49
 */
@Module
public abstract class CommModule {

    @Singleton
    @Binds
    public abstract Context context(App application);

    @Singleton
    @Provides
    public static Gson providerGson() { //gson
        return new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
    }

    @Singleton
    @Provides
    public static SharedPreferences providerSharedPreferences(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context);
    }
}
