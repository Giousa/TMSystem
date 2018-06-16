package com.zmm.tmsystem.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.mikepenz.iconics.IconicsDrawable;
import com.mikepenz.ionicons_typeface_library.Ionicons;
import com.zmm.tmsystem.R;
import com.zmm.tmsystem.bean.TeacherBean;
import com.zmm.tmsystem.common.Constant;
import com.zmm.tmsystem.common.utils.ACache;
import com.zmm.tmsystem.common.utils.CheckUtils;
import com.zmm.tmsystem.common.utils.TeacherCacheUtil;
import com.zmm.tmsystem.dagger.component.AppComponent;
import com.zmm.tmsystem.ui.widget.CustomInfoItemView;
import com.zmm.tmsystem.ui.widget.GlideCircleTransform;
import com.zmm.tmsystem.ui.widget.TitleBar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Description:
 * Author:zhangmengmeng
 * Date:2018/6/17
 * Time:上午2:49
 */

public class SettingActivity extends BaseActivity implements CustomInfoItemView.OnItemClickListener {

    @BindView(R.id.title_bar)
    TitleBar mTitleBar;
    @BindView(R.id.iv_head)
    ImageView mIvHead;
    @BindView(R.id.setting_item_phone)
    CustomInfoItemView mSettingItemPhone;
    @BindView(R.id.setting_item_password)
    CustomInfoItemView mSettingItemPassword;
    @BindView(R.id.setting_item_code)
    CustomInfoItemView mSettingItemCode;
    @BindView(R.id.setting_item_change)
    CustomInfoItemView mSettingItemChange;

    @Override
    protected int setLayout() {
        return R.layout.activity_setting;
    }

    @Override
    protected void init() {

        mTitleBar.setCenterTitle(getResources().getString(R.string.main_title_setting));
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

        ACache aCache = ACache.get(this);
        TeacherBean teacherBean = (TeacherBean) aCache.getAsObject(Constant.TEACHER);


        String icon = teacherBean.getIcon();
        if (CheckUtils.checkString(icon)) {
            Glide.with(this)
                    .load(icon)
                    .transform(new GlideCircleTransform(this))
                    .error(new IconicsDrawable(this)
                            .icon(Ionicons.Icon.ion_android_contact)
                            .color(getResources().getColor(R.color.md_blue_500)
                            ))
                    .into(mIvHead);
        } else {
            mIvHead.setImageDrawable(new IconicsDrawable(this)
                    .icon(Ionicons.Icon.ion_android_contact)
                    .color(getResources().getColor(R.color.md_blue_500)
                    ));
        }

        mSettingItemPhone.setOnItemClickListener(this, Constant.TYPE_PHONE_LOGIN);
        mSettingItemPassword.setOnItemClickListener(this, Constant.TYPE_PASSWORD);
        mSettingItemCode.setOnItemClickListener(this, Constant.TYPE_QR_CODE);
        mSettingItemChange.setOnItemClickListener(this, Constant.TYPE_CHANGE);


    }

    @Override
    protected void setupActivityComponent(AppComponent appComponent) {

    }


    @Override
    public void itemClick(int type, String name) {
        switch (type) {

            case Constant.TYPE_PHONE_LOGIN:

                break;
            case Constant.TYPE_PASSWORD:

                break;
            case Constant.TYPE_QR_CODE:

                break;
            case Constant.TYPE_CHANGE:
                startActivity(LoginActivity.class, false);
                break;

        }
    }

    @OnClick(R.id.btn_exit)
    public void onViewClicked() {
        TeacherCacheUtil.clear(this);
        startActivity(LoginActivity.class);
    }
}
