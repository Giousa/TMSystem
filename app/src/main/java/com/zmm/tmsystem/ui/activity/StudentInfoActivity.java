package com.zmm.tmsystem.ui.activity;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.mikepenz.iconics.IconicsDrawable;
import com.mikepenz.ionicons_typeface_library.Ionicons;
import com.zmm.tmsystem.R;
import com.zmm.tmsystem.bean.StudentBean;
import com.zmm.tmsystem.common.Constant;
import com.zmm.tmsystem.common.utils.ACache;
import com.zmm.tmsystem.common.utils.ToastUtils;
import com.zmm.tmsystem.dagger.component.AppComponent;
import com.zmm.tmsystem.dagger.component.DaggerStudentComponent;
import com.zmm.tmsystem.dagger.module.StudentModule;
import com.zmm.tmsystem.mvp.presenter.StudentPresenter;
import com.zmm.tmsystem.mvp.presenter.contract.StudentContract;
import com.zmm.tmsystem.rx.RxBus;
import com.zmm.tmsystem.ui.widget.CustomInfoItemView;
import com.zmm.tmsystem.ui.widget.SimpleConfirmDialog;
import com.zmm.tmsystem.ui.widget.TitleBar;

import java.io.Serializable;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Description:
 * Author:zhangmengmeng
 * Date:2018/6/20
 * Time:下午3:57
 */

public class StudentInfoActivity extends BaseActivity<StudentPresenter> implements StudentContract.StudentView, CustomInfoItemView.OnItemClickListener, Toolbar.OnMenuItemClickListener {


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
    @BindView(R.id.btn_select_confirm)
    Button mBtnSelectConfirm;

    private int mIntentParam;
    private StudentBean mStudentBean;
    private MenuItem mItemEdit;
    private boolean isEdit = true;


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

        //0：代表添加新学生，1:代表修改学生，这个时候需要initData数据，2：从托管学生和补习学生跳转
        mIntentParam = getIntent().getIntExtra(Constant.INTENT_PARAM,0);

        initToolBar();

        initView();

