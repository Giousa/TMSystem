package com.zmm.tmsystem.common;

/**
 * Description:
 * Author:zhangmengmeng
 * Date:2018/5/23
 * Time:下午5:54
 */

public class Constant {


//    public static final String BASE_URL="http://192.168.1.101:8081/tms/";
    public static final String BASE_URL="http://172.28.21.5:8081/tms/";

    public static final String BASE_IMG_URL="http://uog.oss-cn-shanghai.aliyuncs.com/pic/";


    public static final String BUNDLE_PARAM = "BUNDLE_PARAM";
    public static final String REGISTER_PARAM = "REGISTER_PARAM";
    public static final String MODIFY_PARAM = "MODIFY_PARAM";
    public static final String TEACHER = "TEACHER";
    public static final String TEACHER_ID = "TEACHER_ID";
    public static final String TMS_COOKIE = "tms_cookie";
    public static final String SIGN = "sign";


    //-----------------教师用户信息点击------------------
    public static final int TYPE_ICON = 0;
    public static final int TYPE_NAME = 1;
    public static final int TYPE_GENDER = 2;
    public static final int TYPE_PHONE = 3;
    public static final int TYPE_CHILDCARE_NAME = 4;
    public static final int TYPE_SCHOOL = 5;
    public static final int TYPE_GRADE = 6;
    public static final int TYPE_COURSE = 7;
    public static final int TYPE_ADDRESS = 8;
    public static final int TYPE_MODIFY_PHONE = 9;
    public static final int TYPE_MODIFY_PASSWORD = 10;
    public static final int TYPE_QR_CODE = 11;
    public static final int TYPE_CHANGE = 12;

    //缓存托管中心id
    public static final String TERM_CLICKED = "TERM_CLICKED";
    public static final String TERM = "TERM";

    //Rxbus消息
    public static final String UPDATE_TITLE = "UPDATE_TITLE";
    public static final String UPDATE_TERM = "UPDATE_TERM";
    public static final String UPDATE_STUDENT = "UPDATE_STUDENT";
    public static final String ADD_TERM_STUDENT = "ADD_TERM_STUDENT";


    //-----------------学生信息点击------------------
    public static final int TYPE_STUDENT_ICON = 0;
    public static final int TYPE_STUDENT_NAME = 1;
    public static final int TYPE_STUDENT_GENDER = 2;
    public static final int TYPE_STUDENT_PHONE = 3;
    public static final int TYPE_STUDENT_ADDRESS = 4;
    public static final int TYPE_STUDENT_GUARDIAN1 = 5;
    public static final int TYPE_STUDENT_GUARDIANPHONE1 = 6;
    public static final int TYPE_STUDENT_GUARDIAN2 = 7;
    public static final int TYPE_STUDENT_GUARDIANPHONE2 = 8;



}
