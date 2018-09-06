package com.zmm.tmsystem.ui.activity;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;

import com.mikepenz.iconics.IconicsDrawable;
import com.mikepenz.ionicons_typeface_library.Ionicons;
import com.zmm.tmsystem.R;
import com.zmm.tmsystem.bean.CertificatesBean;
import com.zmm.tmsystem.common.Constant;
import com.zmm.tmsystem.dagger.component.AppComponent;
import com.zmm.tmsystem.dagger.component.DaggerCertificateInfoComponent;
import com.zmm.tmsystem.dagger.module.CertificateInfoModule;
import com.zmm.tmsystem.mvp.presenter.CertificateInfoPresenter;
import com.zmm.tmsystem.mvp.presenter.contract.CertificateInfoContract;
import com.zmm.tmsystem.ui.adapter.CertificateAdapter;
import com.zmm.tmsystem.ui.widget.TitleBar;

import java.util.List;

import butterknife.BindView;

/**
 * Description:
 * Author:zhangmengmeng
 * Date:2018/9/4
 * Email:65489469@qq.com
 */
public class CertificateActivity extends BaseActivity<CertificateInfoPresenter> implements Toolbar.OnMenuItemClickListener, CertificateInfoContract.CertificateInfoView {

    @BindView(R.id.title_bar)
    TitleBar mTitleBar;
    @BindView(R.id.rv_list)
    RecyclerView mRecyclerView;
    @BindView(R.id.empty)
    RelativeLayout mEmpty;

    private MenuItem mMenuItemAdd;
    private String mChildcareStudentId;
    private CertificateAdapter mCertificateAdapter;

    @Override
    protected int setLayout() {
        return R.layout.activity_certificate;
    }

    @Override
    protected void setupActivityComponent(AppComponent appComponent) {
        DaggerCertificateInfoComponent.builder()
                .appComponent(appComponent)
                .certificateInfoModule(new CertificateInfoModule(this))
                .build()
                .inject(this);
    }

    @Override
    protected void init() {

        mChildcareStudentId = this.getIntent().getStringExtra(Constant.CHILDCARE_STUDENT_ID);

        initToolBar();

        initListData();

    }

    @Override
    protected void onResume() {
        super.onResume();
        mPresenter.queryAllCertificates(mChildcareStudentId);

    }

    private void initToolBar() {
        //这里一定要加上，否则menu不显示
        setSupportActionBar(mTitleBar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);


        mTitleBar.setCenterTitle("荣誉证书");
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


    private void initListData() {
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        mCertificateAdapter = new CertificateAdapter(this);

        mRecyclerView.setAdapter(mCertificateAdapter);

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

        Intent intent = new Intent(this, CertificateInfoActivity.class);
        intent.putExtra(Constant.CHILDCARE_STUDENT_ID, mChildcareStudentId);
        startActivity(intent);

//        switch (item.getItemId()) {
//
//            case R.id.menu_add:
//
//                Intent intent = new Intent(this, CertificateInfoActivity.class);
//                intent.putExtra(Constant.CHILDCARE_STUDENT_ID, mChildcareStudentId);
//                startActivity(intent);
//
//                break;
//            case R.id.menu_setting:
//
//                startActivity(StudentRemoveActivity.class, false);
//                break;
//
//        }

        return false;
    }

    @Override
    public void uploadSuccess() {

    }

    @Override
    public void querySuccess(List<CertificatesBean> certificatesBeans) {

        if(certificatesBeans != null && certificatesBeans.size() > 0){
            mEmpty.setVisibility(View.GONE);
        }else {
            mEmpty.setVisibility(View.VISIBLE);
        }
        mCertificateAdapter.setNewData(certificatesBeans);
    }

    @Override
    public void deleteSuccess() {

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

}
