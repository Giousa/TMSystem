package com.zmm.tmsystem.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.zmm.tmsystem.R;
import com.zmm.tmsystem.common.Constant;
import com.zmm.tmsystem.dagger.component.AppComponent;
import com.zmm.tmsystem.ui.widget.TitleBar;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Description:  注册用户+忘记密码
 * Author:zhangmengmeng
 * Date:2018/6/12
 * Time:下午10:50
 */

public class RegisterActivity extends BaseActivity {

    @BindView(R.id.title_bar)
    TitleBar mTitleBar;
    @BindView(R.id.register_phone)
    EditText mRegisterPhone;
    @BindView(R.id.register_psd)
    EditText mRegisterPsd;
    @BindView(R.id.register_yzm)
    EditText mRegisterYzm;
    @BindView(R.id.register)
    TextView mRegister;

    @Override
    protected int setLayout() {
        return R.layout.activity_register;
    }

    @Override
    protected void init() {

        Bundle bundle = getIntent().getExtras();
        int paramInt = bundle.getInt(Constant.REGISTER_PARAM, 1);

        System.out.println("paramInt = " + paramInt);

        if (paramInt == 1) {
            mTitleBar.setCenterTitle(getResources().getString(R.string.register));
            mRegister.setText(getResources().getString(R.string.register));
        } else {
            mTitleBar.setCenterTitle(getResources().getString(R.string.forget_password));
            mRegister.setText(getResources().getString(R.string.forget_password));
        }

        mTitleBar.setNavigationIcon(R.drawable.ic_action_back);
        mTitleBar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    @Override
    protected void setupActivityComponent(AppComponent appComponent) {

    }


    @OnClick({R.id.register_getYzm, R.id.register})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.register_getYzm:
                break;
            case R.id.register:
                break;
        }
    }

}
