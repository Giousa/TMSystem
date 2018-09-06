package com.zmm.tmsystem.ui.activity;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;

import com.mikepenz.iconics.IconicsDrawable;
import com.mikepenz.ionicons_typeface_library.Ionicons;
import com.zmm.tmsystem.R;
import com.zmm.tmsystem.common.Constant;
import com.zmm.tmsystem.dagger.component.AppComponent;
import com.zmm.tmsystem.ui.adapter.SpendingAdapter;
import com.zmm.tmsystem.ui.widget.TitleBar;

import butterknife.BindView;

/**
 * Description:
 * Author:zhangmengmeng
 * Date:2018/9/5
 * Email:65489469@qq.com
 */
public class ScoreActivity extends BaseActivity implements Toolbar.OnMenuItemClickListener {

    @BindView(R.id.title_bar)
    TitleBar mTitleBar;
    @BindView(R.id.empty)
    RelativeLayout mEmpty;
    @BindView(R.id.rv_list)
    RecyclerView mRecyclerView;

    private MenuItem mMenuItemAdd;
    private String mChildcareStudentId;
    private int mGradeLevel;


    @Override
    protected int setLayout() {
        return R.layout.activity_score;
    }

    @Override
    protected void setupActivityComponent(AppComponent appComponent) {

    }

    @Override
    protected void init() {

        mChildcareStudentId = this.getIntent().getStringExtra(Constant.CHILDCARE_STUDENT_ID);
        mGradeLevel = this.getIntent().getIntExtra(Constant.CHILDCARE_STUDENT_GRADE_LEVEL, 0);

        initToolBar();

        initListData();
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

        mTitleBar.setOnMenuItemClickListener(this);


    }

    private void initListData() {

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

        Intent intent = new Intent(this,ScoreInfoActivity.class);
        intent.putExtra(Constant.CHILDCARE_STUDENT_ID,mChildcareStudentId);
        intent.putExtra(Constant.CHILDCARE_STUDENT_GRADE_LEVEL,mGradeLevel);
        startActivity(intent);

        return false;
    }

}
