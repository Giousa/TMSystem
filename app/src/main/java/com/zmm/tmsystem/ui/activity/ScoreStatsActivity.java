package com.zmm.tmsystem.ui.activity;

import android.view.View;
import android.widget.RelativeLayout;

import com.mikepenz.iconics.IconicsDrawable;
import com.mikepenz.ionicons_typeface_library.Ionicons;
import com.zmm.tmsystem.R;
import com.zmm.tmsystem.bean.ScoreBean;
import com.zmm.tmsystem.common.Constant;
import com.zmm.tmsystem.dagger.component.AppComponent;
import com.zmm.tmsystem.dagger.component.DaggerScoreComponent;
import com.zmm.tmsystem.dagger.module.ScoreModule;
import com.zmm.tmsystem.mvp.presenter.ScorePresenter;
import com.zmm.tmsystem.mvp.presenter.contract.ScoreContract;
import com.zmm.tmsystem.ui.widget.TitleBar;

import java.util.List;

import butterknife.BindView;

/**
 * Description:
 * Author:zhangmengmeng
 * Date:2018/9/5
 * Email:65489469@qq.com
 */
public class ScoreStatsActivity extends BaseActivity<ScorePresenter> implements ScoreContract.ScoreView{

    @BindView(R.id.title_bar)
    TitleBar mTitleBar;
    @BindView(R.id.empty)
    RelativeLayout mEmpty;

    private String mChildcareStudentId;
    private int mGradeLevel;

    @Override
    protected int setLayout() {
        return R.layout.activity_score_stats;
    }

    @Override
    protected void setupActivityComponent(AppComponent appComponent) {
        DaggerScoreComponent.builder()
                .appComponent(appComponent)
                .scoreModule(new ScoreModule(this))
                .build()
                .inject(this);
    }

    @Override
    protected void init() {

        mChildcareStudentId = this.getIntent().getStringExtra(Constant.CHILDCARE_STUDENT_ID);
        mGradeLevel = this.getIntent().getIntExtra(Constant.CHILDCARE_STUDENT_GRADE_LEVEL, 0);

        initToolBar();

    }

    @Override
    protected void onResume() {
        super.onResume();
        mPresenter.queryAllScores(mChildcareStudentId);

    }

    private void initToolBar() {
        //这里一定要加上，否则menu不显示
        setSupportActionBar(mTitleBar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);


        mTitleBar.setCenterTitle("成绩单");
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

    }

    @Override
    public void requestSuccess(String msg) {

    }

    @Override
    public void querySuccess(ScoreBean scoreBean) {

    }

    @Override
    public void queryAllScores(List<ScoreBean> scoreBeans) {
        System.out.println("成绩折线图："+scoreBeans);
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
