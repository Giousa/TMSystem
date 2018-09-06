package com.zmm.tmsystem.mvp.presenter;

import com.zmm.tmsystem.bean.ScoreBean;
import com.zmm.tmsystem.mvp.presenter.contract.ScoreContract;
import com.zmm.tmsystem.rx.RxHttpResponseCompat;
import com.zmm.tmsystem.rx.subscriber.ErrorHandlerSubscriber;

import java.util.List;

import javax.inject.Inject;

/**
 * Description:
 * Author:zhangmengmeng
 * Date:2018/9/6
 * Email:65489469@qq.com
 */
public class ScorePresenter extends BasePresenter<ScoreContract.IScoreModel,ScoreContract.ScoreView>{


    @Inject
    public ScorePresenter(ScoreContract.IScoreModel model, ScoreContract.ScoreView view) {
        super(model, view);
    }


    /**
     * 添加成绩单
     * @param scoreBean
     */
    public void addScore(ScoreBean scoreBean) {

        mModel.addScore(scoreBean)
                .compose(RxHttpResponseCompat.<String>compatResult())
                .subscribe(new ErrorHandlerSubscriber<String>(mContext) {
                    @Override
                    public void onNext(String s) {
                        mView.requestSuccess(s);
                    }
                });

    }

    /**
     * 查询所有成绩
     * @param childcareStudentId
     */
    public void queryAllScores(String childcareStudentId) {
        mModel.queryAllScores(childcareStudentId)
                .compose(RxHttpResponseCompat.<List<ScoreBean>>compatResult())
                .subscribe(new ErrorHandlerSubscriber<List<ScoreBean>>(mContext) {
                    @Override
                    public void onNext(List<ScoreBean> scoreBeans) {
                        mView.queryAllScores(scoreBeans);
                    }
                });
    }
}
