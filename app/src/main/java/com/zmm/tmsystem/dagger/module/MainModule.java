package com.zmm.tmsystem.dagger.module;

import com.zmm.tmsystem.http.ApiService;
import com.zmm.tmsystem.mvp.model.MainModel;
import com.zmm.tmsystem.mvp.presenter.contract.MainContract;

import dagger.Module;
import dagger.Provides;

/**
 * Description:
 * Author:zhangmengmeng
 * Date:2018/6/14
 * Time:下午11:48
 */

@Module
public class MainModule {

    private MainContract.MainView mMainView;

    public MainModule(MainContract.MainView mainView) {
        mMainView = mainView;
    }

    @Provides
    public MainContract.MainView provideMainView(){
        return mMainView;
    }

    @Provides
    public MainContract.IMainModel provideMainModel(ApiService apiService){
        return new MainModel(apiService);
    }
}
