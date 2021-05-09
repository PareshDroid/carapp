package com.myapplication.bootcamp.carapp.di.component

import com.myapplication.bootcamp.carapp.di.ActivityScope
import com.myapplication.bootcamp.carapp.di.module.ActivityModule
import com.myapplication.bootcamp.carapp.ui.dummy.DummyActivity
import com.myapplication.bootcamp.carapp.ui.login.LoginActivity
import com.myapplication.bootcamp.carapp.ui.main.MainActivity
import com.myapplication.bootcamp.carapp.ui.signup.SignUpActivity
import com.myapplication.bootcamp.carapp.ui.splash.SplashActivity
import dagger.Component

@ActivityScope
@Component(
    dependencies = [ApplicationComponent::class],
    modules = [ActivityModule::class]
)
interface ActivityComponent {

    fun inject(activity: SplashActivity)

    fun inject(activity: LoginActivity)

    fun inject(activity: DummyActivity)

    fun inject(activity: MainActivity)

    fun inject(activity: SignUpActivity)
}