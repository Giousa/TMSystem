package com.zmm.tmsystem.mvp.presenter;

import com.zmm.tmsystem.bean.TermBean;
import com.zmm.tmsystem.common.Constant;
import com.zmm.tmsystem.common.utils.ACache;
import com.zmm.tmsystem.mvp.presenter.contract.TermContract;
import com.zmm.tmsystem.rx.RxHttpResponseCompat;
import com.zmm.tmsystem.rx.subscriber.ErrorHandlerSubscriber;
import com.zmm.tmsystem.ui.widget.TermInfoDialog;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.disposables.Disposable;

/**
 * Description:
 * Author:zhangmengmeng
 * Date:2018/6/17
 * Time:下午7:43
 */

public class TermPresenter extends BasePresenter<TermContract.ITermModel,TermContract.TermView> {


    @Inject
    public TermPresenter(TermContract.ITermModel model, TermContract.TermView view) {
        super(model, view);
    }

    /**
     * 查询所有托管周期
     */
    public void queryAllTerm() {
        ACache aCache = ACache.get(mContext);
        String tId = aCache.getAsString(Constant.TEACHER_ID);

        mModel.queryAllTerm(tId)
                .compose(RxHttpResponseCompat.<List<TermBean>>compatResult())
                .subscribe(new ErrorHandlerSubscriber<List<TermBean>>(mContext) {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(List<TermBean> list) {
                        mView.getAllTerms(list);
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    /**
     * 创建新的托管周期
     */
    public void createTerm() {

        System.out.println("开启dialog之前");
        final TermInfoDialog termInfoDialog = new TermInfoDialog(mContext,null,null,null,null,null);

        termInfoDialog.setOnClickListener(new TermInfoDialog.OnClickListener() {
            @Override
            public void onCancel() {
                System.out.println("---cancel---");
                termInfoDialog.dismiss();
                mView.dismissLoading();
            }

            @Override
            public void onConfirm(String title, int year, int month, String term) {
                termInfoDialog.dismiss();
                mView.dismissLoading();
                System.out.println("---confirm---");
                System.out.println("title = "+title);
                System.out.println("year = "+year);
                System.out.println("month = "+month);
                System.out.println("term = "+term);

                update2Server(title,year,month,term);
            }
        });

        termInfoDialog.show();
        mView.showLoading();

    }

    /**
     * 更新到服务器
     * @param title
     * @param year
     * @param month
     * @param term
     */
    private void update2Server(String title, int year, int month, String term) {

        ACache aCache = ACache.get(mContext);
        String tId = aCache.getAsString(Constant.TEACHER_ID);

        TermBean termBean = new TermBean();
        termBean.setTeacherId(tId);
        termBean.setTitle(title);
        termBean.setYear(year);
        termBean.setMonth(month);
        termBean.setTerm(term);

        mModel.createNewTerm(termBean)
                .compose(RxHttpResponseCompat.<String>compatResult())
                .subscribe(new ErrorHandlerSubscriber<String>(mContext) {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(String s) {
                        mView.updateSuccess();
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

}
