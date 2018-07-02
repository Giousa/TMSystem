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
 * Date:2018/6/20
 * Time:下午1:21
 */

public interface StudentContract {

    interface IStudentModel{

        Observable<BaseBean<StudentBean>> addStudent(StudentBean studentBean);

        Observable<BaseBean<String>> updateStudent(StudentBean studentBean);

        Observable<BaseBean<String>> deleteStudent(String id);

        Observable<BaseBean<List<StudentBean>>> queryAllStudentsByTeacherId(String id);

        //添加托管学生
        Observable<BaseBean<String>> addChildcareStudents(String termId,List<String> idList);



    }

    interface StudentView extends BaseView{

        void inputSuccess(int type,String content);

        void addSuccess(StudentBean studentBean);

        void updateSuccess();

        void deleteStudent();

        void querySuccess(List<StudentBean> studentBeans);
    }
}
