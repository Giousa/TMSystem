package com.zmm.tmsystem.application;

import android.app.Application;
import android.content.Context;
import android.view.View;

import com.zmm.tmsystem.dagger.component.AppComponent;
import com.zmm.tmsystem.dagger.component.DaggerAppComponent;
import com.zmm.tmsystem.dagger.module.AppModule;
import com.zmm.tmsystem.dagger.module.HttpModule;
import com.zmm.tmsystem.ui.activity.BaseActivity;

import java.util.ArrayList;
import java.util.List;


/**
 * Description:
 * Author:zhangmengmeng
 * Date:2018/5/23
 * Time:下午5:56
 */

public class AppApplication extends Application {


    private AppComponent mAppComponent;
    private View mView;

    private List<BaseActivity> mBaseActivityList;


    public AppComponent getAppComponent() {
        return mAppComponent;
    }


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
        mBaseActivityList = new ArrayList<>();
        mAppComponent = DaggerAppComponent.builder().httpModule(new HttpModule()).appModule(new AppModule(this)).build();

    }

    /**
     * 添加Activity
     */
    public void addActivity_(BaseActivity activity) {
        if (!mBaseActivityList.contains(activity)) {
            mBaseActivityList.add(activity);//把当前Activity添加到集合中
        }
    }

    /**
     * 销毁单个Activity
     */
    public void removeActivity_(BaseActivity activity) {
        if (mBaseActivityList.contains(activity)) {
            mBaseActivityList.remove(activity);//从集合中移除
            activity.finish();//销毁当前Activity
        }
    }


    /**
     * 销毁全部Activity
     */
    public void removeAllActivity_(){
        for (BaseActivity activity:mBaseActivityList) {
            activity.finish();
        }
    }
}
