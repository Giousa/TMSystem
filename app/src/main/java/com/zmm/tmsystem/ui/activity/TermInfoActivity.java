package com.zmm.tmsystem.ui.activity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.jakewharton.rxbinding2.InitialValueObservable;
import com.jakewharton.rxbinding2.view.RxView;
import com.jakewharton.rxbinding2.widget.RxTextView;
import com.mikepenz.iconics.IconicsDrawable;
import com.mikepenz.ionicons_typeface_library.Ionicons;
import com.zmm.tmsystem.R;
import com.zmm.tmsystem.bean.TermBean;
import com.zmm.tmsystem.common.Constant;
import com.zmm.tmsystem.common.utils.ACache;
import com.zmm.tmsystem.common.utils.ToastUtils;
import com.zmm.tmsystem.dagger.component.AppComponent;
import com.zmm.tmsystem.dagger.component.DaggerTermComponent;
import com.zmm.tmsystem.dagger.module.TermModule;
import com.zmm.tmsystem.mvp.presenter.TermPresenter;
import com.zmm.tmsystem.mvp.presenter.contract.TermContract;
import com.zmm.tmsystem.rx.RxBus;
import com.zmm.tmsystem.ui.widget.LoadingButton;
import com.zmm.tmsystem.ui.widget.SimpleSelectDialog;
import com.zmm.tmsystem.ui.widget.TitleBar;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function4;

/**
 * Description:
 * Author:zhangmengmeng
 * Date:2018/6/20
 * Time:上午12:18
 */

public class TermInfoActivity extends BaseActivity<TermPresenter> implements TermContract.TermView {


    @BindView(R.id.et_dialog_title)
    EditText mEtDialogTitle;
    @BindView(R.id.et_dialog_year)
    EditText mEtDialogYear;
    @BindView(R.id.et_dialog_month)
    EditText mEtDialogMonth;
    @BindView(R.id.et_dialog_term)
    EditText mEtDialogTerm;
    @BindView(R.id.btn_dialog_update)
    LoadingButton mBtnDialogUpdate;
    @BindView(R.id.btn_dialog_delete)
    LoadingButton mBtnDialogDelete;
    @BindView(R.id.title_bar)
    TitleBar mTitleBar;
    private TermBean mTermBean;

    @Override
    protected int setLayout() {
        return R.layout.activity_term_info;
    }

    @Override
    protected void setupActivityComponent(AppComponent appComponent) {
        DaggerTermComponent.builder()
                .appComponent(appComponent)
                .termModule(new TermModule(this))
                .build()
                .inject(this);
    }

    @Override
    protected void init() {

        initToolBar();

        initData();

        initEvent();

    }

    private void initToolBar() {
        mTitleBar.setCenterTitle("托管周期信息");
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

    }


    private void initData() {
        mTermBean = (TermBean) getIntent().getSerializableExtra(Constant.TERM_CLICKED);

        String title = mTermBean.getTitle();
        String year = mTermBean.getYear() + "";
        String month = mTermBean.getMonth() + "";
        String term = mTermBean.getTerm();

        mEtDialogTitle.setText(title);
        mEtDialogYear.setText(year);
        mEtDialogMonth.setText(month);
        mEtDialogTerm.setText(term);

        mEtDialogTitle.setSelection(title.length());
        mEtDialogYear.setSelection(year.length());
        mEtDialogMonth.setSelection(month.length());
        mEtDialogTerm.setSelection(term.length());
    }


    private void initEvent() {

        InitialValueObservable<CharSequence> obTitle = RxTextView.textChanges(mEtDialogTitle);
        InitialValueObservable<CharSequence> obYear = RxTextView.textChanges(mEtDialogYear);
        InitialValueObservable<CharSequence> obMonth = RxTextView.textChanges(mEtDialogMonth);
        InitialValueObservable<CharSequence> obTerm = RxTextView.textChanges(mEtDialogTerm);


        Observable.combineLatest(obTitle, obYear, obMonth, obTerm,
                new Function4<CharSequence, CharSequence, CharSequence, CharSequence, Boolean>() {
                    @Override
                    public Boolean apply(CharSequence title, CharSequence year, CharSequence month, CharSequence term) throws Exception {
                        return isTitleValid(title.toString()) && isYearValid(year.toString()) && isMonthValid(month.toString()) && isTermValid(term.toString());
                    }
                }).subscribe(new Consumer<Boolean>() {
            @Override
            public void accept(Boolean aBoolean) throws Exception {
                RxView.enabled(mBtnDialogUpdate).accept(aBoolean);
            }
        });


        RxView.clicks(mBtnDialogUpdate).subscribe(new Consumer<Object>() {
            @Override
            public void accept(Object o) throws Exception {

                mTermBean.setTitle(mEtDialogTitle.getText().toString().trim());
                mTermBean.setYear(Integer.parseInt(mEtDialogYear.getText().toString().trim()));
                mTermBean.setMonth(Integer.parseInt(mEtDialogMonth.getText().toString().trim()));
                mTermBean.setTerm(mEtDialogTerm.getText().toString().trim());

                mPresenter.updateTerm(mTermBean);
            }
        });

        RxView.clicks(mBtnDialogDelete).subscribe(new Consumer<Object>() {
            @Override
            public void accept(Object o) throws Exception {
//                mPresenter.updateTerm(mTermBean);
                final SimpleSelectDialog simpleSelectDialog = new SimpleSelectDialog(TermInfoActivity.this);
                simpleSelectDialog.setOnClickListener(new SimpleSelectDialog.OnClickListener() {
                    @Override
                    public void onCancel() {
                        simpleSelectDialog.dismiss();
                    }

                    @Override
                    public void onConfirm() {
                        simpleSelectDialog.dismiss();

                        mPresenter.delete(mTermBean.getId());
                    }
                });

                simpleSelectDialog.show();
            }
        });


    }

    private boolean isTitleValid(String title) {
        return !TextUtils.isEmpty(title);
    }

    private boolean isYearValid(String year) {
        return !TextUtils.isEmpty(year);
    }

    private boolean isMonthValid(String month) {
        return !TextUtils.isEmpty(month);
    }

    private boolean isTermValid(String term) {
        return !TextUtils.isEmpty(term);
    }


    @Override
    public void showLoading() {
        mBtnDialogUpdate.showLoading();
    }

    @Override
    public void showError(String msg) {
        mBtnDialogUpdate.showButtonText();
    }

    @Override
    public void dismissLoading() {
        mBtnDialogUpdate.showButtonText();
    }

    @Override
    public void createSuccess(TermBean termBean) {

    }

    @Override
    public void updateSuccess(String s) {
        ToastUtils.SimpleToast(this, s);
        RxBus.getDefault().post("updateTerm");
    }

    @Override
    public void deleteSuccess(String id) {

        ACache aCache = ACache.get(this);
        TermBean termBean = (TermBean) aCache.getAsObject(Constant.TERM);
        if(id.equals(termBean.getId())){
            aCache.put(Constant.TERM,new TermBean());
        }
        RxBus.getDefault().post(Constant.UPDATE_TERM);
        finish();
    }

    @Override
    public void getAllTerms(List<TermBean> list) {

    }

}
