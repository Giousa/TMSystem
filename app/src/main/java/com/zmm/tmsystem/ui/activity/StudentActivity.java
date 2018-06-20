package com.zmm.tmsystem.ui.activity;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;

import com.mikepenz.iconics.IconicsDrawable;
import com.mikepenz.ionicons_typeface_library.Ionicons;
import com.zmm.tmsystem.R;
import com.zmm.tmsystem.bean.StudentBean;
import com.zmm.tmsystem.dagger.component.AppComponent;
import com.zmm.tmsystem.dagger.component.DaggerStudentComponent;
import com.zmm.tmsystem.dagger.module.StudentModule;
import com.zmm.tmsystem.mvp.presenter.StudentPresenter;
import com.zmm.tmsystem.mvp.presenter.contract.StudentContract;
import com.zmm.tmsystem.ui.widget.TitleBar;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Description:
 * Author:zhangmengmeng
 * Date:2018/6/20
 * Time:下午3:18
 */

public class StudentActivity extends BaseActivity<StudentPresenter> implements StudentContract.StudentView, Toolbar.OnMenuItemClickListener {

    @BindView(R.id.title_bar)
    TitleBar mTitleBar;
    @BindView(R.id.rv_list)
    RecyclerView mRvList;
    @BindView(R.id.root_view)
    LinearLayout mRootView;


    private MenuItem mMenuItemAdd;


    @Override
    protected int setLayout() {
        return R.layout.activity_student;
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

        initToolBar();

    }

    private void initToolBar() {
        //这里一定要加上，否则menu不显示
        setSupportActionBar(mTitleBar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);


        mTitleBar.setCenterTitle("学生管理");
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

    @Override
    public void showLoading() {

    }

    @Override
    public void showError(String msg) {

    }

    @Override
    public void dismissLoading() {

    }

    @Override
    public void inputSuccess(int type, String content) {

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


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_actionbar, menu);
        menu.findItem(R.id.menu_setting).setVisible(false);
        mMenuItemAdd = menu.findItem(R.id.menu_add);

        mMenuItemAdd.setIcon(new IconicsDrawable(this)
                .icon(Ionicons.Icon.ion_android_add)
                .sizeDp(20)
                .color(getResources().getColor(R.color.white)
                ));

        mMenuItemAdd.setVisible(true);
        return true;
    }


    @Override
    public boolean onMenuItemClick(MenuItem item) {
        startActivity(StudentInfoActivity.class,false);
        return false;
    }


    @OnClick(R.id.btn_select_confirm)
    public void onViewClicked() {
    }
}
