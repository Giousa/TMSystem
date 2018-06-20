package com.zmm.tmsystem.ui.activity;

import android.widget.LinearLayout;

import com.zmm.tmsystem.R;
import com.zmm.tmsystem.bean.StudentBean;
import com.zmm.tmsystem.common.Constant;
import com.zmm.tmsystem.dagger.component.AppComponent;
import com.zmm.tmsystem.dagger.component.DaggerStudentComponent;
import com.zmm.tmsystem.dagger.module.StudentModule;
import com.zmm.tmsystem.mvp.presenter.StudentPresenter;
import com.zmm.tmsystem.mvp.presenter.contract.StudentContract;
import com.zmm.tmsystem.ui.widget.CustomInfoItemView;
import com.zmm.tmsystem.ui.widget.TitleBar;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Description:
 * Author:zhangmengmeng
 * Date:2018/6/20
 * Time:下午3:57
 */

public class StudentInfoActivity extends BaseActivity<StudentPresenter> implements StudentContract.StudentView, CustomInfoItemView.OnItemClickListener {


    @BindView(R.id.title_bar)
    TitleBar mTitleBar;
    @BindView(R.id.custom_item_icon)
    CustomInfoItemView mCustomItemIcon;
    @BindView(R.id.custom_item_name)
    CustomInfoItemView mCustomItemName;
    @BindView(R.id.custom_item_gender)
    CustomInfoItemView mCustomItemGender;
    @BindView(R.id.custom_item_phone)
    CustomInfoItemView mCustomItemPhone;
    @BindView(R.id.custom_item_address)
    CustomInfoItemView mCustomItemAddress;
    @BindView(R.id.custom_item_guardian1)
    CustomInfoItemView mCustomItemGuardian1;
    @BindView(R.id.custom_item_guardian_phone1)
    CustomInfoItemView mCustomItemGuardianPhone1;
    @BindView(R.id.custom_item_guardian2)
    CustomInfoItemView mCustomItemGuardian2;
    @BindView(R.id.custom_item_guardian_phone2)
    CustomInfoItemView mCustomItemGuardianPhone2;
    @BindView(R.id.root_view)
    LinearLayout mRootView;


    @Override
    protected int setLayout() {
        return R.layout.activity_student_info;
    }

    @Override
    protected void setupActivityComponent(AppComponent appComponent) {
        DaggerStudentComponent.builder()
                .appComponent(appComponent)
                .studentModule(new StudentModule(this))
                .build()
                .inject(this);
    }

    @Override
    protected void init() {


        mCustomItemIcon.setOnItemClickListener(this, Constant.TYPE_STUDENT_ICON);
        mCustomItemName.setOnItemClickListener(this, Constant.TYPE_STUDENT_NAME);
        mCustomItemGender.setOnItemClickListener(this, Constant.TYPE_STUDENT_GENDER);
        mCustomItemPhone.setOnItemClickListener(this, Constant.TYPE_STUDENT_PHONE);
        mCustomItemAddress.setOnItemClickListener(this, Constant.TYPE_STUDENT_ADDRESS);
        mCustomItemGuardian1.setOnItemClickListener(this, Constant.TYPE_STUDENT_GUARDIAN1);
        mCustomItemGuardianPhone1.setOnItemClickListener(this, Constant.TYPE_STUDENT_GUARDIANPHONE1);
        mCustomItemGuardian2.setOnItemClickListener(this, Constant.TYPE_STUDENT_GUARDIAN2);
        mCustomItemGuardianPhone2.setOnItemClickListener(this, Constant.TYPE_STUDENT_GUARDIANPHONE2);

    }


    @Override
    public void itemClick(int type, String name) {
        mPresenter.addNewStudent(type,name,mRootView,mScreenWidth);
    }


    @Override
    public void showLoading() {
        makeWindowDark();
    }

    @Override
    public void showError(String msg) {

    }

    @Override
    public void dismissLoading() {
        makeWindowLight();
    }

    @Override
    public void inputSuccess(int type, String content) {

        switch (type){
            case Constant.TYPE_STUDENT_ICON:

                break;
            case Constant.TYPE_STUDENT_NAME:
                mCustomItemName.setContent(content);
                break;
            case Constant.TYPE_STUDENT_GENDER:
                mCustomItemGender.setContent(content);
                break;
            case Constant.TYPE_STUDENT_PHONE:
                mCustomItemPhone.setContent(content);
                break;
            case Constant.TYPE_STUDENT_ADDRESS:
                mCustomItemAddress.setContent(content);
                break;
            case Constant.TYPE_STUDENT_GUARDIAN1:
                mCustomItemGuardian1.setContent(content);
                break;
            case Constant.TYPE_STUDENT_GUARDIANPHONE1:
                mCustomItemGuardianPhone1.setContent(content);
                break;
            case Constant.TYPE_STUDENT_GUARDIAN2:
                mCustomItemGuardian2.setContent(content);
                break;
            case Constant.TYPE_STUDENT_GUARDIANPHONE2:
                mCustomItemGuardianPhone2.setContent(content);
                break;

        }

    }

    @Override
    public void addSuccess(StudentBean studentBean) {

    }

    @Override
    public void updateSuccess() {

    }

    @Override
    public void deleteStudent() {

    }

    @Override
    public void querySuccess(List<StudentBean> studentBeans) {

    }

    @OnClick(R.id.btn_select_confirm)
    public void onViewClicked() {
    }

}
