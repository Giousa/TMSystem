package com.zmm.tmsystem.dagger.component;

import com.zmm.tmsystem.dagger.module.TermModule;
import com.zmm.tmsystem.dagger.scope.ActivityScope;
import com.zmm.tmsystem.ui.activity.TermActivity;

import dagger.Component;

/**
 * Description:
 * Author:zhangmengmeng
 * Date:2018/6/17
 * Time:下午7:45
 */
@ActivityScope
@Component(modules = TermModule.class,dependencies = AppComponent.class)
public interface TermComponent {

    void inject(TermActivity activity);
}