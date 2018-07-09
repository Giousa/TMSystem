package com.zmm.tmsystem.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;

import com.mikepenz.iconics.IconicsDrawable;
import com.mikepenz.ionicons_typeface_library.Ionicons;
import com.zmm.tmsystem.R;
import com.zmm.tmsystem.bean.ChildcareStudentBean;
import com.zmm.tmsystem.bean.TermBean;
import com.zmm.tmsystem.common.Constant;
import com.zmm.tmsystem.common.utils.ToastUtils;
import com.zmm.tmsystem.dagger.component.AppComponent;
import com.zmm.tmsystem.dagger.component.DaggerChildcareStudentComponent;
import com.zmm.tmsystem.dagger.module.ChildcareStudentModule;
import com.zmm.tmsystem.mvp.presenter.ChildcareStudentPresenter;
import com.zmm.tmsystem.mvp.presenter.contract.ChildcareStudentContract;
import com.zmm.tmsystem.rx.RxBus;
import com.zmm.tmsystem.ui.widget.CustomInfoItemView;
import com.zmm.tmsystem.ui.widget.SimpleConfirmDialog;
import com.zmm.tmsystem.ui.widget.TitleBar;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;

/**
 * Description:
 * Author:zhangmengmeng
 * Date:2018/7/3
 * Time:上午11:34
 */

public class ChildcareStudentInfoActivity extends BaseActivity<ChildcareStudentPresenter> implements ChildcareStudentContract.ChildcareStudentView, Toolbar.OnMenuItemClickListener, CustomInfoItemView.OnItemClickListener {

    @BindView(R.id.title_bar)
    TitleBar mTitleBar;
    @BindView(R.id.custom_item_icon)
    CustomInfoItemView mCustomItemIcon;
    @BindView(R.id.custom_item_name)
    CustomInfoItemView mCustomItemName;
    @BindView(R.id.custom_item_school)
    CustomInfoItemView mCustomItemSchool;
    @BindView(R.id.custom_item_grade)
    CustomInfoItemView mCustomItemGrade;
    @BindView(R.id.custom_item_teacher)
    CustomInfoItemView mCustomItemTeacher;
    @BindView(R.id.custom_item_teacher_phone)
    CustomInfoItemView mCustomItemTeacherPhone;
    @BindView(R.id.custom_item_guardian_phone)
    CustomInfoItemView mCustomItemGuardianPhone;
    @BindView(R.id.custom_item_info)
    CustomInfoItemView mCustomItemInfo;
    @BindView(R.id.custom_item_certificates)
    CustomInfoItemView mCustomItemCertificates;
    @BindView(R.id.custom_item_pay)
    CustomInfoItemView mCustomItemPay;
    @BindView(R.id.root_view)
    LinearLayout mRootView;

    private ChildcareStudentBean mChildcareStudentBean;
    private MenuItem mItemEdit;
    private boolean isEdit = false;

    @Override
    protected int setLayout() {
        return R.layout.activity_student_childcare;
    }

    @Override
    protected void setupActivityComponent(AppComponent appComponent) {
        DaggerChildcareStudentComponent.builder()
                .appComponent(appComponent)
                .childcareStudentModule(new ChildcareStudentModule(this))
                .build()
                .inject(this);
    }

    @Override
    protected void init() {


        mChildcareStudentBean = (ChildcareStudentBean) getIntent().getSerializableExtra(Constant.STUDENT);

        initToolBar();

        initView();

        initData(mChildcareStudentBean);

        operateBus();
    }


