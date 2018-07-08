package com.zmm.tmsystem.mvp.presenter.contract;

import com.zmm.tmsystem.bean.BaseBean;
import com.zmm.tmsystem.bean.ChildcareStudentBean;
import com.zmm.tmsystem.bean.SchoolBean;
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

        Observable<BaseBean<List<ChildcareStudentBean>>> queryAllChildcareStudents(String id);

        Observable<BaseBean<String>> updateChildcareStudent(int flag,ChildcareStudentBean childcareStudentBean);

        Observable<BaseBean<String>> deleteChildcareStudent(String id);

        Observable<BaseBean<ChildcareStudentBean>> findChildcareStudentById(String id);

        Observable<BaseBean<List<SchoolBean>>> querySchools();
    }

    interface ChildcareStudentView extends BaseView{

        void querySuccess(List<ChildcareStudentBean> childcareStudentBeans);
        void addSuccess(ChildcareStudentBean childcareStudentBean);
        void updateSuccess(ChildcareStudentBean childcareStudentBean);
        void deleteSuccess();


    }
}
