package com.zmm.tmsystem.mvp.presenter;

import com.zmm.tmsystem.bean.TeacherBean;
import com.zmm.tmsystem.common.Constant;
import com.zmm.tmsystem.common.utils.ACache;
import com.zmm.tmsystem.mvp.presenter.contract.HomeContract;
import com.zmm.tmsystem.rx.RxHttpResponseCompat;
import com.zmm.tmsystem.rx.subscriber.ProgressSubcriber;

import javax.inject.Inject;

/**
 * Description:
 * Author:zhangmengmeng
 * Date:2018/6/14
 * Time:下午11:51
 */

public class HomePresenter extends BasePresenter<HomeContract.IHomeModel,HomeContract.HomeView> {


    @Inject
    public HomePresenter(HomeContract.IHomeModel model, HomeContract.HomeView view) {
        super(model, view);
    }


    public void getTeacherById(){

        ACache aCache = ACache.get(mContext);
        String tId = aCache.getAsString(Constant.TEACHER_ID);

        mModel.getTeacherById(tId)
                .compose(RxHttpResponseCompat.<TeacherBean>compatResult())
                .subscribe(new ProgressSubcriber<TeacherBean>(mContext,mView) {
                    @Override
                    public void onNext(TeacherBean teacherBean) {
                        mView.showTeacherInfo(teacherBean);
                    }

                });


    }

}
