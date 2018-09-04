package com.zmm.tmsystem.mvp.presenter.contract;

import com.zmm.tmsystem.bean.BaseBean;
import com.zmm.tmsystem.mvp.view.BaseView;

import io.reactivex.Observable;
import okhttp3.MultipartBody;

/**
 * Description:
 * Author:zhangmengmeng
 * Date:2018/9/4
 * Email:65489469@qq.com
 */
public interface CertificateInfoContract {

    interface ICertificateInfoModel{
        Observable<BaseBean<String>> uploadCertificatePics(String id, String title,String content, MultipartBody.Part [] part);
    }


    interface CertificateInfoView extends BaseView{

        void uploadSuccess();
    }
}
