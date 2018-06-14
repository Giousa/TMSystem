package com.zmm.tmsystem.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.jakewharton.rxbinding2.InitialValueObservable;
import com.jakewharton.rxbinding2.view.RxView;
import com.jakewharton.rxbinding2.widget.RxTextView;
import com.zmm.tmsystem.R;
import com.zmm.tmsystem.common.Constant;
import com.zmm.tmsystem.dagger.component.AppComponent;
import com.zmm.tmsystem.ui.widget.LoadingButton;
import com.zmm.tmsystem.ui.widget.TitleBar;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observable;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function3;

/**
 * Description:  注册用户+忘记密码
 * Author:zhangmengmeng
 * Date:2018/6/12
 * Time:下午10:50
 */

public class RegisterActivity extends BaseActivity {

    @BindView(R.id.title_bar)
    TitleBar mTitleBar;
    @BindView(R.id.btn_login)
    LoadingButton mBtnLogin;
    @BindView(R.id.et_phone)
    EditText mEtPhone;
    @BindView(R.id.et_psd)
    EditText mEtPsd;
    @BindView(R.id.et_yzm)
    EditText mEtYzm;
    @BindView(R.id.tv_getYzm)
    TextView mTvGetYzm;

    @Override
    protected int setLayout() {
        return R.layout.activity_register;
    }

    @Override
    protected void init() {


        mTitleBar.setNavigationIcon(R.drawable.ic_action_back);
        mTitleBar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        Bundle bundle = getIntent().getExtras();
        int paramInt = bundle.getInt(Constant.REGISTER_PARAM, 1);

        System.out.println("paramInt = " + paramInt);

        if (paramInt == 1) {
            mTitleBar.setCenterTitle(getResources().getString(R.string.register));
            mBtnLogin.setText(getResources().getString(R.string.register));
            mBtnLogin.setLoadingText(getResources().getString(R.string.register_loading));
        } else {
            mTitleBar.setCenterTitle(getResources().getString(R.string.forget_password));
            mBtnLogin.setText(getResources().getString(R.string.reset_password));
            mBtnLogin.setLoadingText(getResources().getString(R.string.reset_password_loading));
        }


        InitialValueObservable<CharSequence> obMobi = RxTextView.textChanges(mEtPhone);
        InitialValueObservable<CharSequence> obPassword = RxTextView.textChanges(mEtPsd);
        InitialValueObservable<CharSequence> obYzm = RxTextView.textChanges(mEtYzm);

        Observable.combineLatest(obMobi, obPassword, obYzm, new Function3<CharSequence, CharSequence, CharSequence, Boolean>() {
            @Override
            public Boolean apply(CharSequence phone, CharSequence password, CharSequence yzm) throws Exception {
                return isPhoneValid(phone.toString()) && isPasswordValid(password.toString()) && isYzmValid(yzm.toString());
            }
        }).subscribe(new Consumer<Boolean>() {
            @Override
            public void accept(Boolean aBoolean) throws Exception {
                RxView.enabled(mBtnLogin).accept(aBoolean);
            }

        });

        RxView.clicks(mTvGetYzm).subscribe(new Consumer<Object>() {
            @Override
            public void accept(Object o) throws Exception {

                
            }
        });

        RxView.clicks(mBtnLogin).subscribe(new Consumer<Object>() {
            @Override
            public void accept(Object o) throws Exception {
            }
        });


    }

    private boolean isYzmValid(String yzm) {
        return yzm.length() == 6;
    }

    private boolean isPasswordValid(String password) {
        return password.length() >= 6 && password.length() <= 16;
    }

    private boolean isPhoneValid(String phone) {
        return phone.length() == 11;
    }

    @Override
    protected void setupActivityComponent(AppComponent appComponent) {

    }

}
