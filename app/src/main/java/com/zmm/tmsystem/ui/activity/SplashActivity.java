package com.zmm.tmsystem.ui.activity;

import android.view.animation.AccelerateDecelerateInterpolator;

import com.eftimoff.androipathview.PathView;
import com.zmm.tmsystem.R;
import com.zmm.tmsystem.dagger.component.AppComponent;
import com.zmm.tmsystem.ui.MainActivity;

import butterknife.BindView;

/**
 * Description:
 * Author:zhangmengmeng
 * Date:2018/6/12
 * Time:下午4:15
 */

public class SplashActivity extends BaseActivity {


    @BindView(R.id.pathView)
    PathView mPathView;

    @Override
    protected int setLayout() {
        return R.layout.activity_splash;
    }

    @Override
    protected void init() {
        mPathView.getPathAnimator()
                .delay(50)
                .duration(2000)
                .listenerEnd(new PathView.AnimatorBuilder.ListenerEnd() {
                    @Override
                    public void onAnimationEnd() {
                        startActivity(MainActivity.class);

                    }
                })
                .interpolator(new AccelerateDecelerateInterpolator())
                .start();
    }

    @Override
    protected void setupActivityComponent(AppComponent appComponent) {

    }

}
