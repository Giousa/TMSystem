package com.zmm.tmsystem.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.LayoutInflaterCompat;
import android.support.v7.app.AppCompatActivity;

import com.mikepenz.iconics.context.IconicsLayoutInflater2;
import com.zmm.tmsystem.application.AppApplication;
import com.zmm.tmsystem.dagger.component.AppComponent;
import com.zmm.tmsystem.mvp.presenter.BasePresenter;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Description:
 * Author:zhangmengmeng
 * Date:2018/5/24
 * Time:下午1:13
 */

public abstract class BaseActivity<T extends BasePresenter> extends AppCompatActivity {

    private Unbinder mUnbinder;
    protected AppApplication mAppApplication;

    @Inject
    T mPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        LayoutInflaterCompat.setFactory2(getLayoutInflater(), new IconicsLayoutInflater2(getDelegate()));

        super.onCreate(savedInstanceState);


        setContentView(setLayout());

        mUnbinder = ButterKnife.bind(this);

        mAppApplication = (AppApplication) getApplication();
        setupActivityComponent(mAppApplication.getAppComponent());

        init();

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(mUnbinder != Unbinder.EMPTY){
            mUnbinder.unbind();
        }
    }

    protected abstract int setLayout();

    protected abstract void init();

    protected abstract void setupActivityComponent(AppComponent appComponent);

    protected void startActivity(Class activity) {

        startActivity(activity,true);
    }

    protected void startActivity(Class activity, boolean finish) {
        Intent intent = new Intent(this, activity);
        startActivity(intent);
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
        if (finish) {
            finish();
        }
    }

}
