package com.zmm.tmsystem.ui.fragment;

import com.zmm.tmsystem.R;
import com.zmm.tmsystem.bean.ChildcareStudentBean;
import com.zmm.tmsystem.bean.TermBean;
import com.zmm.tmsystem.common.Constant;
import com.zmm.tmsystem.common.utils.ACache;
import com.zmm.tmsystem.dagger.component.AppComponent;
import com.zmm.tmsystem.dagger.component.DaggerCommentComponent;
import com.zmm.tmsystem.dagger.module.CommentModule;
import com.zmm.tmsystem.mvp.presenter.CommentPresenter;
import com.zmm.tmsystem.mvp.presenter.contract.CommentContract;

import java.util.List;

/**
 * Description:
 * Author:zhangmengmeng
 * Date:2018/6/15
 * Time:上午10:02
 */

public class CommentFragment extends ProgressFragment<CommentPresenter> implements CommentContract.CommentView{

    private ACache mACache;


    @Override
    protected int setLayout() {
        return R.layout.fragment_comment;
    }

    @Override
    protected void setupAcitivtyComponent(AppComponent appComponent) {
        DaggerCommentComponent.builder()
                .appComponent(appComponent)
                .commentModule(new CommentModule(this))
                .build()
                .inject(this);
    }

    @Override
    protected void init() {

        mACache = ACache.get(getActivity());
        TermBean termBean = (TermBean) mACache.getAsObject(Constant.TERM);

        if(termBean != null){
//            mPresenter.queryTodayStudents(termBean.getId());
            mPresenter.queryAllChildcareStudents(termBean.getId());
        }
    }

    @Override
    public void queryTodaySuccess(List<ChildcareStudentBean> childcareStudentBeans) {

    }

    @Override
    public void queryStudents(List<ChildcareStudentBean> childcareStudentBeans) {
        System.out.println("childcareStudentBeans = "+childcareStudentBeans);
    }

    @Override
    public void commentSuccess(String msg) {

    }

    @Override
    public void commentFailure() {

    }
}
