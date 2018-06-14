package com.zmm.tmsystem.mvp.presenter;

import com.zmm.tmsystem.mvp.presenter.contract.MainContract;

import javax.inject.Inject;

/**
 * Description:
 * Author:zhangmengmeng
 * Date:2018/6/14
 * Time:下午11:51
 */

public class MainPresenter extends BasePresenter<MainContract.IMainModel,MainContract.MainView> {


    @Inject
    public MainPresenter(MainContract.IMainModel model, MainContract.MainView view) {
        super(model, view);
    }


}
