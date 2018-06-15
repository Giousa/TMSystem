package com.zmm.tmsystem.dagger.component;

import com.zmm.tmsystem.dagger.module.HomeModule;
import com.zmm.tmsystem.dagger.scope.ActivityScope;
import com.zmm.tmsystem.ui.fragment.HomeFragment;

import dagger.Component;

/**
 * Description:
 * Author:zhangmengmeng
 * Date:2018/6/14
 * Time:下午11:50
 */
@ActivityScope
@Component(modules = HomeModule.class,dependencies = AppComponent.class)
public interface HomeComponent {

    //TODO 暂时关闭
    void inject(HomeFragment fragment);
}
