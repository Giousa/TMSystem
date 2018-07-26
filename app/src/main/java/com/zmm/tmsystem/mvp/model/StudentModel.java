package com.zmm.tmsystem.mvp.model;

import com.zmm.tmsystem.bean.BaseBean;
import com.zmm.tmsystem.bean.ChildcareStudentBean;
import com.zmm.tmsystem.bean.StudentBean;
import com.zmm.tmsystem.http.ApiService;
import com.zmm.tmsystem.mvp.presenter.contract.StudentContract;

import java.util.List;

import io.reactivex.Observable;
import okhttp3.MultipartBody;

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
    public Observable<BaseBean<String>> removeStudent(String id) {
        return mApiService.removeStudent(id);
    }

    @Override
    public Observable<BaseBean<String>> returnStudent(String id) {
        return mApiService.returnStudent(id);
    }

    @Override
    public Observable<BaseBean<List<StudentBean>>> queryAllStudentsByTeacherId(String id) {
        return mApiService.queryAllStudentsByTeacherId(id);
    }

    @Override
    public Observable<BaseBean<List<StudentBean>>> queryRemoveStudentsByTeacherId(String id) {
        return mApiService.queryRemoveStudentsByTeacherId(id);
    }

    @Override
    public Observable<BaseBean<String>> addChildcareStudents(String termId, List<String> idList) {
        return mApiService.addChildcareStudents(termId,idList);
    }

    @Override
    public Observable<BaseBean<StudentBean>> uploadStudentPic(String id, MultipartBody.Part file) {
        return mApiService.uploadStudentPic(id,file);
    }


}
