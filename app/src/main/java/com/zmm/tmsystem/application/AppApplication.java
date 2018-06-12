package com.zmm.tmsystem.application;

import android.app.Application;
import android.content.Context;
import android.view.View;

import com.zmm.tmsystem.dagger.component.AppComponent;
import com.zmm.tmsystem.dagger.component.DaggerAppComponent;
import com.zmm.tmsystem.dagger.module.AppModule;
import com.zmm.tmsystem.dagger.module.HttpModule;


/**
 * Description:
 * Author:zhangmengmeng
 * Date:2018/5/23
 * Time:下午5:56
 */

public class AppApplication extends Application {


    private AppComponent mAppComponent;


    public AppComponent getAppComponent() {
        return mAppComponent;
    }

    private View mView;

    public View getView() {
        return mView;
    }

    public void setView(View view) {
        mView = view;
    }

    public static AppApplication get(Context context){
        return (AppApplication) context.getApplicationContext();
    }

    @Override
    public void onCreate() {
        super.onCreate();

        mAppComponent = DaggerAppComponent.builder().httpModule(new HttpModule()).appModule(new AppModule(this)).build();

    }
}
