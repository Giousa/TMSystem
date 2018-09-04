package com.zmm.tmsystem.mvp.model;

import com.zmm.tmsystem.bean.BaseBean;
import com.zmm.tmsystem.http.ApiService;
import com.zmm.tmsystem.mvp.presenter.contract.CertificateInfoContract;

import io.reactivex.Observable;
import okhttp3.MultipartBody;

/**
 * Description:
 * Author:zhangmengmeng
 * Date:2018/9/4
 * Email:65489469@qq.com
 */
public class CertificateInfoModel implements CertificateInfoContract.ICertificateInfoModel {

    private ApiService mApiService;


    public CertificateInfoModel(ApiService apiService) {
        mApiService = apiService;
    }

    @Override
    public Observable<BaseBean<String>> uploadCertificatePics(String id, String title, String content, MultipartBody.Part[] part) {
        return mApiService.uploadCertificatePics(id,title,content,part);
    }
}
