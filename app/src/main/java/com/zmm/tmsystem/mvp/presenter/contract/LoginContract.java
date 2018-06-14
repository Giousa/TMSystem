package com.zmm.tmsystem.mvp.presenter.contract;


import com.zmm.tmsystem.bean.BaseBean;
import com.zmm.tmsystem.bean.TeacherBean;
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


        Observable<BaseBean<String>> getVerifyCode(String phone);

        Observable<BaseBean<TeacherBean>> login(String phone, String password);

        Observable<BaseBean<TeacherBean>> oneKeyLogin(String phone, String verifyCode);


    }


    interface LoginView extends BaseView {

        void checkPhoneError();
        void checkPasswprdError();
        void loginSuccess(TeacherBean bean);
        void loginError();

    }
}
