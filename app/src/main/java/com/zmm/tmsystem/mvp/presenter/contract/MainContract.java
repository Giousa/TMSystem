package com.zmm.tmsystem.mvp.presenter.contract;

import com.zmm.tmsystem.bean.BaseBean;
import com.zmm.tmsystem.bean.TeacherBean;
import com.zmm.tmsystem.mvp.view.BaseView;

import java.io.File;

import io.reactivex.Observable;

/**
 * Description:
 * Author:zhangmengmeng
 * Date:2018/6/14
 * Time:下午11:13
 */

public interface MainContract {


    interface IMainModel{

        Observable<BaseBean<TeacherBean>> getTeacherById(String id);

        Observable<BaseBean<TeacherBean>> updateTeacherInfo(TeacherBean teacherBean);

        Observable<BaseBean<TeacherBean>> uploadTeacherPic(String id, File uploadFile);

        Observable<BaseBean<String>> signInfo(String tId);

        Observable<BaseBean<String>> sign(String tId);


    }

    interface MainView extends BaseView{

        void checkParamEmpty();

        void updateSuccess();
        void uploadSuccess();

        void signSuccess();
        void signExist();
    }
}
