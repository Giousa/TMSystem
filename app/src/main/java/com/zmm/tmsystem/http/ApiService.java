package com.zmm.tmsystem.http;


import com.zmm.tmsystem.bean.BaseBean;
import com.zmm.tmsystem.bean.TeacherBean;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Description:
 * Author:zhangmengmeng
 * Date:2018/6/6
 * Time:上午10:31
 */

public interface ApiService {

    String BASE_URL = "http://192.168.1.102:8081/tms/";


    /**
     * 获取验证码
     * @param phone
     * @return
     */
    @FormUrlEncoded
    @POST("getVerifyCode/")
    Observable<BaseBean<String>> getVerifyCode(@Field("phone") String phone);


    /**
     * 登录
     * @param phone
     * @param password
     * @return
     */
    @FormUrlEncoded
    @POST("login/")
    Observable<BaseBean<TeacherBean>> login(@Field("phone") String phone, @Field("password") String password);

    /**
     * 注册
     * @param phone
     * @param password
     * @return
     */
    @FormUrlEncoded
    @POST("register/")
    Observable<BaseBean<TeacherBean>> register(@Field("phone") String phone, @Field("password") String password, @Field("verifyCode") String verifyCode);

    /**
     * 忘记密码
     * @param phone
     * @param newPassword
     * @param verifyCode
     * @return
     */
    @FormUrlEncoded
    @POST("forgetPassword/")
    Observable<BaseBean<String>> forgetPassword(@Field("phone") String phone, @Field("newPassword") String newPassword, @Field("verifyCode") String verifyCode);


    /**
     * 修改密码
     * @param id
     * @param newPassword
     * @param verifyCode
     * @return
     */
    @FormUrlEncoded
    @POST("modifyPassword/")
    Observable<BaseBean<String>> modifyPassword(@Field("id") String id, @Field("newPassword") String newPassword, @Field("verifyCode") String verifyCode);




}
