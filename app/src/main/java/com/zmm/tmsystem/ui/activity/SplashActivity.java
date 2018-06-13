package com.zmm.tmsystem.ui.activity;

import com.zmm.tmsystem.R;
import com.zmm.tmsystem.dagger.component.AppComponent;
import java.util.concurrent.TimeUnit;
import io.reactivex.Observable;
import io.reactivex.functions.Consumer;

/**
 * Description:
 * Author:zhangmengmeng
 * Date:2018/6/12
 * Time:下午4:15
 */

public class SplashActivity extends BaseActivity {

    @Override
    protected int setLayout() {
        return R.layout.activity_splash;
    }

    @Override
    protected void init() {


        Observable.timer(2, TimeUnit.SECONDS).subscribe(new Consumer<Long>() {
            @Override
            public void accept(Long aLong) throws Exception {
                startActivity(LoginActivity.class);

            }
        });


    }

    @Override
    protected void setupActivityComponent(AppComponent appComponent) {

    }

}
