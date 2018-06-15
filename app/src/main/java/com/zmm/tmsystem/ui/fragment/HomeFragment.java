package com.zmm.tmsystem.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.mikepenz.iconics.IconicsDrawable;
import com.mikepenz.ionicons_typeface_library.Ionicons;
import com.zmm.tmsystem.R;
import com.zmm.tmsystem.bean.TeacherBean;
import com.zmm.tmsystem.common.utils.CheckUtils;
import com.zmm.tmsystem.dagger.component.AppComponent;
import com.zmm.tmsystem.dagger.component.DaggerHomeComponent;
import com.zmm.tmsystem.dagger.module.HomeModule;
import com.zmm.tmsystem.mvp.presenter.HomePresenter;
import com.zmm.tmsystem.mvp.presenter.contract.HomeContract;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Description:
 * Author:zhangmengmeng
 * Date:2018/6/15
 * Time:上午10:02
 */

public class HomeFragment extends ProgressFragment<HomePresenter> implements HomeContract.HomeView {


    @BindView(R.id.iv_head_icon)
    ImageView mIvHeadIcon;
    @BindView(R.id.tv_head_name)
    TextView mTvHeadName;
    @BindView(R.id.iv_head_gender)
    ImageView mIvHeadGender;
    @BindView(R.id.tv_head_childcare)
    TextView mTvHeadChildcare;
    @BindView(R.id.tv_head_sign)
    TextView mTvHeadSign;
    @BindView(R.id.tv_head_sign_level)
    TextView mTvHeadSignLevel;
    @BindView(R.id.tv_head_school_name)
    TextView mTvHeadSchoolName;
    @BindView(R.id.tv_head_grade_name)
    TextView mTvHeadGradeName;
    @BindView(R.id.tv_head_course_name)
    TextView mTvHeadCourseName;
    @Override
    protected int setLayout() {
        return R.layout.fragment_home;
    }

    @Override
    protected void setupAcitivtyComponent(AppComponent appComponent) {
        DaggerHomeComponent.builder()
                .appComponent(appComponent)
                .homeModule(new HomeModule(this))
                .build()
                .inject(this);
    }

    @Override
    protected void init() {


        mIvHeadIcon.setImageDrawable(new IconicsDrawable(mApplication)
                .icon(Ionicons.Icon.ion_android_contact)
                .color(getResources().getColor(R.color.md_blue_500)));


        mIvHeadGender.setImageDrawable(new IconicsDrawable(mApplication)
                .icon(Ionicons.Icon.ion_female)
                .color(getResources().getColor(R.color.colorAccent)));


        mPresenter.getTeacherById();
    }

    @Override
    public void showTeacherInfo(TeacherBean teacherBean) {
        System.out.println("HomeFragment teacher = " + teacherBean.toString());
        String name = teacherBean.getName();
        String childcareName = teacherBean.getChildcareName();
        String schoolName = teacherBean.getSchoolName();
        String gradeName = teacherBean.getGradeName();
        String courseName = teacherBean.getCourseName();

        int gender = teacherBean.getGender();
        int signDays = teacherBean.getSignDays();

        if(CheckUtils.checkString(name)){
            mTvHeadName.setText(name);
        }

        if(CheckUtils.checkString(childcareName)){
            mTvHeadChildcare.setText(childcareName);
        }

        if(CheckUtils.checkString(schoolName)){
            mTvHeadSchoolName.setText(schoolName);
        }

        if(CheckUtils.checkString(gradeName)){
            mTvHeadGradeName.setText(gradeName);
        }

        if(CheckUtils.checkString(courseName)){
            mTvHeadCourseName.setText(courseName);
        }

        if(gender == 0){
            mIvHeadGender.setImageDrawable(new IconicsDrawable(mApplication)
                    .icon(Ionicons.Icon.ion_female)
                    .color(getResources().getColor(R.color.colorAccent)));
        }else {
            mIvHeadGender.setImageDrawable(new IconicsDrawable(mApplication)
                    .icon(Ionicons.Icon.ion_male)
                    .color(getResources().getColor(R.color.colorPrimary)));
        }



    }

    @Override
    public void signSuccess() {

    }

    @Override
    public void signExist() {

    }


    @OnClick({R.id.iv_head_icon, R.id.tv_head_sign})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_head_icon:

                break;
            case R.id.tv_head_sign:
                
                break;
        }
    }

}
