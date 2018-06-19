package com.zmm.tmsystem.ui.fragment;

import android.text.TextUtils;

import com.zmm.tmsystem.R;
import com.zmm.tmsystem.bean.TermBean;
import com.zmm.tmsystem.common.utils.ToastUtils;
import com.zmm.tmsystem.dagger.component.AppComponent;
import com.zmm.tmsystem.dagger.component.DaggerTermComponent;
import com.zmm.tmsystem.dagger.module.TermModule;
import com.zmm.tmsystem.mvp.presenter.TermPresenter;
import com.zmm.tmsystem.mvp.presenter.contract.TermContract;
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

public class ManageFragment extends ProgressFragment<TermPresenter> implements TermContract.TermView {

    @Override
    protected int setLayout() {
        return R.layout.manager;
    }

    @Override
    protected void setupAcitivtyComponent(AppComponent appComponent) {
        DaggerTermComponent.builder()
                .appComponent(appComponent)
                .termModule(new TermModule(this))
                .build()
                .inject(this);
    }

    @Override
    protected void init() {

        operateBus();
    }

    @Override
    public void updateSuccess(TermBean termBean) {

    }

    @Override
    public void getAllTerms(List<TermBean> list) {

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
                        if(!TextUtils.isEmpty(s) && s.equals("addTermStudent")){
                            ToastUtils.SimpleToast(mApplication,"addTermStudent");
                        }
                    }
                });
    }
}
