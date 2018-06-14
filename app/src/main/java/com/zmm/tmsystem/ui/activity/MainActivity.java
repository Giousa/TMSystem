package com.zmm.tmsystem.ui.activity;

import com.zmm.tmsystem.R;
import com.zmm.tmsystem.dagger.component.AppComponent;
import com.zmm.tmsystem.dagger.component.DaggerMainComponent;
import com.zmm.tmsystem.dagger.module.MainModule;
import com.zmm.tmsystem.mvp.presenter.MainPresenter;
import com.zmm.tmsystem.mvp.presenter.contract.MainContract;

public class MainActivity extends BaseActivity<MainPresenter> implements MainContract.MainView {

    @Override
    protected int setLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected void init() {

    }

    @Override
    protected void setupActivityComponent(AppComponent appComponent) {
        DaggerMainComponent.builder()
                .appComponent(appComponent)
                .mainModule(new MainModule(this))
                .build()
                .inject(this);
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void showError(String msg) {

    }

    @Override
    public void dismissLoading() {

    }

    @Override
    public void checkParamEmpty() {

    }

    @Override
    public void updateSuccess() {

    }

    @Override
    public void uploadSuccess() {

    }

    @Override
    public void signSuccess() {

    }

    @Override
    public void signExist() {

    }
}
