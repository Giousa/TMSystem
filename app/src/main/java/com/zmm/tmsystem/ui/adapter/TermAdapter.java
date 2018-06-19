package com.zmm.tmsystem.ui.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
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


    public TermAdapter(List<TermBean> data) {
        super(R.layout.item_term,data);
    }

    @Override
    protected void convert(BaseViewHolder helper, TermBean item) {
        helper.setText(R.id.tv_title, item.getTitle());
    }
}
