package com.zmm.tmsystem.ui.activity;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.FrameLayout;

import com.mikepenz.iconics.IconicsDrawable;
import com.mikepenz.ionicons_typeface_library.Ionicons;
import com.zmm.tmsystem.R;
import com.zmm.tmsystem.dagger.component.AppComponent;
import com.zmm.tmsystem.ui.fragment.BottomBar;
import com.zmm.tmsystem.ui.fragment.CommentFragment;
import com.zmm.tmsystem.ui.fragment.CramFragment;
import com.zmm.tmsystem.ui.fragment.HomeFragment;
import com.zmm.tmsystem.ui.fragment.ManageFragment;
import com.zmm.tmsystem.ui.widget.TitleBar;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity {

    @BindView(R.id.title_bar)
    TitleBar mTitleBar;
    @BindView(R.id.fl_container)
    FrameLayout mFlContainer;
    @BindView(R.id.bottom_bar)
    BottomBar mBottomBar;
    private String url = "http://uog.oss-cn-shanghai.aliyuncs.com/icon_head/92edb0c47af3556d1f40d538c4e25e2b_xll.jpg";


    @Override
    protected int setLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected void init() {

//        Glide.with(this)
//                .load(url)
//                .transform(new GlideCircleTransform(this))
//                .error(new IconicsDrawable(this)
//                        .icon(Ionicons.Icon.ion_android_contact)
//                        .color(getResources().getColor(R.color.colorAccent)
//                        ))
//                .into(mIvHeader);
        initTablayout();


    }

    private void initTablayout() {

        Drawable drawable1 = new IconicsDrawable(this)
                .icon(Ionicons.Icon.ion_android_contact)
                .color(getResources().getColor(R.color.colorAccent)
                );

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
    }


    @Override
    protected void setupActivityComponent(AppComponent appComponent) {
    }

}
