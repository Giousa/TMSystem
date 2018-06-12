package com.zmm.tmsystem.http;


import com.zmm.tmsystem.bean.BaseBean;

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

    String BASE_URL = "http://localhost:8081/tms/";


    /**
     * 应用详情界面
     * @param phone
     * @return
     */
    @FormUrlEncoded
    @POST("getVerifyCode/")
    Observable<BaseBean<String>> getVerifyCode(@Field("phone") String phone);

}
