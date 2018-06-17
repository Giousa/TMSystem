package com.zmm.tmsystem.ui.activity;

import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;
import com.mikepenz.iconics.IconicsDrawable;
import com.mikepenz.ionicons_typeface_library.Ionicons;
import com.zmm.tmsystem.R;
import com.zmm.tmsystem.bean.TeacherBean;
import com.zmm.tmsystem.common.Constant;
import com.zmm.tmsystem.common.utils.ACache;
import com.zmm.tmsystem.common.utils.CheckUtils;
import com.zmm.tmsystem.common.utils.ToastUtils;
import com.zmm.tmsystem.dagger.component.AppComponent;
import com.zmm.tmsystem.dagger.component.DaggerTeacherComponent;
import com.zmm.tmsystem.dagger.module.TeacherModule;
import com.zmm.tmsystem.mvp.presenter.TeacherPresenter;
import com.zmm.tmsystem.mvp.presenter.contract.TeacherContract;
import com.zmm.tmsystem.ui.widget.CustomInfoItemView;
import com.zmm.tmsystem.ui.widget.GlideCircleTransform;
import com.zmm.tmsystem.ui.widget.TitleBar;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Description:
 * Author:zhangmengmeng
 * Date:2018/6/15
 * Time:下午4:09
 */

public class TeacherInfoActivity extends BaseActivity<TeacherPresenter> implements
        CustomInfoItemView.OnItemClickListener, TeacherContract.TeacherView {

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
    @BindView(R.id.custom_item_childcare)
    CustomInfoItemView mCustomItemChildcare;
    @BindView(R.id.custom_item_school)
    CustomInfoItemView mCustomItemSchool;
    @BindView(R.id.custom_item_grade)
    CustomInfoItemView mCustomItemGrade;
    @BindView(R.id.custom_item_course)
    CustomInfoItemView mCustomItemCourse;
    @BindView(R.id.custom_item_address)
    CustomInfoItemView mCustomItemAddress;
    @BindView(R.id.root_view)
    LinearLayout mRootView;

    private int mScreenWidth;


    @Override
    protected int setLayout() {
        return R.layout.activity_teacher_info;
    }


    @Override
    protected void setupActivityComponent(AppComponent appComponent) {
        DaggerTeacherComponent.builder()
                .appComponent(appComponent)
                .teacherModule(new TeacherModule(this))
                .build()
                .inject(this);
    }


    @Override
    protected void init() {

        mScreenWidth = getScreenWidth();

        mTitleBar.setCenterTitle("教师信息");
        mTitleBar.setNavigationIcon(new IconicsDrawable(this)
                .icon(Ionicons.Icon.ion_android_arrow_back)
                .sizeDp(20)
                .color(getResources().getColor(R.color.white)
                ));
        mTitleBar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        mCustomItemIcon.setOnItemClickListener(this, Constant.TYPE_ICON);
        mCustomItemName.setOnItemClickListener(this, Constant.TYPE_NAME);
        mCustomItemGender.setOnItemClickListener(this, Constant.TYPE_GENDER);
        mCustomItemPhone.setOnItemClickListener(this, Constant.TYPE_PHONE);
        mCustomItemChildcare.setOnItemClickListener(this, Constant.TYPE_CHILDCARE_NAME);
        mCustomItemSchool.setOnItemClickListener(this, Constant.TYPE_SCHOOL);
        mCustomItemGrade.setOnItemClickListener(this, Constant.TYPE_GRADE);
        mCustomItemCourse.setOnItemClickListener(this, Constant.TYPE_COURSE);
        mCustomItemAddress.setOnItemClickListener(this, Constant.TYPE_ADDRESS);


        ACache aCache = ACache.get(this);
        TeacherBean teacherBean = (TeacherBean) aCache.getAsObject(Constant.TEACHER);

        initData(teacherBean);

    }



    /**
     * 初始化数据
     */
    private void initData(TeacherBean teacherBean) {


        String icon = teacherBean.getIcon();
        String name = teacherBean.getName();
        int gender = teacherBean.getGender();
        String phone = teacherBean.getPhoneNum1();
        String childcareName = teacherBean.getChildcareName();
        String schoolName = teacherBean.getSchoolName();
        String gradeName = teacherBean.getGradeName();
        String courseName = teacherBean.getCourseName();
        String address = teacherBean.getAddress();

        if(CheckUtils.checkString(icon)){
            mCustomItemIcon.setIcon(icon);
        }

        if (CheckUtils.checkString(name)) {
            mCustomItemName.setContent(name);
        }

        if (CheckUtils.checkString(phone)) {
            mCustomItemPhone.setContent(phone);
        }

        if (CheckUtils.checkString(childcareName)) {
            mCustomItemChildcare.setContent(childcareName);
        }

        if (CheckUtils.checkString(schoolName)) {
            mCustomItemSchool.setContent(schoolName);
        }

        if (CheckUtils.checkString(gradeName)) {
            mCustomItemGrade.setContent(gradeName);
        }

        if (CheckUtils.checkString(courseName)) {
            mCustomItemCourse.setContent(courseName);
        }

        if (CheckUtils.checkString(address)) {
            mCustomItemAddress.setContent(address);
        }

        if (gender == 0) {
            mCustomItemGender.setContent("女");
        } else {
            mCustomItemGender.setContent("男");
        }

    }

    @Override
    public void itemClick(int type,String name) {

        mPresenter.updateTeacherByType(type,name,mRootView,mScreenWidth);

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
    public void updateSuccess(String title, TeacherBean teacherBean) {

        ToastUtils.SimpleToast(this, "更新" + title + "成功");
        initData(teacherBean);

    }


}
