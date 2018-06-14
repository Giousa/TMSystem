package com.zmm.tmsystem.mvp.model;

import com.zmm.tmsystem.bean.BaseBean;
import com.zmm.tmsystem.bean.TeacherBean;
import com.zmm.tmsystem.http.ApiService;
import com.zmm.tmsystem.mvp.presenter.contract.MainContract;

import java.io.File;

import io.reactivex.Observable;

/**
 * Description:
 * Author:zhangmengmeng
 * Date:2018/6/14
 * Time:下午11:47
 */

public class MainModel implements MainContract.IMainModel {

    private ApiService mApiService;

    public MainModel(ApiService apiService) {
        mApiService = apiService;
    }

    @Override
    public Observable<BaseBean<TeacherBean>> getTeacherById(String id) {

        Observable<BaseBean<TeacherBean>> observable = mApiService.getTeacherById(id);
        return observable;
    }

    @Override
    public Observable<BaseBean<TeacherBean>> updateTeacherInfo(TeacherBean teacherBean) {

        Observable<BaseBean<TeacherBean>> observable = mApiService.updateTeacherInfo(teacherBean);

        return observable;
    }

    @Override
    public Observable<BaseBean<TeacherBean>> uploadTeacherPic(String id, File uploadFile) {
        return null;
    }

    @Override
    public Observable<BaseBean<String>> signInfo(String tId) {
        return null;
    }

    @Override
    public Observable<BaseBean<String>> sign(String tId) {
        return null;
    }
}
