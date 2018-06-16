package com.zmm.tmsystem.mvp.presenter;

import com.zmm.tmsystem.common.Constant;
import com.zmm.tmsystem.common.utils.ToastUtils;
import com.zmm.tmsystem.mvp.presenter.contract.TeacherContract;

import javax.inject.Inject;

/**
 * Description:
 * Author:zhangmengmeng
 * Date:2018/6/15
 * Time:下午10:42
 */

public class TeacherPresenter extends BasePresenter<TeacherContract.ITeacherModel,TeacherContract.TeacherView> {


    @Inject
    public TeacherPresenter(TeacherContract.ITeacherModel model, TeacherContract.TeacherView view) {
        super(model, view);
    }

    public void updateTeacherInfo(int id){

        System.out.println("id = "+id);
        switch (id){

            case Constant.TYPE_ICON:
                uloadIcon();
                break;
            case Constant.TYPE_NAME:
                updateName();
                break;
            case Constant.TYPE_GENDER:
                updateGender();
                break;
            case Constant.TYPE_PHONE:
                updatePhone();
                break;
            case Constant.TYPE_CHILDCARE_NAME:
                updateChildCare();
                break;
            case Constant.TYPE_SCHOOL:
                selecctSchool();
                break;
            case Constant.TYPE_GRADE:
                selecctGrade();
                break;
            case Constant.TYPE_COURSE:
                selecctCourse();
                break;
            case Constant.TYPE_ADDRESS:
                updateAddress();
                break;
            case Constant.TYPE_QR_CODE:
                showQrCode();
                break;

        }
    }

    private void uloadIcon() {

    }

    private void updateName() {

    }

    private void updateGender() {

    }

    private void updatePhone() {

    }

    private void updateChildCare() {
    }

    private void selecctSchool() {
    }

    private void selecctGrade() {
    }

    private void selecctCourse() {

    }

    private void updateAddress() {

    }

    private void showQrCode() {

    }
}
