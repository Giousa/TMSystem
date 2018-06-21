package com.zmm.tmsystem.mvp.presenter;

import android.text.TextUtils;
import android.widget.LinearLayout;

import com.zmm.tmsystem.bean.StudentBean;
import com.zmm.tmsystem.common.Constant;
import com.zmm.tmsystem.mvp.presenter.contract.StudentContract;
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
 * Date:2018/6/20
 * Time:下午2:24
 */

public class StudentPresenter extends BasePresenter<StudentContract.IStudentModel,StudentContract.StudentView> {


    private String title = null;
    private String hint = null;
    private int type;
    private String name;
    private LinearLayout mRootView;
    private int mScreenWidth;
    private List<String> mList;



    @Inject
    public StudentPresenter(StudentContract.IStudentModel model, StudentContract.StudentView view) {
        super(model, view);
    }

    public void queryAllStudents(String id) {

        mModel.queryAllStudentsByTeacherId(id)
                .compose(RxHttpResponseCompat.<List<StudentBean>>compatResult())
                .subscribe(new ErrorHandlerSubscriber<List<StudentBean>>(mContext) {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(List<StudentBean> studentBeans) {
                        mView.querySuccess(studentBeans);
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public void addStudent(StudentBean studentBean) {

        mModel.addStudent(studentBean)
                .compose(RxHttpResponseCompat.<StudentBean>compatResult())
                .subscribe(new ErrorHandlerSubscriber<StudentBean>(mContext) {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(StudentBean studentBean) {
                        mView.addSuccess(studentBean);
                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }

    /**
     * 添加新学生 这里不做任何网络请求，仅仅只是为了填充学生信息，最后在点击事件中统一上传
     * @param type
     * @param name
     * @param rootView
     * @param screenWidth
     */
    public void addNewStudent(int type, String name, LinearLayout rootView, int screenWidth) {

        this.type = type;
        this.name = name;
        mRootView = rootView;
        mScreenWidth = screenWidth;
        if(mList != null){
            mList.clear();
            mList = null;
        }
        switch (type){

            case Constant.TYPE_STUDENT_ICON:
//                uloadIcon();
                break;
            case Constant.TYPE_STUDENT_NAME:
                title = "姓名";
                hint = "请输入您的姓名";
                inputString(false);
                break;
            case Constant.TYPE_STUDENT_GENDER:
                title = "性别";
                mList = new ArrayList<>();
                mList.add("女");
                mList.add("男");

                selectString();
                break;
            case Constant.TYPE_STUDENT_PHONE:
                title = "联系电话";
                hint = "请输入联系电话";
                inputString(true);
                break;
            case Constant.TYPE_STUDENT_ADDRESS:
                title = "地址";
                hint = "请输入您的地址";
                inputString(false);
                break;
            case Constant.TYPE_STUDENT_GUARDIAN1:
                title = "监护人";
                hint = "请输入监护人姓名";
                inputString(false);
                break;
            case Constant.TYPE_STUDENT_GUARDIANPHONE1:
                title = "监护人电话";
                hint = "请输入监护人电话";
                inputString(true);
                break;
            case Constant.TYPE_STUDENT_GUARDIAN2:
                title = "备用监护人";
                hint = "请输入监护人姓名";
                inputString(false);
                break;
            case Constant.TYPE_STUDENT_GUARDIANPHONE2:
                title = "监护人电话";
                hint = "请输入监护人电话";
                inputString(true);
                break;

        }
    }


    /**
     * 输入框修改
     */
    private void inputString(boolean isNumberType) {

        final SimpleInputDialog simpleInputDialog = new SimpleInputDialog(mContext, title, hint,name,isNumberType);

        simpleInputDialog.setOnClickListener(new SimpleInputDialog.OnClickListener() {

            @Override
            public void onCancel() {
                simpleInputDialog.dismiss();
                mView.dismissLoading();
            }

            @Override
            public void onConfirm(String content) {
                simpleInputDialog.dismiss();
                if(TextUtils.isEmpty(content)){
                    return;
                }
                mView.dismissLoading();
                mView.inputSuccess(type,content);

            }
        });

        simpleInputDialog.show();

        //这里的功能，是用来显示 makeWindowDark()  背景变暗
        mView.showLoading();
    }


    /**
     * 选择框修改
     */
    private void selectString() {


        SingleSelectView singleSelectView = new SingleSelectView(mContext,mRootView,mScreenWidth,title,mList);

        singleSelectView.setOnSelectClickListener(new SingleSelectView.OnSelectClickListener() {
            @Override
            public void onCancel() {

                mView.dismissLoading();
            }

            @Override
            public void onConfirm(String content) {
                mView.dismissLoading();
                mView.inputSuccess(type,content);
            }
        });

        mView.showLoading();
    }



}
