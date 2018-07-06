package com.zmm.tmsystem.mvp.presenter;

import android.widget.LinearLayout;

import com.zmm.tmsystem.bean.ChildcareStudentBean;
import com.zmm.tmsystem.bean.StudentBean;
import com.zmm.tmsystem.mvp.presenter.contract.ChildcareStudentContract;
import com.zmm.tmsystem.rx.RxHttpResponseCompat;
import com.zmm.tmsystem.rx.subscriber.ErrorHandlerSubscriber;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.disposables.Disposable;

/**
 * Description:
 * Author:zhangmengmeng
 * Date:2018/7/2
 * Time:上午11:53
 */

public class ChildcareStudentPresenter extends BasePresenter<ChildcareStudentContract.IChildcareStudentModel,ChildcareStudentContract.ChildcareStudentView> {


    @Inject
    public ChildcareStudentPresenter(ChildcareStudentContract.IChildcareStudentModel model, ChildcareStudentContract.ChildcareStudentView view) {
        super(model, view);
    }

    /**
     * 查询所有托管学生
     * @param id
     */
    public void queryAllChildcareStudents(String id) {

        mModel.queryAllChildcareStudents(id)
                .compose(RxHttpResponseCompat.<List<ChildcareStudentBean>>compatResult())
                .subscribe(new ErrorHandlerSubscriber<List<ChildcareStudentBean>>(mContext) {

                    @Override
                    public void onNext(List<ChildcareStudentBean> childcareStudentBeans) {
                        mView.querySuccess(childcareStudentBeans);

                    }
                });

    }

    /**
     * 删除托管学生
     * @param id
     */
    public void deleteChildcareStudent(String id) {
        mModel.deleteChildcareStudent(id)
                .compose(RxHttpResponseCompat.<String>compatResult())
                .subscribe(new ErrorHandlerSubscriber<String>(mContext) {
                    @Override
                    public void onNext(String s) {
                        mView.deleteSuccess();
                    }
                });
    }

    /**
     * 更新数据
     * @param type
     * @param name
     * @param rootView
     * @param screenWidth
     */
    public void updateChildcareStudent(int type, String name, LinearLayout rootView, int screenWidth) {

    }

    /**
     * 根据id，获取托管学生信息
     * @param id
     */
    public void findChildcareStudentById(String id) {
        mModel.findChildcareStudentById(id)
                .compose(RxHttpResponseCompat.<ChildcareStudentBean>compatResult())
                .subscribe(new ErrorHandlerSubscriber<ChildcareStudentBean>(mContext) {
                    @Override
                    public void onNext(ChildcareStudentBean childcareStudentBean) {
                        mView.updateSuccess(childcareStudentBean);
                    }
                });
    }
}
