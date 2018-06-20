package com.zmm.tmsystem.ui.fragment;

import android.text.TextUtils;

import com.zmm.tmsystem.R;
import com.zmm.tmsystem.bean.StudentBean;
import com.zmm.tmsystem.common.Constant;
import com.zmm.tmsystem.common.utils.ToastUtils;
import com.zmm.tmsystem.dagger.component.AppComponent;
import com.zmm.tmsystem.dagger.component.DaggerStudentComponent;
import com.zmm.tmsystem.dagger.module.StudentModule;
import com.zmm.tmsystem.mvp.presenter.StudentPresenter;
import com.zmm.tmsystem.mvp.presenter.contract.StudentContract;
import com.zmm.tmsystem.rx.RxBus;

import java.util.List;

import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;

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

        operateBus();

        
    }


    /**
     * RxBus 这里响应MainActivity中ToolBar  Add按钮点击事件
     */
    private void operateBus() {
        RxBus.getDefault().toObservable()
                .map(new Function<Object, String>() {
                    @Override
                    public String apply(Object o) throws Exception {
                        return (String) o;
                    }
                })
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Exception {
                        if(!TextUtils.isEmpty(s) && s.equals(Constant.ADD_TERM_STUDENT)){
                            ToastUtils.SimpleToast(mApplication,"addTermStudent");

                        }
                    }
                });
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
