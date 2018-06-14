package com.zmm.tmsystem.mvp.presenter.contract;

import com.zmm.tmsystem.bean.BaseBean;
import com.zmm.tmsystem.bean.TeacherBean;
import com.zmm.tmsystem.mvp.view.BaseView;

import io.reactivex.Observable;

/**
 * Description:
 * Author:zhangmengmeng
 * Date:2018/6/12
 * Time:下午4:08
 */

public interface RegisterContract {

    interface IRegisterModel{

        Observable<BaseBean<String>> getVerifyCode(String phone);

        Observable<BaseBean<TeacherBean>> register(String phone, String password);

        Observable<BaseBean<TeacherBean>> forgetPassword(String phone, String newPassword, String verifyCode);

        Observable<BaseBean<TeacherBean>> modifyPassword(String id, String newPassword, String verifyCode);

        Observable<BaseBean<TeacherBean>> oneKeyRegister(String phone, String verifyCode);

    }

    interface RegisterView extends BaseView{

        void checkPhoneError();
        void checkPasswprdError();

        void verifyCodeSuccess();
        void verifyCodeFailure();

        void performSuccess();
        void performError();

    }
}
