package com.zmm.tmsystem.ui.activity;

import com.zmm.tmsystem.R;
import com.zmm.tmsystem.bean.ChildcareStudentBean;
import com.zmm.tmsystem.common.Constant;
import com.zmm.tmsystem.dagger.component.AppComponent;
import com.zmm.tmsystem.dagger.component.DaggerChildcareStudentComponent;
import com.zmm.tmsystem.dagger.module.ChildcareStudentModule;
import com.zmm.tmsystem.mvp.presenter.ChildcareStudentPresenter;
import com.zmm.tmsystem.mvp.presenter.contract.ChildcareStudentContract;

import java.io.Serializable;
import java.util.List;

/**
 * Description:
 * Author:zhangmengmeng
 * Date:2018/7/3
 * Time:上午11:34
 */

public class ChildcareStudentInfoActivity extends BaseActivity<ChildcareStudentPresenter> implements ChildcareStudentContract.ChildcareStudentView {

    @Override
    protected int setLayout() {
        return R.layout.activity_student_childcare;
    }

    @Override
    protected void setupActivityComponent(AppComponent appComponent) {
        DaggerChildcareStudentComponent.builder()
                .appComponent(appComponent)
                .childcareStudentModule(new ChildcareStudentModule(this))
                .build()
                .inject(this);
    }

    @Override
    protected void init() {

        ChildcareStudentBean childcareStudentBean = (ChildcareStudentBean) getIntent().getSerializableExtra(Constant.STUDENT);

        System.out.println("childcareStudentBean = "+childcareStudentBean);
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void showError(String msg) {

    }

    @Override
    public void dismissLoading() {

    }

    @Override
    public void querySuccess(List<ChildcareStudentBean> childcareStudentBeans) {

    }

    @Override
    public void addSuccess(ChildcareStudentBean childcareStudentBean) {

    }

    @Override
    public void updateSuccess() {

    }

    @Override
    public void deleteSuccess() {

    }
}
