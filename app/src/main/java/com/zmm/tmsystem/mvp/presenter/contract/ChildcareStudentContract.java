package com.zmm.tmsystem.mvp.presenter.contract;

import com.zmm.tmsystem.bean.BaseBean;
import com.zmm.tmsystem.bean.ChildcareStudentBean;
import com.zmm.tmsystem.bean.StudentBean;
import com.zmm.tmsystem.mvp.view.BaseView;

import java.util.List;

import io.reactivex.Observable;

/**
 * Description:
 * Author:zhangmengmeng
 * Date:2018/6/21
 * Time:下午5:49
 */

public interface ChildcareStudentContract {

    interface IChildcareStudentModel{

        Observable<BaseBean<ChildcareStudentBean>> addChildcareStudent(ChildcareStudentBean childcareStudentBean);

        Observable<BaseBean<String>> updateChildcareStudent(ChildcareStudentBean childcareStudentBean);

        Observable<BaseBean<String>> deleteChildcareStudent(String id);

        Observable<BaseBean<ChildcareStudentBean>> getChildcareStudentById(String id);

        Observable<BaseBean<List<ChildcareStudentBean>>> queryAllChildcareStudents(String id);

    }

    interface ChildcareStudentView extends BaseView{

        void addSuccess(ChildcareStudentBean childcareStudentBean);
        void updateSuccess();
        void deleteSuccess();


    }
}
