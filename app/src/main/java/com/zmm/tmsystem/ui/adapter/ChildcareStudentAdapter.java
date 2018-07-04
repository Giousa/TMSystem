package com.zmm.tmsystem.ui.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.mikepenz.iconics.IconicsDrawable;
import com.mikepenz.ionicons_typeface_library.Ionicons;
import com.zmm.tmsystem.R;
import com.zmm.tmsystem.bean.ChildcareStudentBean;
import com.zmm.tmsystem.bean.StudentBean;
import com.zmm.tmsystem.common.utils.AgeUtils;
import com.zmm.tmsystem.ui.widget.GlideCircleTransform;

/**
 * Description:
 * Author:zhangmengmeng
 * Date:2018/6/17
 * Time:下午4:41
 */

public class ChildcareStudentAdapter extends BaseQuickAdapter<ChildcareStudentBean,BaseViewHolder>{

    private Context mContext;

    public ChildcareStudentAdapter(Context context) {
        super(R.layout.item_student_childcare);
        mContext = context;
    }

    @Override
    protected void convert(BaseViewHolder helper, ChildcareStudentBean childcareStudentBean) {



        helper.setText(R.id.tv_student_name, childcareStudentBean.getStudent().getName());
        String schoolName = childcareStudentBean.getSchool();
        helper.setText(R.id.tv_student_school, (TextUtils.isEmpty(schoolName)) ? "学校":schoolName);
        int gender = childcareStudentBean.getStudent().getGender();
        helper.setText(R.id.tv_student_gender, ((gender == 0)?"女":"男"));
//        long birthday = childcareStudentBean.getStudent().getBirthday();
//        helper.setText(R.id.tv_student_age, (birthday == 0)?"0": AgeUtils.getAgeFromBirthTime(birthday)+"");
        helper.setText(R.id.tv_student_grade, (TextUtils.isEmpty(childcareStudentBean.getGrade())) ? "":childcareStudentBean.getGrade());

        ImageView imageView = helper.getView(R.id.iv_student_icon);

        String icon = childcareStudentBean.getStudent().getIcon();
        if(TextUtils.isEmpty(icon)){

            if(gender == 0){
                imageView.setImageDrawable(new IconicsDrawable(mContext)
                        .icon(Ionicons.Icon.ion_android_contact)
                        .color(mContext.getResources().getColor(R.color.colorAccent)));

            }else {
                imageView.setImageDrawable(new IconicsDrawable(mContext)
                        .icon(Ionicons.Icon.ion_android_contact)
                        .color(mContext.getResources().getColor(R.color.colorPrimary)));
            }

        }else {
            Glide.with(mContext)
                    .load(icon)
                    .transform(new GlideCircleTransform(mContext))
                    .error(new IconicsDrawable(mContext)
                            .icon(Ionicons.Icon.ion_android_contact)
                            .color(mContext.getResources().getColor(R.color.colorAccent)
                            ))
                    .into(imageView);

            }

        }


}
