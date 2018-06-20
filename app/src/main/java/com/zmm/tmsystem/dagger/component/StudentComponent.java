package com.zmm.tmsystem.dagger.component;

import com.zmm.tmsystem.dagger.module.StudentModule;
import com.zmm.tmsystem.dagger.scope.ActivityScope;
import com.zmm.tmsystem.ui.fragment.ManageFragment;

import dagger.Component;

/**
 * Description:
 * Author:zhangmengmeng
 * Date:2018/6/20
 * Time:下午2:20
 */
@ActivityScope
@Component(modules = StudentModule.class,dependencies = AppComponent.class)
public interface StudentComponent {

    void inject(ManageFragment fragment);
}
