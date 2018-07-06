package com.zmm.tmsystem.mvp.model;

import com.zmm.tmsystem.bean.BaseBean;
import com.zmm.tmsystem.bean.ChildcareStudentBean;
import com.zmm.tmsystem.bean.StudentBean;
import com.zmm.tmsystem.http.ApiService;
import com.zmm.tmsystem.mvp.presenter.contract.ChildcareStudentContract.IChildcareStudentModel;

import java.util.List;

import io.reactivex.Observable;

/**
 * Description:
 * Author:zhangmengmeng
 * Date:2018/7/2
 * Time:上午11:52
 */

public class ChildcareStudentModel implements IChildcareStudentModel {

    private ApiService mApiService;

    public ChildcareStudentModel(ApiService apiService) {
        mApiService = apiService;
    }

    @Override
    public Observable<BaseBean<List<ChildcareStudentBean>>> queryAllChildcareStudents(String id) {
        return mApiService.queryAllChildcareStudents(id);
    }

    @Override
    public Observable<BaseBean<String>> updateChildcareStudent(ChildcareStudentBean childcareStudentBean) {
        return mApiService.updateChildcareStudent(childcareStudentBean);
    }

    @Override
    public Observable<BaseBean<String>> deleteChildcareStudent(String id) {
        return mApiService.deleteChildcareStudent(id);
    }

    @Override
    public Observable<BaseBean<ChildcareStudentBean>> findChildcareStudentById(String id) {
        return mApiService.findChildcareStudentById(id);
    }

}