    private void initToolBar() {

        //这里一定要加上，否则menu不显示
        setSupportActionBar(mTitleBar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        mTitleBar.setCenterTitle("托管学生资料");
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
//        mCustomItemIcon.setOnItemClickListener(this, Constant.TYPE_STUDENT_ICON);
//        mCustomItemName.setOnItemClickListener(this, Constant.TYPE_STUDENT_NAME);
//        mCustomItemGuardianPhone.setOnItemClickListener(this, Constant.TYPE_STUDENT_GUARDIANPHONE1);
        mCustomItemSchool.setOnItemClickListener(this, Constant.TYPE_STUDENT_SCHOOL);
        mCustomItemGrade.setOnItemClickListener(this, Constant.TYPE_STUDENT_GRADE);
        mCustomItemTeacher.setOnItemClickListener(this, Constant.TYPE_STUDENT_TEACHER);
        mCustomItemTeacherPhone.setOnItemClickListener(this, Constant.TYPE_STUDENT_TEACHER_PHONE);
        mCustomItemInfo.setOnItemClickListener(this, Constant.TYPE_STUDENT_INFO);
        mCustomItemCertificates.setOnItemClickListener(this, Constant.TYPE_STUDENT_CERTIFICATES);
        mCustomItemPay.setOnItemClickListener(this, Constant.TYPE_STUDENT_PAY);

    }

    private void initData(ChildcareStudentBean childcareStudentBean) {

        String icon = childcareStudentBean.getStudent().getIcon();
        String name = childcareStudentBean.getStudent().getName();
        String school = childcareStudentBean.getSchool();
        String grade = childcareStudentBean.getGrade();
        String teacher = childcareStudentBean.getTeacher();
        String teacherPhone = childcareStudentBean.getTeacherPhone();
        String guardian1Phone = childcareStudentBean.getStudent().getGuardian1Phone();

        mCustomItemIcon.setIcon(icon);
        mCustomItemName.setContent(name);
        mCustomItemSchool.setContent(school);
        mCustomItemGrade.setContent(grade);
        mCustomItemTeacher.setContent(teacher);
        mCustomItemTeacherPhone.setContent(teacherPhone);
        mCustomItemGuardianPhone.setContent(guardian1Phone);


    }


    @Override
    public void showLoading() {

    }

    @Override
    public void showError(String msg) {
        ToastUtils.SimpleToast(this,msg);
    }

    @Override
    public void dismissLoading() {

    }

    @Override
    public void querySuccess(List<ChildcareStudentBean> childcareStudentBeans) {

    }

    @Override
    public void updateSuccess(ChildcareStudentBean childcareStudentBean) {
        initData(childcareStudentBean);
        RxBus.getDefault().post(Constant.UPDATE_STUDENT_CHILDCARE);
    }

    @Override
    public void deleteSuccess() {
        ToastUtils.SimpleToast(this,getResources().getString(R.string.student_delete_success));
        RxBus.getDefault().post(Constant.UPDATE_STUDENT_CHILDCARE);
        finish();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_actionbar, menu);
//        menu.findItem(R.id.menu_setting).setVisible(false);
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

        }else {
            isEdit = true;
            mItemEdit.setIcon(new IconicsDrawable(this)
                    .iconText("完成")
                    .sizeDp(30)
                    .color(getResources().getColor(R.color.white)
                    ));
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
                mPresenter.deleteChildcareStudent(mChildcareStudentBean.getId());

            }
        });

        simpleConfirmDialog.show();
    }

    @Override
    public void itemClick(int type, String name) {

        if(isEdit){
            mPresenter.updateChildcareStudentData(type, name, mRootView, mScreenWidth,mChildcareStudentBean);
        }else {
            switch (type){


                case Constant.TYPE_STUDENT_TEACHER_PHONE:
                    ToastUtils.SimpleToast(this,"班主任电话");
                    break;

                case Constant.TYPE_STUDENT_GUARDIANPHONE1:
                    ToastUtils.SimpleToast(this,"监护人电话");
                    break;

                case Constant.TYPE_STUDENT_INFO:

                    Intent intentInfo = new Intent(this,StudentInfoActivity.class);
                    intentInfo.putExtra(Constant.INTENT_PARAM,2);
                    intentInfo.putExtra(Constant.STUDENT,mChildcareStudentBean.getStudent());
                    startActivity(intentInfo);

                    break;

                case Constant.TYPE_STUDENT_CERTIFICATES:
                    ToastUtils.SimpleToast(this,"进入荣誉证书界面");
                    break;

                case Constant.TYPE_STUDENT_PAY:
                    ToastUtils.SimpleToast(this,"进入消费详细界面");
                    break;
            }
        }

    }

    /**
     * RxBus  这里是更新选中的托管项目
     */
    private void operateBus() {
        RxBus.getDefault().toObservable()
                .map(new Function<Object, String>() {
                    @Override
                    public String apply(Object o) throws Exception {
                        return (String) o;
                    }
                })
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Exception {
                        if(!TextUtils.isEmpty(s)){

                            if(s.equals(Constant.UPDATE_STUDENT)){
                                //学生个人信息变动，更新数据
                                mPresenter.findChildcareStudentById(mChildcareStudentBean.getId());
                            }

                        }
                    }
                });
    }
}
