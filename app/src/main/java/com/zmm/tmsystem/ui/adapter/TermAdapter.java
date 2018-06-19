package com.zmm.tmsystem.ui.adapter;

import android.content.Context;
import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.mikepenz.iconics.IconicsDrawable;
import com.mikepenz.ionicons_typeface_library.Ionicons;
import com.zmm.tmsystem.R;
import com.zmm.tmsystem.bean.TermBean;

import java.util.List;

/**
 * Description:
 * Author:zhangmengmeng
 * Date:2018/6/17
 * Time:下午4:41
 */

public class TermAdapter extends BaseQuickAdapter<TermBean,BaseViewHolder>{


    private Context mContext;
    private int mCheckPosition = 0;

    public TermAdapter(Context context) {
        super(R.layout.item_term);
        mContext = context;
    }

    @Override
    protected void convert(BaseViewHolder helper, TermBean item) {
        helper.setText(R.id.tv_term_title, item.getTitle());
        helper.setText(R.id.tv_term_year, item.getYear()+"");
        helper.setText(R.id.tv_term_month, item.getMonth()+"");
        helper.setText(R.id.tv_term_content, item.getTerm());

        ImageView imageView = helper.getView(R.id.iv_term_checked);

        int adapterPosition = helper.getAdapterPosition();
        System.out.println("adapterPosition = "+adapterPosition);

        if(adapterPosition == mCheckPosition){
            imageView.setImageDrawable(new IconicsDrawable(mContext)
                    .icon(Ionicons.Icon.ion_android_checkbox_outline)
                    .color(mContext.getResources().getColor(R.color.colorAccent)));
        }else {
            imageView.setImageDrawable(new IconicsDrawable(mContext)
                    .icon(Ionicons.Icon.ion_android_checkbox_outline)
                    .color(mContext.getResources().getColor(R.color.chart_text)));
        }



    }

    public void setChecked(int position) {
        mCheckPosition = position;
        notifyDataSetChanged();
    }
}
