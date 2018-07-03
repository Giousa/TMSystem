package com.zmm.tmsystem.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.zmm.tmsystem.R;
import com.zmm.tmsystem.bean.ChildcareStudentBean;
import com.zmm.tmsystem.bean.StudentBean;
import com.zmm.tmsystem.bean.TermBean;
import com.zmm.tmsystem.common.Constant;
import com.zmm.tmsystem.common.utils.ACache;
import com.zmm.tmsystem.common.utils.ToastUtils;
import com.zmm.tmsystem.dagger.component.AppComponent;
import com.zmm.tmsystem.dagger.component.DaggerChildcareStudentComponent;
import com.zmm.tmsystem.dagger.module.ChildcareStudentModule;
import com.zmm.tmsystem.mvp.presenter.ChildcareStudentPresenter;
import com.zmm.tmsystem.mvp.presenter.contract.ChildcareStudentContract;
import com.zmm.tmsystem.rx.RxBus;
import com.zmm.tmsystem.ui.activity.StudentActivity;
import com.zmm.tmsystem.ui.activity.StudentInfoActivity;
import com.zmm.tmsystem.ui.adapter.ChildcareStudentAdapter;
import com.zmm.tmsystem.ui.adapter.StudentAdapter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;

/**
 * Description:
 * Author:zhangmengmeng
 * Date:2018/6/15
 * Time:上午10:02
 */

public class ManageFragment extends ProgressFragment<ChildcareStudentPresenter> implements ChildcareStudentContract.ChildcareStudentView {

    @BindView(R.id.rv_list)
    RecyclerView mRecyclerView;


    private ACache mACache;
    private ChildcareStudentAdapter mStudentAdapter;


    @Override
    protected int setLayout() {
        return R.layout.manager;
    }

    @Override
    protected void setupAcitivtyComponent(AppComponent appComponent) {
        DaggerChildcareStudentComponent.builder()
                .appComponent(appComponent)
                .childcareStudentModule(new ChildcareStudentModule(this))
                .build()
                .inject(this);
    }

    @Override
    protected void init() {

        mACache = ACache.get(getActivity());

        initData();

        operateBus();
    }


    private void initData() {

        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        mStudentAdapter = new ChildcareStudentAdapter(getActivity());
        mStudentAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                //条目点击，进入详情
                ToastUtils.SimpleToast(getActivity(),"position = "+position);
//                StudentBean studentBean = (StudentBean) adapter.getItem(position);
//                Intent intent = new Intent(StudentActivity.this,StudentInfoActivity.class);
//                intent.putExtra(Constant.INTENT_PARAM,1);
//                intent.putExtra(Constant.STUDENT,studentBean);
//                startActivity(intent);
            }
        });

        mRecyclerView.setAdapter(mStudentAdapter);

        TermBean termBean = (TermBean) mACache.getAsObject(Constant.TERM);

        if (termBean != null) {
            mPresenter.queryAllChildcareStudents(termBean.getId());
        }

    }


    @Override
    public void onResume() {
        super.onResume();

    }

    @Override
    public void querySuccess(List<ChildcareStudentBean> childcareStudentBeans) {
        System.out.println("托管学生个数 = " + childcareStudentBeans.size());
        System.out.println("childcareStudentBeans = " + childcareStudentBeans);

        mStudentAdapter.setNewData(childcareStudentBeans);
    }

    @Override
    public void addSuccess(ChildcareStudentBean childcareStudentBean) {

    }

    @Override
    public void updateSuccess() {

    }

    @Override
    public void deleteSuccess() {

    }


    /**
     * RxBus  这里是更新选中的托管项目
     */
    private void operateBus() {
        RxBus.getDefault().toObservable()
                .map(new Function<Object, String>() {
                    @Override
                    public String apply(Object o) throws Exception {
                        return (String) o;
                    }
                })
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Exception {
                        if(!TextUtils.isEmpty(s)){

                            if(s.equals(Constant.UPDATE_TITLE) || s.equals(Constant.UPDATE_STUDENT_CHILDCARE)){
                                TermBean termBean = (TermBean) mACache.getAsObject(Constant.TERM);

                                if(termBean == null || termBean.getTitle()== null){
                                    System.out.println("没有选中任何托管周期");
                                    mStudentAdapter.setNewData(null);

                                }else {
                                    System.out.println("加载新托管学生...");
                                    mPresenter.queryAllChildcareStudents(termBean.getId());
                                }
                            }

                        }
                    }
                });
    }
}
