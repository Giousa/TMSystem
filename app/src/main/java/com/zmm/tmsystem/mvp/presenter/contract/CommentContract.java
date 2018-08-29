package com.zmm.tmsystem.mvp.presenter.contract;

import com.zmm.tmsystem.bean.BaseBean;
import com.zmm.tmsystem.bean.ChildcareStudentBean;
import com.zmm.tmsystem.mvp.view.BaseView;

import java.util.List;

import io.reactivex.Observable;

/**
 * Description:
 * Author:zhangmengmeng
 * Date:2018/8/10
 * Email:65489469@qq.com
 */
public interface CommentContract {


    interface ICommentModel{

        /**
         * 获取当天学生评价情况
         * @param id
         * @return
         */
        Observable<BaseBean<List<ChildcareStudentBean>>> queryToday(String id);


        /**
         * 评价学生
         * @param id
         * @param level
         * @param content
         * @return
         */
        Observable<BaseBean<String>> commentChildcareStudent(String id,int level,String content);


    }

    interface CommentView extends BaseView{


        void queryTodaySuccess(List<ChildcareStudentBean> childcareStudentBeans);

        void commentSuccess(String msg);

        void commentFailure();

    }
}
