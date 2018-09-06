package com.zmm.tmsystem.ui.activity;

import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.mikepenz.iconics.IconicsDrawable;
import com.mikepenz.ionicons_typeface_library.Ionicons;
import com.zmm.tmsystem.R;
import com.zmm.tmsystem.bean.ScoreBean;
import com.zmm.tmsystem.common.Constant;
import com.zmm.tmsystem.common.utils.ToastUtils;
import com.zmm.tmsystem.dagger.component.AppComponent;
import com.zmm.tmsystem.dagger.component.DaggerScoreComponent;
import com.zmm.tmsystem.dagger.module.ScoreModule;
import com.zmm.tmsystem.mvp.presenter.ScorePresenter;
import com.zmm.tmsystem.mvp.presenter.contract.ScoreContract;
import com.zmm.tmsystem.ui.widget.CustomPayItemView;
import com.zmm.tmsystem.ui.widget.SimpleScoreSelectDialog;
import com.zmm.tmsystem.ui.widget.TitleBar;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Description:
 * Author:zhangmengmeng
 * Date:2018/9/5
 * Email:65489469@qq.com
 */
public class ScoreInfoActivity extends BaseActivity<ScorePresenter> implements ScoreContract.ScoreView {

    @BindView(R.id.title_bar)
    TitleBar mTitleBar;
    @BindView(R.id.et_title)
    EditText etTitle;
    @BindView(R.id.et_chinese)
    EditText etChinese;
    @BindView(R.id.et_maths)
    EditText etMaths;
    @BindView(R.id.et_english)
    EditText etEnglish;
    @BindView(R.id.et_physics)
    EditText etPhysics;
    @BindView(R.id.ll_physics)
    LinearLayout llPhysics;
    @BindView(R.id.et_chemistry)
    EditText etChemistry;
    @BindView(R.id.ll_chemistry)
    LinearLayout llChemistry;

    private String mChildcareStudentId;
    private int mGradeLevel;

    @Override
    protected int setLayout() {
        return R.layout.activity_score_info;
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

        if(mGradeLevel >= 7){
            llPhysics.setVisibility(View.VISIBLE);
            llChemistry.setVisibility(View.VISIBLE);
        }

        initToolBar();


    }

    private void initToolBar() {
        //这里一定要加上，否则menu不显示
        setSupportActionBar(mTitleBar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);


        mTitleBar.setCenterTitle("添加成绩单");
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



    @OnClick({R.id.iv_arrow, R.id.btn_submit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_arrow:

                selectTitle();
                break;
            case R.id.btn_submit:

                submitScore();

                break;
        }
    }

    /**
     * 选择标题
     */
    private void selectTitle() {
        final SimpleScoreSelectDialog simpleScoreSelectDialog = new SimpleScoreSelectDialog(this);
        simpleScoreSelectDialog.setOnTagClickListener(new CustomPayItemView.OnTagClickListener() {
            @Override
            public void OnTagClick(String name) {

                etTitle.setText(name);
                etTitle.setSelection(name.length());
                simpleScoreSelectDialog.dismiss();
            }
        });

        simpleScoreSelectDialog.show();

    }

    /**
     * 提交
     */
    private void submitScore() {

        String title = etTitle.getText().toString();
        if(TextUtils.isEmpty(title)){
            ToastUtils.SimpleToast(this,"请输入标题");
            return;
        }

        String chinese = etChinese.getText().toString();
        String math = etMaths.getText().toString();
        String english = etEnglish.getText().toString();
        String physics = etPhysics.getText().toString();
        String chemistry = etChemistry.getText().toString();

        if(TextUtils.isEmpty(chinese) &&  TextUtils.isEmpty(math) && TextUtils.isEmpty(english) &&
                TextUtils.isEmpty(physics) && TextUtils.isEmpty(chemistry) ){
            ToastUtils.SimpleToast(this,"请输入任意一科分数");
            return;
        }

        int intChinese = 0;
        int intMath = 0;
        int intEnglish = 0;
        int intPhysics = 0;
        int intChemistry = 0;

        if(!TextUtils.isEmpty(chinese)){
            intMath = Integer.parseInt(chinese);
        }

        if(!TextUtils.isEmpty(math)){
            intChinese = Integer.parseInt(math);
        }

        if(!TextUtils.isEmpty(english)){
            intEnglish = Integer.parseInt(english);
        }

        if(!TextUtils.isEmpty(physics)){
            intPhysics = Integer.parseInt(physics);
        }

        if(!TextUtils.isEmpty(chemistry)){
            intChemistry = Integer.parseInt(chemistry);
        }

        ScoreBean scoreBean = new ScoreBean();
        scoreBean.setStudentId(mChildcareStudentId);
        scoreBean.setTitle(title);
        scoreBean.setChinese(intChinese);
        scoreBean.setMaths(intMath);
        scoreBean.setEnglish(intEnglish);
        scoreBean.setPhysics(intPhysics);
        scoreBean.setChemistry(intChemistry);
        mPresenter.addScore(scoreBean);
    }


    @Override
    public void requestSuccess(String msg) {
        ToastUtils.SimpleToast(this,msg);
        finish();
    }

    @Override
    public void querySuccess(ScoreBean scoreBean) {
    }

    @Override
    public void queryAllScores(List<ScoreBean> scoreBeans) {

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
