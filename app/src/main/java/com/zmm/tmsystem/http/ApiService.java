package com.zmm.tmsystem.http;


import com.zmm.tmsystem.bean.BaseBean;
import com.zmm.tmsystem.bean.ChildcareListBean;
import com.zmm.tmsystem.bean.ChildcareStudentBean;
import com.zmm.tmsystem.bean.SchoolBean;
import com.zmm.tmsystem.bean.StudentBean;
import com.zmm.tmsystem.bean.TeacherBean;
import com.zmm.tmsystem.bean.TermBean;

import java.util.List;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Description:
 * Author:zhangmengmeng
 * Date:2018/6/6
 * Time:上午10:31
 */

public interface ApiService {


    /**
     * -----------------------------登录注册界面接口-----------------------------
     */

    /**
     * 获取验证码
     * @param phone
     * @return
     */
    @GET("getVerifyCode/{phone}")
    Observable<BaseBean<String>> getVerifyCode(@Path("phone") String phone);


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
     * 修改手机号或密码
     * @param id
     * @param type
     * @param content
     * @param verifyCode
     * @return
     */
    @FormUrlEncoded
    @POST("modifyByType/")
    Observable<BaseBean<String>> modifyByType(@Field("id") String id, @Field("type") int type, @Field("content") String content, @Field("verifyCode") String verifyCode);


    /**
     * -----------------------------Home界面接口-----------------------------
     */


    /**
     * 根据id获取教师信息
     * @param id
     * @return
     */
    @GET("getTeacherById/{id}")
    Observable<BaseBean<TeacherBean>> getTeacherById(@Path("id") String id);


    /**
     * 获取当前签到信息
     * @param tId
     * @return
     */
    @GET("signInfo/{tId}")
    Observable<BaseBean<String>> signInfo(@Path("tId") String tId);

    /**
     * 点击签到
     * @param tId
     * @return
     */
    @GET("sign/{tId}")
    Observable<BaseBean<String>> sign(@Path("tId") String tId);


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
    @GET("querySchools/")
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


    @Multipart
    @POST("uploadTeacherPic/{id}")
    Observable<BaseBean<TeacherBean>> uploadTeacherPic( @Path("id") String id,  @Part() MultipartBody.Part file);


    /**
     * -----------------------------托管管理界面接口-----------------------------
     */


    /**
     * 创建托管中心信息
     * @param termBean
     * @return
     */
    @POST("createNewTerm/")
    Observable<BaseBean<TermBean>> createNewTerm(@Body TermBean termBean);

    /**
     * 获取所有托管信列表
     * @param tId
     * @return
     */
    @GET("queryAllTerm/{tId}")
    Observable<BaseBean<List<TermBean>>> queryAllTerm(@Path("tId") String tId);

    /**
     * 根据id获取托管信息
     * @param id
     * @return
     */
    @GET("queryTermById//{id}")
    Observable<BaseBean<TermBean>> queryTermById(@Path("id") String id);

    /**
     * 更新托管资料
     * @param termBean
     * @return
     */
    @POST("updateTerm/")
    Observable<BaseBean<TermBean>> updateTerm(@Body TermBean termBean);

    /**
     * 删除托管资料
     * @param id
     * @return
     */
    @GET("deleteTerm//{id}")
    Observable<BaseBean<String>> deleteTerm(@Path("id") String id);


    /**
     * -----------------------------学生相关接口-----------------------------
     */

    /**
     * 添加学生
     * @param studentBean
     * @return
     */
    @POST("addStudent/")
    Observable<BaseBean<StudentBean>> addStudent(@Body StudentBean studentBean);

    /**
     * 更新学生
     * @param studentBean
     * @return
     */
    @POST("updateStudent/")
    Observable<BaseBean<String>> updateStudent(@Body StudentBean studentBean);


    /**
     * 删除学生
     * @param id
     * @return
     */
    @GET("deleteStudent/{id}")
    Observable<BaseBean<String>> deleteStudent(@Path("id") String id);

    /**
     * 移除学生
     * @param id
     * @return
     */
    @GET("removeStudent/{id}")
    Observable<BaseBean<String>> removeStudent(@Path("id") String id);

    /**
     * 还原学生
     * @param id
     * @return
     */
    @GET("returnStudent/{id}")
    Observable<BaseBean<String>> returnStudent(@Path("id") String id);

    /**
     * 查询学生
     * @param id
     * @return
     */
    @GET("queryAllStudentsByTeacherId/{id}")
    Observable<BaseBean<List<StudentBean>>> queryAllStudentsByTeacherId(@Path("id") String id);

    /**
     * 查询移除学生
     * @param id
     * @return
     */
    @GET("queryRemoveStudentsByTeacherId/{id}")
    Observable<BaseBean<List<StudentBean>>> queryRemoveStudentsByTeacherId(@Path("id") String id);


    /**
     * -----------------------------托管学生相关接口-----------------------------
     */

    /**
     * 添加学生
     * @param idList
     * @return
     */
//    @FormUrlEncoded
    @GET("addChildcareStudents")
    Observable<BaseBean<String>> addChildcareStudents(@Query("termId") String termId, @Query("ids")  List<String> idList);


    /**
     * 根据托管id，查询全部托管学生
     * @param id
     * @return
     */
    @GET("queryAllChildcareStudents/{id}")
    Observable<BaseBean<List<ChildcareStudentBean>>> queryAllChildcareStudents(@Path("id") String id);

    /**
     * 更新托管的学生信息
     * @return
     */
    @FormUrlEncoded
    @POST("updateChildcareStudent")
    Observable<BaseBean<ChildcareStudentBean>> updateChildcareStudent(@Field("type") int type,@Field("id") String id,@Field("level") int level,@Field("content") String content );

    /**
     * 删除托管学生
     * @param id
     * @return
     */
    @GET("deleteChildcareStudent/{id}")
    Observable<BaseBean<String>> deleteChildcareStudent(@Path("id") String id);

    /**
     * 根据托管学生id，获取托管学生信息
     * @param id
     * @return
     */
    @GET("findChildcareStudentById/{id}")
    Observable<BaseBean<ChildcareStudentBean>> findChildcareStudentById(@Path("id") String id);

    @Multipart
    @POST("uploadPics")
    Observable<BaseBean<String>> uploadPics( @Query("t_id") String t_id,  @Query("listJson") String listJson, @Part() MultipartBody.Part [] file);

}
