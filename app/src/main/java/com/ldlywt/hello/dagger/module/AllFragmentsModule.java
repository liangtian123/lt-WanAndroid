package com.ldlywt.hello.dagger.module;

import com.ldlywt.hello.dagger.scope.PerFragment;
import com.ldlywt.hello.ui.home.HomeFragment;
import com.ldlywt.hello.ui.mine.MineFragment;
import com.ldlywt.hello.ui.project.ProjectFragment;
import com.ldlywt.hello.ui.tree.TreeFragment;

import dagger.Module;
import dagger.Provides;
import dagger.android.ContributesAndroidInjector;

/**
 * <pre>
 *     author : lt
 *     e-mail : androidlt@163.com
 *     time   : 2018/09/03
 *     desc   :
 *     version: 1.0
 * </pre>
 */
@Module
public  abstract class AllFragmentsModule {
//    @PerFragment
//    @ContributesAndroidInjector(modules = TopicFragmentModule.class)
//    abstract TopicFragment contributeTopicFragmentInjector();

    @PerFragment
    @ContributesAndroidInjector()
    abstract HomeFragment contributeHomeFragmentInjector();

    @PerFragment
    @ContributesAndroidInjector()
    abstract MineFragment contributeMineFragmentInjector();

    @PerFragment
    @ContributesAndroidInjector()
    abstract TreeFragment contributeTreeFragmentInjector();

    @PerFragment
    @ContributesAndroidInjector()
    abstract ProjectFragment contributeProjectFragmentInjector();
}
