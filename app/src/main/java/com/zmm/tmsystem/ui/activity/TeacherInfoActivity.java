package com.zmm.tmsystem.ui.activity;

import android.view.View;

import com.zmm.tmsystem.R;
import com.zmm.tmsystem.bean.TeacherBean;
import com.zmm.tmsystem.common.Constant;
import com.zmm.tmsystem.common.utils.ACache;
import com.zmm.tmsystem.common.utils.CheckUtils;
import com.zmm.tmsystem.dagger.component.AppComponent;
import com.zmm.tmsystem.ui.widget.CustomInfoItemView;
import com.zmm.tmsystem.ui.widget.TitleBar;

import butterknife.BindView;

/**
 * Description:
 * Author:zhangmengmeng
 * Date:2018/6/15
 * Time:下午4:09
 */

public class TeacherInfoActivity extends BaseActivity implements CustomInfoItemView.OnItemClickListener {

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
    @BindView(R.id.custom_item_qr_code)
    CustomInfoItemView mCustomItemQrCode;

    private final int TYPE_ICON = 0;
    private final int TYPE_NAME = 1;
    private final int TYPE_GENDER = 2;
    private final int TYPE_PHONE = 3;
    private final int TYPE_CHILDCARE_NAME = 4;
    private final int TYPE_SCHOOL = 5;
    private final int TYPE_GRADE = 6;
    private final int TYPE_COURSE = 7;
    private final int TYPE_ADDRESS = 8;
    private final int TYPE_QR_CODE = 9;

    @Override
    protected int setLayout() {
        return R.layout.activity_teacher_info;
    }

    @Override
    protected void init() {

        mTitleBar.setCenterTitle("教师信息");
        mTitleBar.setNavigationIcon(R.drawable.ic_action_back);
        mTitleBar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        mCustomItemIcon.setOnItemClickListener(this,TYPE_ICON);
        mCustomItemName.setOnItemClickListener(this,TYPE_NAME);
        mCustomItemGender.setOnItemClickListener(this,TYPE_GENDER);
        mCustomItemPhone.setOnItemClickListener(this,TYPE_PHONE);
        mCustomItemChildcare.setOnItemClickListener(this,TYPE_CHILDCARE_NAME);
        mCustomItemSchool.setOnItemClickListener(this,TYPE_SCHOOL);
        mCustomItemGrade.setOnItemClickListener(this,TYPE_GRADE);
        mCustomItemCourse.setOnItemClickListener(this,TYPE_COURSE);
        mCustomItemAddress.setOnItemClickListener(this,TYPE_ADDRESS);
        mCustomItemQrCode.setOnItemClickListener(this, TYPE_QR_CODE);

        initData();

    }

    /**
     * 初始化数据
     */
    private void initData() {

        ACache aCache = ACache.get(this);
        TeacherBean teacherBean = (TeacherBean) aCache.getAsObject(Constant.TEACHER);

        if(teacherBean == null){
            return;
        }

        String icon = teacherBean.getIcon();
        String name = teacherBean.getName();
        int gender = teacherBean.getGender();
        String phone = teacherBean.getPhone();
        String childcareName = teacherBean.getChildcareName();
        String schoolName = teacherBean.getSchoolName();
        String gradeName = teacherBean.getGradeName();
        String courseName = teacherBean.getCourseName();
        String address = teacherBean.getAddress();

        if(CheckUtils.checkString(icon)){
            mCustomItemIcon.setIcon(icon);
        }

        if(CheckUtils.checkString(name)){
            mCustomItemName.setContent(name);
        }

        if(CheckUtils.checkString(phone)){
            mCustomItemPhone.setContent(phone);
        }

        if(CheckUtils.checkString(childcareName)){
            mCustomItemChildcare.setContent(childcareName);
        }

        if(CheckUtils.checkString(schoolName)){
            mCustomItemSchool.setContent(schoolName);
        }

        if(CheckUtils.checkString(gradeName)){
            mCustomItemGrade.setContent(gradeName);
        }

        if(CheckUtils.checkString(courseName)){
            mCustomItemCourse.setContent(courseName);
        }

        if(CheckUtils.checkString(address)){
            mCustomItemAddress.setContent(address);
        }

        if(gender == 0){
            mCustomItemGender.setContent("女");
        }else {
            mCustomItemGender.setContent("男");
        }

    }

    @Override
    protected void setupActivityComponent(AppComponent appComponent) {

    }

    @Override
    public void itemClick(int id) {
        switch (id){
            case TYPE_ICON:

                break;

            case TYPE_NAME:

                break;
            case TYPE_GENDER:

                break;
            case TYPE_PHONE:

                break;
            case TYPE_CHILDCARE_NAME:

                break;
            case TYPE_SCHOOL:

                break;
            case TYPE_GRADE:

                break;
            case TYPE_COURSE:

                break;
            case TYPE_ADDRESS:

                break;
            case TYPE_QR_CODE:

                break;

        }
    }
}
