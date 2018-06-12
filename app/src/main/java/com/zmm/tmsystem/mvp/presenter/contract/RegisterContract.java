package com.zmm.tmsystem.mvp.presenter.contract;

import com.zmm.tmsystem.bean.BaseBean;
import com.zmm.tmsystem.bean.UserBean;
import com.zmm.tmsystem.mvp.view.BaseView;

import io.reactivex.Observable;

/**
 * Description:
 * Author:zhangmengmeng
 * Date:2018/6/12
 * Time:下午4:08
 */

public interface RegisterContract {

    interface RegisterModel{

        Observable<BaseBean<UserBean>> register(String phone, String password, String verifyCode);

        Observable<BaseBean<UserBean>> oneKeyRegister(String phone, String verifyCode);

    }

    interface RegisterView extends BaseView{

    }
}
