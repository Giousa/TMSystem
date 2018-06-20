package com.zmm.tmsystem.mvp.presenter;

import com.zmm.tmsystem.mvp.presenter.contract.StudentContract;

import javax.inject.Inject;

/**
 * Description:
 * Author:zhangmengmeng
 * Date:2018/6/20
 * Time:下午2:24
 */

public class StudentPresenter extends BasePresenter<StudentContract.IStudentModel,StudentContract.StudentView> {


    @Inject
    public StudentPresenter(StudentContract.IStudentModel model, StudentContract.StudentView view) {
        super(model, view);
    }
}
