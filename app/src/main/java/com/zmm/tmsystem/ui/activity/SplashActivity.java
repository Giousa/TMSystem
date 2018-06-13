package com.zmm.tmsystem.ui.activity;

import android.view.animation.AccelerateDecelerateInterpolator;

import com.eftimoff.androipathview.PathView;
import com.zmm.tmsystem.R;
import com.zmm.tmsystem.dagger.component.AppComponent;
import com.zmm.tmsystem.ui.MainActivity;

import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import io.reactivex.Observable;
import io.reactivex.functions.Consumer;

/**
 * Description:
 * Author:zhangmengmeng
 * Date:2018/6/12
 * Time:下午4:15
 */

public class SplashActivity extends BaseActivity {


//    @BindView(R.id.pathView)
//    PathView mPathView;

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


//        mPathView.getPathAnimator()
//                .delay(50)
//                .duration(1000)
//                .listenerEnd(new PathView.AnimatorBuilder.ListenerEnd() {
//                    @Override
//                    public void onAnimationEnd() {
//                        startActivity(LoginActivity.class);
//
//                    }
//                })
//                .interpolator(new AccelerateDecelerateInterpolator())
//                .start();

    }

    @Override
    protected void setupActivityComponent(AppComponent appComponent) {

    }

}
