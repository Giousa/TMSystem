package com.zmm.tmsystem.ui.fragment;

import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.mikepenz.iconics.IconicsDrawable;
import com.mikepenz.ionicons_typeface_library.Ionicons;
import com.zmm.tmsystem.R;
import com.zmm.tmsystem.bean.TeacherBean;
import com.zmm.tmsystem.common.Constant;
import com.zmm.tmsystem.common.utils.ACache;
import com.zmm.tmsystem.common.utils.CheckUtils;
import com.zmm.tmsystem.dagger.component.AppComponent;
import com.zmm.tmsystem.dagger.component.DaggerHomeComponent;
import com.zmm.tmsystem.dagger.module.HomeModule;
import com.zmm.tmsystem.mvp.presenter.HomePresenter;
import com.zmm.tmsystem.mvp.presenter.contract.HomeContract;
import com.zmm.tmsystem.rx.RxBus;
import com.zmm.tmsystem.ui.activity.TeacherInfoActivity;
import com.zmm.tmsystem.ui.widget.GlideCircleTransform;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.functions.Consumer;

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

        ACache aCache = ACache.get(mContext);
        TeacherBean teacherBean = (TeacherBean) aCache.getAsObject(Constant.TEACHER);

        showTeacherInfo(teacherBean);

        RxBus.getDefault().toObservable(TeacherBean.class).subscribe(new Consumer<TeacherBean>() {
            @Override
            public void accept(TeacherBean teacherBean) throws Exception {
                System.out.println("HomeFragment  收到通知");
                showTeacherInfo(teacherBean);
            }
        });

        mPresenter.getSignInfo();
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

        if(CheckUtils.checkString(icon)){

            Glide.with(mContext)
                    .load(icon)
                    .transform(new GlideCircleTransform(mContext))
                    .error(new IconicsDrawable(mContext)
                            .icon(Ionicons.Icon.ion_android_contact)
                            .color(getResources().getColor(R.color.md_blue_500)
                            ))
                    .into(mIvHeadIcon);

        }else {
            mIvHeadIcon.setImageDrawable(new IconicsDrawable(mApplication)
                    .icon(Ionicons.Icon.ion_android_contact)
                    .color(getResources().getColor(R.color.md_blue_500)));
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
    public void signInfoSuccess(String msg) {
        mTvHeadSign.setText(msg);
    }

    @Override
    public void signSuccess() {
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
