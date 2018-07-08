package com.zmm.tmsystem.mvp.presenter;

import android.text.TextUtils;
import android.widget.LinearLayout;

import com.zmm.tmsystem.bean.ChildcareStudentBean;
import com.zmm.tmsystem.bean.SchoolBean;
import com.zmm.tmsystem.bean.StudentBean;
import com.zmm.tmsystem.bean.TeacherBean;
import com.zmm.tmsystem.common.Constant;
import com.zmm.tmsystem.common.utils.TeacherCacheUtil;
import com.zmm.tmsystem.mvp.presenter.contract.ChildcareStudentContract;
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
 * Date:2018/7/2
 * Time:上午11:53
 */

public class ChildcareStudentPresenter extends BasePresenter<ChildcareStudentContract.IChildcareStudentModel,ChildcareStudentContract.ChildcareStudentView> {


    private String title = null;
    private String hint = null;
    private int type;
    private String name;
    private LinearLayout mRootView;
    private int mScreenWidth;
    private List<String> mList;
    private ChildcareStudentBean mChildcareStudentBean;


    @Inject
    public ChildcareStudentPresenter(ChildcareStudentContract.IChildcareStudentModel model, ChildcareStudentContract.ChildcareStudentView view) {
        super(model, view);
    }

    /**
     * 查询所有托管学生
     * @param id
     */
    public void queryAllChildcareStudents(String id) {

        mModel.queryAllChildcareStudents(id)
                .compose(RxHttpResponseCompat.<List<ChildcareStudentBean>>compatResult())
                .subscribe(new ErrorHandlerSubscriber<List<ChildcareStudentBean>>(mContext) {

                    @Override
                    public void onNext(List<ChildcareStudentBean> childcareStudentBeans) {
                        mView.querySuccess(childcareStudentBeans);

                    }
                });

    }

    /**
     * 删除托管学生
     * @param id
     */
    public void deleteChildcareStudent(String id) {
        mModel.deleteChildcareStudent(id)
                .compose(RxHttpResponseCompat.<String>compatResult())
                .subscribe(new ErrorHandlerSubscriber<String>(mContext) {
                    @Override
                    public void onNext(String s) {
                        mView.deleteSuccess();
                    }
                });
    }

    /**
     * 更新数据
     * @param type
     * @param name
     * @param rootView
     * @param screenWidth
     * @param childcareStudentBean
     */
    public void updateChildcareStudentData(int type, String name, LinearLayout rootView, int screenWidth, ChildcareStudentBean childcareStudentBean) {

        this.type = type;
        this.name = name;
        mRootView = rootView;
        mScreenWidth = screenWidth;
        mChildcareStudentBean = childcareStudentBean;
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
            case Constant.TYPE_STUDENT_SCHOOL:
                title = "学校";
                mList = new ArrayList<>();

                queryAllSchools();
                break;
            case Constant.TYPE_STUDENT_GRADE:
                title = "年级";
                hint = "请输入您的年级";
                inputString(true);
                break;
            case Constant.TYPE_STUDENT_TEACHER:
                title = "班主任";
                hint = "请输入班主任名称";
                inputString(false);
                break;
            case Constant.TYPE_STUDENT_TEACHER_PHONE:
                title = "班主任电话";
                hint = "请输入班主任姓名";
                inputString(false);
                break;
            case Constant.TYPE_STUDENT_GUARDIANPHONE1:
                title = "监护人电话";
                hint = "请输入监护人电话";
                inputString(true);
                break;

        }

    }

    /**
     * 查询学校
     */
    private void queryAllSchools() {
        mModel.querySchools()
                .compose(RxHttpResponseCompat.<List<SchoolBean>>compatResult())
                .subscribe(new ErrorHandlerSubscriber<List<SchoolBean>>(mContext) {

                    @Override
                    public void onNext(List<SchoolBean> schoolBeans) {
                        if(schoolBeans != null && schoolBeans.size() > 0){

                            for (SchoolBean school:schoolBeans) {
                                mList.add(school.getName());
                            }

                            selectString();
                        }
                    }

                });
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
                update2Server(type,content);

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
                update2Server(type,content);
            }
        });

        mView.showLoading();
    }

    /**
     * 更新数据到服务器
     * @param type
     * @param content
     */
    private void update2Server(int type, String content) {

        int flag = 0;

        switch (type){

            case Constant.TYPE_STUDENT_ICON:
                mChildcareStudentBean.getStudent().setIcon(content);
                flag = 0;
                break;
            case Constant.TYPE_STUDENT_NAME:
                mChildcareStudentBean.getStudent().setName(content);
                flag = 0;
                break;
            case Constant.TYPE_STUDENT_SCHOOL:
                mChildcareStudentBean.setSchool(content);
                flag = 1;
                break;
            case Constant.TYPE_STUDENT_GRADE:
                mChildcareStudentBean.setGrade(content);
                flag = 1;
                break;
            case Constant.TYPE_STUDENT_TEACHER:
                mChildcareStudentBean.setTeacher(content);
                flag = 1;
                break;
            case Constant.TYPE_STUDENT_TEACHER_PHONE:
                mChildcareStudentBean.setTeacherPhone(content);
                flag = 1;
                break;
            case Constant.TYPE_STUDENT_GUARDIANPHONE1:
                mChildcareStudentBean.getStudent().setGuardian1Phone(content);
                flag = 0;
                break;

        }

        mModel.updateChildcareStudent(flag,mChildcareStudentBean);
    }

    /**
     * 根据id，获取托管学生信息
     * @param id
     */
    public void findChildcareStudentById(String id) {
        mModel.findChildcareStudentById(id)
                .compose(RxHttpResponseCompat.<ChildcareStudentBean>compatResult())
                .subscribe(new ErrorHandlerSubscriber<ChildcareStudentBean>(mContext) {
                    @Override
                    public void onNext(ChildcareStudentBean childcareStudentBean) {
                        mView.updateSuccess(childcareStudentBean);
                    }
                });
    }
}
