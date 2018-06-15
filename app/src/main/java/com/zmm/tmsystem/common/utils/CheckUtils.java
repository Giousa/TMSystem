package com.zmm.tmsystem.common.utils;

import android.text.TextUtils;

/**
 * Description:
 * Author:zhangmengmeng
 * Date:2018/6/15
 * Time:下午3:58
 */

public class CheckUtils {

    public static boolean checkString(String s){

        if(TextUtils.isEmpty(s)){
            return false;
        }else {
            return true;
        }

    }
}
