package com.zmm.tmsystem.http;


import com.zmm.tmsystem.bean.BaseBean;
import com.zmm.tmsystem.bean.SchoolBean;
import com.zmm.tmsystem.bean.TeacherBean;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.Body;
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

    String BASE_URL = "http://192.168.1.101:8081/tms/";


    /**
     * -----------------------------登录注册界面接口-----------------------------
     */

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
    Observable<BaseBean<String>> register(@Field("phone") String phone, @Field("password") String password, @Field("verifyCode") String verifyCode);

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


    /**
     * -----------------------------Home界面接口-----------------------------
     */


    /**
     * 根据id获取教师信息
     * @param id
     * @return
     */
    @FormUrlEncoded
    @POST("getTeacherById/")
    Observable<BaseBean<TeacherBean>> getTeacherById(@Field("id") String id);


    /**
     * 获取当前签到信息
     * @param tId
     * @return
     */
    @FormUrlEncoded
    @POST("signInfo/")
    Observable<BaseBean<String>> signInfo(@Field("tId") String tId);

    /**
     * 点击签到
     * @param tId
     * @return
     */
    @FormUrlEncoded
    @POST("sign/")
    Observable<BaseBean<String>> sign(@Field("tId") String tId);


    /**
     * -----------------------------教师信息界面接口-----------------------------
     */


    /**
     * 更新用户信息
     * @param teacherBean
     * @return
     */
    @FormUrlEncoded
    @POST("getTeacherById/")
    Observable<BaseBean<TeacherBean>> updateTeacherInfo(@Body TeacherBean teacherBean);

    /**
     * 获取学校信息
     * @return
     */
    @POST("querySchools/")
    Observable<BaseBean<List<SchoolBean>>> querySchools();

    /**
     * 根据类型更新教师信息
     * @param id
     * @param type
     * @param content
     * @return
     */
    @FormUrlEncoded
    @POST("updateTeacherByType/")
    Observable<BaseBean<TeacherBean>> updateTeacherByType(@Field("id")String id, @Field("type")int type, @Field("content")String content);


}