        if(mIntentParam != 0){
            isEdit = false;
            mBtnSelectConfirm.setText("修改学生信息");
            mStudentBean = (StudentBean) getIntent().getSerializableExtra(Constant.STUDENT);
            mBtnSelectConfirm.setVisibility(View.GONE);
            initData();
        }

    }

    private void initToolBar() {

        //这里一定要加上，否则menu不显示
        setSupportActionBar(mTitleBar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        mTitleBar.setCenterTitle("学生资料");
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

        mTitleBar.setOnMenuItemClickListener(this);

    }

    private void initView() {
        mCustomItemIcon.setOnItemClickListener(this, Constant.TYPE_STUDENT_ICON);
        mCustomItemName.setOnItemClickListener(this, Constant.TYPE_STUDENT_NAME);
        mCustomItemGender.setOnItemClickListener(this, Constant.TYPE_STUDENT_GENDER);
        mCustomItemPhone.setOnItemClickListener(this, Constant.TYPE_STUDENT_PHONE);
        mCustomItemAddress.setOnItemClickListener(this, Constant.TYPE_STUDENT_ADDRESS);
        mCustomItemGuardian1.setOnItemClickListener(this, Constant.TYPE_STUDENT_GUARDIAN1);
        mCustomItemGuardianPhone1.setOnItemClickListener(this, Constant.TYPE_STUDENT_GUARDIANPHONE1);
        mCustomItemGuardian2.setOnItemClickListener(this, Constant.TYPE_STUDENT_GUARDIAN2);
        mCustomItemGuardianPhone2.setOnItemClickListener(this, Constant.TYPE_STUDENT_GUARDIANPHONE2);

        mCustomItemGender.setContent("男");
    }


    private void initData() {

        String icon = mStudentBean.getIcon();
        String name = mStudentBean.getName();
        Integer gender = mStudentBean.getGender();
        String phone = mStudentBean.getPhone();
        String address = mStudentBean.getAddress();
        String guardian1 = mStudentBean.getGuardian1();
        String guardian1Phone = mStudentBean.getGuardian1Phone();
        String guardian2 = mStudentBean.getGuardian2();
        String guardian2Phone = mStudentBean.getGuardian2Phone();

        if(!TextUtils.isEmpty(icon)){
            mCustomItemIcon.setIcon(icon);
        }

        if(!TextUtils.isEmpty(name)){
            mCustomItemName.setContent(name);
        }

        if (gender == 0) {
            mCustomItemGender.setContent("女");
        } else {
            mCustomItemGender.setContent("男");
        }

        if(!TextUtils.isEmpty(phone)){
            mCustomItemPhone.setContent(phone);
        }

        if(!TextUtils.isEmpty(address)){
            mCustomItemAddress.setContent(address);
        }

        if(!TextUtils.isEmpty(guardian1)){
            mCustomItemGuardian1.setContent(guardian1);
        }

        if(!TextUtils.isEmpty(guardian1Phone)){
            mCustomItemGuardianPhone1.setContent(guardian1Phone);
        }

        if(!TextUtils.isEmpty(guardian2)){
            mCustomItemGuardian2.setContent(guardian2);
        }

        if(!TextUtils.isEmpty(guardian2Phone)){
            mCustomItemGuardianPhone2.setContent(guardian2Phone);
        }


    }


    /**
     * 条目点击事件
     *
     * @param type
     * @param name
     */
    @Override
    public void itemClick(int type, String name) {

        if(isEdit){
            mPresenter.updateStudentData(type, name, mRootView, mScreenWidth);
        }else {
            switch (type){
                case Constant.TYPE_STUDENT_PHONE:
                    ToastUtils.SimpleToast(this,"电话");
                    break;

                case Constant.TYPE_STUDENT_GUARDIANPHONE1:
                    ToastUtils.SimpleToast(this,"电话1");

                    break;

                case Constant.TYPE_STUDENT_GUARDIANPHONE2:
                    ToastUtils.SimpleToast(this,"电话2");

                    break;
            }
        }
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

        switch (type) {
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
        ToastUtils.SimpleToast(this, getResources().getString(R.string.student_add_success));
        RxBus.getDefault().post(Constant.UPDATE_STUDENT);
        finish();
    }

    @Override
    public void addChildcareStudentSuccess(String msg) {

    }

    @Override
    public void updateSuccess() {
        ToastUtils.SimpleToast(this, getResources().getString(R.string.student_update_success));
        RxBus.getDefault().post(Constant.UPDATE_STUDENT);
    }

    @Override
    public void deleteStudent() {
        ToastUtils.SimpleToast(this,getResources().getString(R.string.student_delete_success));
        RxBus.getDefault().post(Constant.UPDATE_STUDENT);
        finish();
    }

    @Override
    public void querySuccess(List<StudentBean> studentBeans) {

    }

    @OnClick(R.id.btn_select_confirm)
    public void onViewClicked() {

        String name = mCustomItemName.getContent();
        String gender = mCustomItemGender.getContent();
        String phone = mCustomItemPhone.getContent();
        String address = mCustomItemAddress.getContent();
        String guardian1 = mCustomItemGuardian1.getContent();
        String guardian_phone1 = mCustomItemGuardianPhone1.getContent();
        String guardian2 = mCustomItemGuardian2.getContent();
        String guardian_phone2 = mCustomItemGuardianPhone2.getContent();

        if (TextUtils.isEmpty(name)) {
            ToastUtils.SimpleToast(this, "学生姓名不能为空");
            return;
        }

        if (TextUtils.isEmpty(guardian1)) {
            ToastUtils.SimpleToast(this, "监护人姓名不能为空");
            return;
        }

        StudentBean studentBean = new StudentBean();
        studentBean.setName(name);
        studentBean.setGender(gender.equals("女") ? 0 : 1);
        studentBean.setPhone(phone);
        studentBean.setAddress(address);
        studentBean.setGuardian1(guardian1);
        studentBean.setGuardian1Phone(guardian_phone1);
        studentBean.setGuardian2(guardian2);
        studentBean.setGuardian2Phone(guardian_phone2);

        String tId = ACache.get(this).getAsString(Constant.TEACHER_ID);
        studentBean.setTeacherId(tId);

        if(mIntentParam == 0){
            //0:添加新学生
            mPresenter.addStudent(studentBean);
        }else {
            //修改学生信息
            studentBean.setId(mStudentBean.getId());
            mPresenter.updateStudent(studentBean);
            edit();
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {


        if(mIntentParam != 0){

            getMenuInflater().inflate(R.menu.menu_actionbar, menu);
//            menu.findItem(R.id.menu_setting).setVisible(false);
            MenuItem item = menu.findItem(R.id.menu_add);

            item.setIcon(new IconicsDrawable(this)
                    .icon(Ionicons.Icon.ion_android_delete)
                    .sizeDp(20)
                    .color(getResources().getColor(R.color.white)
                    ));

            item.setVisible(true);

            mItemEdit = menu.findItem(R.id.menu_setting);

            mItemEdit.setIcon(new IconicsDrawable(this)
                    .iconText("编辑")
                    .sizeDp(30)
                    .color(getResources().getColor(R.color.white)
                    ));

            mItemEdit.setVisible(true);

        }
        return true;
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {

        switch (item.getItemId()){

            case R.id.menu_add:

                delete();
                break;
            case R.id.menu_setting:

                edit();
                break;

        }

        return false;

    }

    private void edit() {

        if(isEdit){
            isEdit = false;
            mItemEdit.setIcon(new IconicsDrawable(this)
                    .iconText("编辑")
                    .sizeDp(30)
                    .color(getResources().getColor(R.color.white)
                    ));

            mBtnSelectConfirm.setVisibility(View.GONE);
        }else {
            isEdit = true;
            mItemEdit.setIcon(new IconicsDrawable(this)
                    .iconText("完成")
                    .sizeDp(30)
                    .color(getResources().getColor(R.color.white)
                    ));
            mBtnSelectConfirm.setVisibility(View.VISIBLE);
        }
    }

    private void delete() {
        final SimpleConfirmDialog simpleConfirmDialog = new SimpleConfirmDialog(this,"是否确定删除此学生？");
        simpleConfirmDialog.setOnClickListener(new SimpleConfirmDialog.OnClickListener() {
            @Override
            public void onCancel() {
                simpleConfirmDialog.dismiss();
            }

            @Override
            public void onConfirm() {
                simpleConfirmDialog.dismiss();
                mPresenter.deleteStudent(mStudentBean.getId());

            }
        });

        simpleConfirmDialog.show();
    }


}
