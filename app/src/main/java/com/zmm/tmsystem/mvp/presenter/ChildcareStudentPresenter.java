package com.zmm.tmsystem.mvp.presenter;

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
}
