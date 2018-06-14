package com.zmm.tmsystem.mvp.presenter.contract;

import com.zmm.tmsystem.bean.BaseBean;
import com.zmm.tmsystem.bean.TeacherBean;
import com.zmm.tmsystem.mvp.view.BaseView;

import io.reactivex.Observable;

/**
 * Description:
 * Author:zhangmengmeng
 * Date:2018/6/12
 * Time:下午4:10
 */

public interface UserContract {

    interface UserModel{

        Observable<BaseBean<String>> getVerifyCode(String phone);

        Observable<BaseBean<TeacherBean>> forgetPassword(String phone, String newPassword, String verifyCode);

        Observable<BaseBean<TeacherBean>> modifyPassword(String id, String newPassword, String verifyCode);

        Observable<BaseBean<TeacherBean>> modifyPhone(String id, String newPhone, String verifyCode);
    }

    interface UserView extends BaseView{

    }
}
