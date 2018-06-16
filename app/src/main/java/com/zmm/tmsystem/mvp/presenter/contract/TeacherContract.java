package com.zmm.tmsystem.mvp.presenter.contract;

import com.zmm.tmsystem.bean.BaseBean;
import com.zmm.tmsystem.bean.TeacherBean;
import com.zmm.tmsystem.mvp.view.BaseView;

import java.io.File;

import io.reactivex.Observable;

/**
 * Description:
 * Author:zhangmengmeng
 * Date:2018/6/15
 * Time:下午10:30
 */

public interface TeacherContract {

    public interface ITeacherModel{

        Observable<BaseBean<TeacherBean>> updateTeacherInfo(TeacherBean teacherBean);

        Observable<BaseBean<TeacherBean>> uploadTeacherPic(String id, File uploadFile);
    }

    public interface TeacherView extends BaseView {


        void updateSuccess(String msg);

    }
}
