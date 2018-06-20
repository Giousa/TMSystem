package com.zmm.tmsystem.mvp.model;

import com.zmm.tmsystem.bean.BaseBean;
import com.zmm.tmsystem.bean.StudentBean;
import com.zmm.tmsystem.http.ApiService;
import com.zmm.tmsystem.mvp.presenter.contract.StudentContract;

import java.util.List;

import io.reactivex.Observable;

/**
 * Description:
 * Author:zhangmengmeng
 * Date:2018/6/20
 * Time:下午2:17
 */

public class StudentModel implements StudentContract.IStudentModel {

    private ApiService mApiService;

    public StudentModel(ApiService apiService) {
        mApiService = apiService;
    }

    @Override
    public Observable<BaseBean<StudentBean>> addStudent(StudentBean studentBean) {
        return mApiService.addStudent(studentBean);
    }

    @Override
    public Observable<BaseBean<String>> updateStudent(StudentBean studentBean) {
        return mApiService.updateStudent(studentBean);
    }

    @Override
    public Observable<BaseBean<String>> deleteStudent(String id) {
        return mApiService.deleteStudent(id);
    }

    @Override
    public Observable<BaseBean<List<StudentBean>>> queryAllStudentsByChildcareId(String id) {
        return mApiService.queryAllStudentsByChildcareId(id);
    }

    @Override
    public Observable<BaseBean<List<StudentBean>>> queryAllStudentsByCramId(String id) {
        return mApiService.queryAllStudentsByCramId(id);
    }
}
