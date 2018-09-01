package com.zmm.tmsystem.ui.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.github.mikephil.charting.data.PieEntry;
import com.mikepenz.iconics.IconicsDrawable;
import com.mikepenz.ionicons_typeface_library.Ionicons;
import com.zmm.tmsystem.R;
import com.zmm.tmsystem.bean.TeacherBean;
import com.zmm.tmsystem.common.Constant;
import com.zmm.tmsystem.common.utils.ACache;
import com.zmm.tmsystem.common.utils.CheckUtils;
import com.zmm.tmsystem.common.utils.ToastUtils;
import com.zmm.tmsystem.dagger.component.AppComponent;
import com.zmm.tmsystem.dagger.component.DaggerHomeComponent;
import com.zmm.tmsystem.dagger.module.HomeModule;
import com.zmm.tmsystem.mvp.presenter.HomePresenter;
import com.zmm.tmsystem.mvp.presenter.contract.HomeContract;
import com.zmm.tmsystem.ui.activity.TeacherInfoActivity;
import com.zmm.tmsystem.ui.widget.CustomMPChartPieView;
import com.zmm.tmsystem.ui.widget.GlideCircleTransform;

import java.util.ArrayList;

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
    @BindView(R.id.custom_pie_view1)
    CustomMPChartPieView customPieView1;
    @BindView(R.id.custom_pie_view2)
    CustomMPChartPieView customPieView2;
    @BindView(R.id.custom_pie_view3)
    CustomMPChartPieView customPieView3;


    private Context mContext;

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


        mContext = getActivity();

        mPresenter.getSignInfo();
    }


    @Override
    public void onResume() {
        super.onResume();
        ACache aCache = ACache.get(mContext);
        TeacherBean teacherBean = (TeacherBean) aCache.getAsObject(Constant.TEACHER);
        if (teacherBean == null) {
            return;
        }
        showTeacherInfo(teacherBean);

        initChartView();
    }

    /**
     * 初始化饼状图view
     */
    private void initChartView() {

        ArrayList<PieEntry> entries = new ArrayList();
        entries.add(new PieEntry(12, "男"));
        entries.add(new PieEntry(17, "女"));

        ArrayList<Integer> colors = new ArrayList<>();
        colors.add(getResources().getColor(R.color.md_indigo_A700));
        colors.add(getResources().getColor(R.color.md_pink_400));

        customPieView1.setData(entries, colors, "性别");


        ArrayList<PieEntry> entries2 = new ArrayList();
        entries2.add(new PieEntry(12, "初中"));
        entries2.add(new PieEntry(17, "小学"));

        ArrayList<Integer> colors2 = new ArrayList<>();
        colors2.add(getResources().getColor(R.color.md_deep_orange_600));
        colors2.add(getResources().getColor(R.color.md_cyan_600));

        customPieView2.setData(entries2, colors2, "年级");


        ArrayList<PieEntry> entries3 = new ArrayList();
        entries3.add(new PieEntry(2, "一初"));
        entries3.add(new PieEntry(3, "实验"));
        entries3.add(new PieEntry(4, "五初"));
        entries3.add(new PieEntry(8, "荣光"));
        entries3.add(new PieEntry(5, "铸才"));
        entries3.add(new PieEntry(6, "一小"));
        entries3.add(new PieEntry(2, "薛城"));

        ArrayList<Integer> colors3 = new ArrayList<>();
        colors3.add(getResources().getColor(R.color.md_indigo_A700));
        colors3.add(getResources().getColor(R.color.md_pink_400));
        colors3.add(getResources().getColor(R.color.md_deep_orange_600));
        colors3.add(getResources().getColor(R.color.md_cyan_600));
        colors3.add(getResources().getColor(R.color.md_deep_purple_400));
        colors3.add(getResources().getColor(R.color.md_green_600));
        colors3.add(getResources().getColor(R.color.md_red_200));

        customPieView3.setData(entries3, colors3, "学校");


    }

    @Override
    protected void onEmptyViewClick() {
        super.onEmptyViewClick();
//        mPresenter.getTeacherById();

        mPresenter.getSignInfo();
    }

    public void showTeacherInfo(TeacherBean teacherBean) {

        String name = teacherBean.getName();
        String icon = teacherBean.getIcon();
        String childcareName = teacherBean.getChildcareName();
        String schoolName = teacherBean.getSchoolName();
        String gradeName = teacherBean.getGradeName();
        String courseName = teacherBean.getCourseName();

        int gender = teacherBean.getGender();
        int signDays = teacherBean.getSignDays();

        if (CheckUtils.checkString(name)) {
            mTvHeadName.setText(name);
        }

        if (CheckUtils.checkString(childcareName)) {
            mTvHeadChildcare.setText(childcareName);
        }

        if (CheckUtils.checkString(schoolName)) {
            mTvHeadSchoolName.setText(schoolName);
        }

        if (CheckUtils.checkString(gradeName)) {
            mTvHeadGradeName.setText(gradeName);
        }

        if (CheckUtils.checkString(courseName)) {
            mTvHeadCourseName.setText(courseName);
        }

        if (CheckUtils.checkString(icon)) {

            Glide.with(mContext)
                    .load(Constant.BASE_IMG_URL + icon)
                    .transform(new GlideCircleTransform(mContext))
                    .error(new IconicsDrawable(mContext)
                            .icon(Ionicons.Icon.ion_android_contact)
                            .color(getResources().getColor(R.color.md_blue_500)
                            ))
                    .into(mIvHeadIcon);

        } else {
            mIvHeadIcon.setImageDrawable(new IconicsDrawable(mApplication)
                    .icon(Ionicons.Icon.ion_android_contact)
                    .color(getResources().getColor(R.color.md_blue_500)));
        }


        if (gender == 0) {
            mIvHeadGender.setImageDrawable(new IconicsDrawable(mApplication)
                    .icon(Ionicons.Icon.ion_female)
                    .color(getResources().getColor(R.color.colorAccent)));
        } else {
            mIvHeadGender.setImageDrawable(new IconicsDrawable(mApplication)
                    .icon(Ionicons.Icon.ion_male)
                    .color(getResources().getColor(R.color.colorPrimary)));
        }


    }

    @Override
    public void signInfoSuccess(String msg) {
        mTvHeadSign.setText(msg);
    }

    @Override
    public void signSuccess() {
        ToastUtils.SimpleToast(mContext, getResources().getString(R.string.home_head_sign_success));
        mTvHeadSign.setText(getResources().getString(R.string.home_head_sign_exist));
    }

    @Override
    public void signExist() {
        mTvHeadSign.setText(getResources().getString(R.string.home_head_sign_exist));
    }


    @OnClick({R.id.iv_head_icon, R.id.tv_head_sign})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_head_icon:

                startActivity(TeacherInfoActivity.class);

                break;
            case R.id.tv_head_sign:

                mPresenter.sign();
                break;
        }
    }

}
