package com.zmm.tmsystem.mvp.presenter.contract;


import com.zmm.tmsystem.bean.BaseBean;
import com.zmm.tmsystem.bean.UserBean;
import com.zmm.tmsystem.mvp.view.BaseView;

import io.reactivex.Observable;

/**
 * Description:
 * Author:zhangmengmeng
 * Date:2018/6/6
 * Time:上午11:27
 */

public interface LoginContract {

    interface LoginModel{


        Observable<BaseBean<UserBean>> login(String phone, String password);

        Observable<BaseBean<UserBean>> oneKeyLogin(String phone, String verifyCode);


    }


    interface LoginView extends BaseView {

        void checkPhoneError();
        void checkPhoneSuccess();
        void loginSuccess(UserBean bean);

    }
}
