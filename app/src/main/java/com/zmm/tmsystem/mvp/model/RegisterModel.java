package com.zmm.tmsystem.mvp.model;

import com.zmm.tmsystem.bean.BaseBean;
import com.zmm.tmsystem.bean.TeacherBean;
import com.zmm.tmsystem.http.ApiService;
import com.zmm.tmsystem.mvp.presenter.contract.RegisterContract;

import io.reactivex.Observable;

/**
 * Description:
 * Author:zhangmengmeng
 * Date:2018/6/14
 * Time:下午2:30
 */

public class RegisterModel implements RegisterContract.IRegisterModel {


    private ApiService mApiService;

    public RegisterModel(ApiService apiService) {
        mApiService = apiService;
    }

    @Override
    public Observable<BaseBean<String>> getVerifyCode(String phone) {

        Observable<BaseBean<String>> verifyCode = mApiService.getVerifyCode(phone);

        return verifyCode;
    }

    @Override
    public Observable<BaseBean<TeacherBean>> register(String phone, String password) {

        Observable<BaseBean<TeacherBean>> register = mApiService.register(phone, password);
        return register;
    }

    @Override
    public Observable<BaseBean<TeacherBean>> forgetPassword(String phone, String newPassword, String verifyCode) {

        Observable<BaseBean<TeacherBean>> forgetPassword = mApiService.forgetPassword(phone, newPassword, verifyCode);
        return forgetPassword;
    }

    @Override
    public Observable<BaseBean<TeacherBean>> modifyPassword(String id, String newPassword, String verifyCode) {
        return null;
    }

    @Override
    public Observable<BaseBean<TeacherBean>> oneKeyRegister(String phone, String verifyCode) {
        return null;
    }
}