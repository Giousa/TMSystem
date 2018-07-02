package com.zmm.tmsystem.ui.adapter;

import android.content.Context;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.mikepenz.iconics.IconicsDrawable;
import com.mikepenz.ionicons_typeface_library.Ionicons;
import com.zmm.tmsystem.R;
import com.zmm.tmsystem.bean.ChildcareStudentBean;
import com.zmm.tmsystem.bean.StudentBean;

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
    protected void convert(BaseViewHolder helper, ChildcareStudentBean studentBean) {


        helper.setText(R.id.tv_student_title, studentBean.getStudent().getName());
        helper.setText(R.id.tv_student_gender, "性别："+((studentBean.getStudent().getGender() == 0)?"女":"男"));
        helper.setText(R.id.tv_student_guardian, "监护人："+studentBean.getStudent().getGuardian1());
        helper.setText(R.id.tv_student_guardian_num, "监护人电话："+studentBean.getStudent().getGuardian1Phone());

        helper.addOnClickListener(R.id.iv_term_checked);

    }

    public void setChecked() {
        notifyDataSetChanged();
    }
}
