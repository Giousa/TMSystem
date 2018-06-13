package com.zmm.tmsystem.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.zmm.tmsystem.R;
import com.zmm.tmsystem.dagger.component.AppComponent;
import com.zmm.tmsystem.ui.widget.TitleBar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Description:
 * Author:zhangmengmeng
 * Date:2018/6/12
 * Time:下午9:33
 */

public class LoginActivity extends BaseActivity {

    @BindView(R.id.et_login_phone)
    EditText mEtLoginPhone;
    @BindView(R.id.et_login_password)
    EditText mEtLoginPassword;
    @BindView(R.id.title_bar)
    TitleBar mTitleBar;


    @Override
    protected int setLayout() {
        return R.layout.activity_login;
    }

    @Override
    protected void init() {

        mTitleBar.setCenterTitle(getResources().getString(R.string.login));
    }

    @Override
    protected void setupActivityComponent(AppComponent appComponent) {

    }

    @OnClick({R.id.tv_login, R.id.tv_login_register, R.id.tv_login_forget})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_login:
                break;
            case R.id.tv_login_register:

                break;
            case R.id.tv_login_forget:
                break;
        }
    }

}
