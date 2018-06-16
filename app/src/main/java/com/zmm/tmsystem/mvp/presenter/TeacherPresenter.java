package com.zmm.tmsystem.mvp.presenter;

import android.text.TextUtils;

import com.zmm.tmsystem.bean.TeacherBean;
import com.zmm.tmsystem.common.Constant;
import com.zmm.tmsystem.common.utils.ACache;
import com.zmm.tmsystem.common.utils.TeacherCacheUtil;
import com.zmm.tmsystem.mvp.presenter.contract.TeacherContract;
import com.zmm.tmsystem.rx.RxBus;
import com.zmm.tmsystem.rx.RxHttpResponseCompat;
import com.zmm.tmsystem.rx.subscriber.ErrorHandlerSubscriber;
import com.zmm.tmsystem.ui.widget.SimpleInputDialog;

import javax.inject.Inject;

import io.reactivex.disposables.Disposable;

/**
 * Description:
 * Author:zhangmengmeng
 * Date:2018/6/15
 * Time:下午10:42
 */

public class TeacherPresenter extends BasePresenter<TeacherContract.ITeacherModel,TeacherContract.TeacherView> {


    private String title = null;


    @Inject
    public TeacherPresenter(TeacherContract.ITeacherModel model, TeacherContract.TeacherView view) {
        super(model, view);
    }

    public void updateTeacherByType(int type){

        switch (type){

            case Constant.TYPE_ICON:
                uloadIcon();
                break;
            case Constant.TYPE_NAME:
                inputString(Constant.TYPE_NAME);
                break;
            case Constant.TYPE_GENDER:
                selectString(Constant.TYPE_GENDER);
                break;
            case Constant.TYPE_PHONE:
                inputString(Constant.TYPE_PHONE);
                break;
            case Constant.TYPE_CHILDCARE_NAME:
                inputString(Constant.TYPE_CHILDCARE_NAME);
                break;
            case Constant.TYPE_SCHOOL:
                selectString(Constant.TYPE_SCHOOL);
                break;
            case Constant.TYPE_GRADE:
                selectString(Constant.TYPE_GRADE);
                break;
            case Constant.TYPE_COURSE:
                selectString(Constant.TYPE_COURSE);
                break;
            case Constant.TYPE_ADDRESS:
                inputString(Constant.TYPE_ADDRESS);
                break;
            case Constant.TYPE_QR_CODE:
                showQrCode();
                break;

        }
    }




    private void uloadIcon() {

    }

    private void showQrCode() {

    }


    /**
     * 输入框修改
     * @param type
     */
    private void inputString(final int type) {

        String hint = null;

        switch (type){

            case Constant.TYPE_NAME:
                title = "姓名";
                hint = "请输入您的姓名";
                break;

            case Constant.TYPE_PHONE:
                title = "联系电话";
                hint = "请输入联系电话";
                break;

            case Constant.TYPE_CHILDCARE_NAME:
                title = "托管机构";
                hint = "请输入机构名称";
                break;

            case Constant.TYPE_ADDRESS:
                title = "地址";
                hint = "请输入您的地址";
                break;


        }

        final SimpleInputDialog simpleInputDialog = new SimpleInputDialog(mContext, title, hint);

        simpleInputDialog.setOnClickListener(new SimpleInputDialog.OnClickListener() {
            @Override
            public void onCancel() {
                simpleInputDialog.dismiss();
                System.out.println("---cancel---");

            }

            @Override
            public void onConfirm(String content) {
                simpleInputDialog.dismiss();
                System.out.println("---confirm---" + content);
                if(TextUtils.isEmpty(content)){
                    return;
                }
                update2Server(type,content);

            }
        });

        simpleInputDialog.show();
    }


    /**
     * 选择框修改
     * @param type
     */
    private void selectString(int type) {

    }


    /**
     * 更新数据到服务器
     * @param type
     * @param content
     */
    private void update2Server(int type, String content) {

        ACache aCache = ACache.get(mContext);
        String id = aCache.getAsString(Constant.TEACHER_ID);

        mModel.updateTeacherByType(id,type,content)
                .compose(RxHttpResponseCompat.<TeacherBean>compatResult())
                .subscribe(new ErrorHandlerSubscriber<TeacherBean>(mContext) {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(TeacherBean teacherBean) {
                        mView.updateSuccess(title,teacherBean);
                        TeacherCacheUtil.save(mContext,teacherBean);
                        RxBus.getDefault().post(teacherBean);
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }


}
