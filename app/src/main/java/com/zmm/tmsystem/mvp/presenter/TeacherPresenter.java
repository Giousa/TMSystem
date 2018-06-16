package com.zmm.tmsystem.mvp.presenter;

import android.text.TextUtils;
import android.widget.LinearLayout;

import com.zmm.tmsystem.bean.SchoolBean;
import com.zmm.tmsystem.bean.TeacherBean;
import com.zmm.tmsystem.common.Constant;
import com.zmm.tmsystem.common.utils.ACache;
import com.zmm.tmsystem.common.utils.TeacherCacheUtil;
import com.zmm.tmsystem.mvp.presenter.contract.TeacherContract;
import com.zmm.tmsystem.rx.RxBus;
import com.zmm.tmsystem.rx.RxHttpResponseCompat;
import com.zmm.tmsystem.rx.subscriber.ErrorHandlerSubscriber;
import com.zmm.tmsystem.ui.widget.SimpleInputDialog;
import com.zmm.tmsystem.ui.widget.SingleSelectView;

import java.util.ArrayList;
import java.util.List;

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
    private String hint = null;
    private int type;
    private LinearLayout mRootView;
    private int mScreenWidth;
    private String id;
    private List<String> mList;



    @Inject
    public TeacherPresenter(TeacherContract.ITeacherModel model, TeacherContract.TeacherView view) {
        super(model, view);
    }

    public void updateTeacherByType(int type,LinearLayout rootView,int screenWidth){

        ACache aCache = ACache.get(mContext);
        id = aCache.getAsString(Constant.TEACHER_ID);

        mRootView = rootView;
        mScreenWidth = screenWidth;
        this.type = type;

        if(mList != null){
            mList.clear();
            mList = null;
        }

        switch (type){

            case Constant.TYPE_ICON:
                uloadIcon();
                break;
            case Constant.TYPE_NAME:
                title = "姓名";
                hint = "请输入您的姓名";
                inputString();
                break;
            case Constant.TYPE_GENDER:
                title = "性别";
                mList = new ArrayList<>();
                mList.add("女");
                mList.add("男");

                selectString();
                break;
            case Constant.TYPE_PHONE:
                title = "联系电话";
                hint = "请输入联系电话";
                inputString();
                break;
            case Constant.TYPE_CHILDCARE_NAME:
                title = "托管机构";
                hint = "请输入机构名称";
                inputString();
                break;
            case Constant.TYPE_SCHOOL:
                title = "在职学校";
                queryAllSchools();
                break;
            case Constant.TYPE_GRADE:
                title = "授课年级";
                mList = new ArrayList<>();
                mList.add("一年级");
                mList.add("二年级");
                mList.add("三年级");
                mList.add("四年级");
                mList.add("五年级");
                mList.add("六年级");
                mList.add("七年级");
                mList.add("八年级");
                mList.add("九年级");
                mList.add("高一");
                mList.add("高二");
                mList.add("高三");
                selectString();
                break;
            case Constant.TYPE_COURSE:
                title = "授课学科";
                selectString();
                break;
            case Constant.TYPE_ADDRESS:
                title = "地址";
                hint = "请输入您的地址";
                inputString();
                break;
            case Constant.TYPE_QR_CODE:
                showQrCode();
                break;

        }
    }

    private void queryAllSchools() {

        mModel.querySchools()
                .compose(RxHttpResponseCompat.<List<SchoolBean>>compatResult())
                .subscribe(new ErrorHandlerSubscriber<List<SchoolBean>>(mContext) {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(List<SchoolBean> schoolBeans) {
                        if(schoolBeans != null && schoolBeans.size() > 0){
                            mList = new ArrayList<>();
                            for (SchoolBean school:schoolBeans) {
                                mList.add(school.getName());
                            }

                            selectString();
                        }
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }


    private void uloadIcon() {

    }

    private void showQrCode() {

    }


    /**
     * 输入框修改
     */
    private void inputString() {

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
     */
    private void selectString() {


        SingleSelectView singleSelectView = new SingleSelectView(mContext,mRootView,mScreenWidth,title,mList);

        singleSelectView.setOnSelectClickListener(new SingleSelectView.OnSelectClickListener() {
            @Override
            public void onCancel() {
                System.out.println("取消");
            }

            @Override
            public void onConfirm(String content) {
                System.out.println("content = "+content);
            }
        });
    }


    /**
     * 更新数据到服务器
     * @param type
     * @param content
     */
    private void update2Server(int type, String content) {

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
