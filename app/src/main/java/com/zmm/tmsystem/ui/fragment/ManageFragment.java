package com.zmm.tmsystem.ui.fragment;

import android.text.TextUtils;

import com.zmm.tmsystem.R;
import com.zmm.tmsystem.common.Constant;
import com.zmm.tmsystem.common.utils.ToastUtils;
import com.zmm.tmsystem.dagger.component.AppComponent;
import com.zmm.tmsystem.rx.RxBus;

import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;

/**
 * Description:
 * Author:zhangmengmeng
 * Date:2018/6/15
 * Time:上午10:02
 */

public class ManageFragment extends ProgressFragment {

    @Override
    protected int setLayout() {
        return R.layout.manager;
    }

    @Override
    protected void setupAcitivtyComponent(AppComponent appComponent) {

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
}
