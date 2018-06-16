package com.zmm.tmsystem.ui.activity;

import android.graphics.drawable.Drawable;
import android.support.v7.view.menu.MenuBuilder;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;

import com.mikepenz.iconics.IconicsDrawable;
import com.mikepenz.ionicons_typeface_library.Ionicons;
import com.zmm.tmsystem.R;
import com.zmm.tmsystem.common.utils.ToastUtils;
import com.zmm.tmsystem.dagger.component.AppComponent;
import com.zmm.tmsystem.ui.widget.BottomBar;
import com.zmm.tmsystem.ui.fragment.CommentFragment;
import com.zmm.tmsystem.ui.fragment.CramFragment;
import com.zmm.tmsystem.ui.fragment.HomeFragment;
import com.zmm.tmsystem.ui.fragment.ManageFragment;
import com.zmm.tmsystem.ui.widget.TitleBar;

import java.lang.reflect.Method;

import butterknife.BindView;

public class MainActivity extends BaseActivity implements BottomBar.OnSwitchFragmentListener, Toolbar.OnMenuItemClickListener {

    @BindView(R.id.title_bar)
    TitleBar mTitleBar;
    @BindView(R.id.fl_container)
    FrameLayout mFlContainer;
    @BindView(R.id.bottom_bar)
    BottomBar mBottomBar;
    private String url = "http://uog.oss-cn-shanghai.aliyuncs.com/icon_head/92edb0c47af3556d1f40d538c4e25e2b_xll.jpg";
    private MenuItem mMenuItem;

    @Override
    protected int setLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected void init() {

        //这里一定要加上，否则menu不显示
        setSupportActionBar(mTitleBar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        mTitleBar.setCenterTitle(getResources().getString(R.string.main_title_home));

        mTitleBar.setOnMenuItemClickListener(this);

        initTablayout();


    }

    private void initTablayout() {


        mBottomBar.setContainer(R.id.fl_container)
                .setTitleBeforeAndAfterColor("#999999", "#ff5d5e")
                .addItem(HomeFragment.class,
                        "我的",
                        R.drawable.item3_before,
                        R.drawable.item3_after)
                .addItem(ManageFragment.class,
                        "托管",
                        R.drawable.item1_before,
                        R.drawable.item1_after)
                .addItem(CramFragment.class,
                        "补课",
                        R.drawable.item2_before,
                        R.drawable.item2_after)
                .addItem(CommentFragment.class,
                        "评价",
                        R.drawable.item4_before,
                        R.drawable.item4_after)
                .build();

        mBottomBar.setOnSwitchFragmentListener(this);

    }


    @Override
    protected void setupActivityComponent(AppComponent appComponent) {
    }

    @Override
    public void onSwitchFragment(int index) {


        switch (index){

            case 0:
                mTitleBar.setCenterTitle(getResources().getString(R.string.main_title_home));
                mMenuItem.setVisible(true);
                break;

            case 1:
                mTitleBar.setCenterTitle(getResources().getString(R.string.main_title_manager));
                mMenuItem.setVisible(false);
                break;

            case 2:
                mTitleBar.setCenterTitle(getResources().getString(R.string.main_title_cram));
                mMenuItem.setVisible(false);
                break;

            case 3:
                mTitleBar.setCenterTitle(getResources().getString(R.string.main_title_comment));
                mMenuItem.setVisible(false);
                break;

        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_actionbar, menu);
        mMenuItem = menu.findItem(R.id.menu_right);
        mMenuItem.setIcon(new IconicsDrawable(this)
                .icon(Ionicons.Icon.ion_android_settings)
                .sizeDp(20)
                .color(getResources().getColor(R.color.white)
                ));
        return true;
    }


    @Override
    public boolean onMenuItemClick(MenuItem item) {
        startActivity(SettingActivity.class,false);
        return false;
    }

    private long time = 0;

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if (keyCode == event.KEYCODE_BACK) {
            if (System.currentTimeMillis() - time > 2000) {
                time = System.currentTimeMillis();
                ToastUtils.SimpleToast(this,"再次点击，退出应用");
            } else {
                removeAllActivity();
            }
        }

        return true;
    }
}
