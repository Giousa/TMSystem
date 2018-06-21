package com.zmm.tmsystem.ui.adapter;

import android.content.Context;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.mikepenz.iconics.IconicsDrawable;
import com.mikepenz.ionicons_typeface_library.Ionicons;
import com.zmm.tmsystem.R;
import com.zmm.tmsystem.bean.StudentBean;

/**
 * Description:
 * Author:zhangmengmeng
 * Date:2018/6/17
 * Time:下午4:41
 */

public class StudentAdapter extends BaseQuickAdapter<StudentBean,BaseViewHolder>{


    private Context mContext;

    public StudentAdapter(Context context) {
        super(R.layout.item_student);
        mContext = context;
    }

    @Override
    protected void convert(BaseViewHolder helper, StudentBean studentBean) {


        helper.setText(R.id.tv_student_title, studentBean.getName());
        helper.setText(R.id.tv_student_gender, "性别："+((studentBean.getGender() == 0)?"女":"男"));
        helper.setText(R.id.tv_student_guardian, "监护人："+studentBean.getGuardian1());
        helper.setText(R.id.tv_student_guardian_num, "监护人电话："+studentBean.getGuardian1Phone());

        helper.addOnClickListener(R.id.iv_term_checked);

        ImageView imageView = helper.getView(R.id.iv_term_checked);

        if(studentBean.isChecked()){
            imageView.setImageDrawable(new IconicsDrawable(mContext)
                    .icon(Ionicons.Icon.ion_android_checkbox_outline)
                    .color(mContext.getResources().getColor(R.color.colorAccent)));
        }else {
            imageView.setImageDrawable(new IconicsDrawable(mContext)
                    .icon(Ionicons.Icon.ion_android_checkbox_outline)
                    .color(mContext.getResources().getColor(R.color.chart_text)));
        }

    }

    public void setChecked() {
        notifyDataSetChanged();
    }
}
