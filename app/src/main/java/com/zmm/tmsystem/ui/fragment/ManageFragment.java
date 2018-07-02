package com.zmm.tmsystem.ui.fragment;

import com.zmm.tmsystem.R;
import com.zmm.tmsystem.bean.StudentBean;
import com.zmm.tmsystem.bean.TermBean;
import com.zmm.tmsystem.common.Constant;
import com.zmm.tmsystem.common.utils.ACache;
import com.zmm.tmsystem.dagger.component.AppComponent;
import com.zmm.tmsystem.dagger.component.DaggerStudentComponent;
import com.zmm.tmsystem.dagger.module.StudentModule;
import com.zmm.tmsystem.mvp.presenter.StudentPresenter;
import com.zmm.tmsystem.mvp.presenter.contract.StudentContract;

import java.util.List;

/**
 * Description:
 * Author:zhangmengmeng
 * Date:2018/6/15
 * Time:上午10:02
 */

public class ManageFragment extends ProgressFragment<StudentPresenter> implements StudentContract.StudentView {

    @Override
    protected int setLayout() {
        return R.layout.manager;
    }

    @Override
    protected void setupAcitivtyComponent(AppComponent appComponent) {
        DaggerStudentComponent.builder()
                .appComponent(appComponent)
                .studentModule(new StudentModule(this))
                .build()
                .inject(this);
    }

    @Override
    protected void init() {

        ACache aCache = ACache.get(getActivity());
        TermBean termBean = (TermBean) aCache.getAsObject(Constant.TERM);


    }


    @Override
    public void inputSuccess(int type, String content) {

    }


    @Override
    public void addSuccess(StudentBean studentBean) {

    }

    @Override
    public void updateSuccess() {

    }

    @Override
    public void deleteStudent() {

    }

    @Override
    public void querySuccess(List<StudentBean> studentBeans) {

    }
}
