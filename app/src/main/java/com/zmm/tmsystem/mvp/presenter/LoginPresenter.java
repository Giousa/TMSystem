package com.zmm.tmsystem.mvp.presenter;

import com.zmm.tmsystem.bean.TeacherBean;
import com.zmm.tmsystem.common.Constant;
import com.zmm.tmsystem.common.utils.ACache;
import com.zmm.tmsystem.common.utils.VerificationUtils;
import com.zmm.tmsystem.mvp.presenter.contract.LoginContract;
import com.zmm.tmsystem.rx.RxHttpResponseCompat;
import com.zmm.tmsystem.rx.subscriber.ErrorHandlerSubscriber;

import javax.inject.Inject;

import io.reactivex.disposables.Disposable;

/**
 * Description:
 * Author:zhangmengmeng
 * Date:2018/6/13
 * Time:下午10:50
 */

public class LoginPresenter extends BasePresenter<LoginContract.LoginModel,LoginContract.LoginView> {


    @Inject
    public LoginPresenter(LoginContract.LoginModel model, LoginContract.LoginView view) {
        super(model, view);
    }


    public void login(String phone,String password){

        mModel.login(phone,password)
                .compose(RxHttpResponseCompat.<TeacherBean>compatResult())
                .subscribe(new ErrorHandlerSubscriber<TeacherBean>(mContext) {
                    @Override
                    public void onSubscribe(Disposable d) {

                        mView.showLoading();
                    }

                    @Override
                    public void onComplete() {

                        mView.dismissLoading();
                    }

                    @Override
                    public void onError(Throwable e) {
                        super.onError(e);
                        mView.dismissLoading();
                        mView.loginFailure();
                    }

                    @Override
                    public void onNext(TeacherBean teacherBean) {
                        mView.dismissLoading();
                        mView.loginSuccess(teacherBean);
                        saveUser(teacherBean);

                        //登录成功，发送消息
//                        RxBus.get().post(loginBean.getUser());
//                        RxBus.getDefault().post(loginBean.getUser());
                    }
                });
    }


    private void saveUser(TeacherBean teacherBean){

        ACache aCache = ACache.get(mContext);

        aCache.put(Constant.TEACHER,teacherBean);

        System.out.println("teacher = "+teacherBean.toString());
    }


}
