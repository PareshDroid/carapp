package com.myapplication.bootcamp.carapp.di.component

import com.myapplication.bootcamp.carapp.di.FragmentScope
import com.myapplication.bootcamp.carapp.di.module.FragmentModule
import com.myapplication.bootcamp.carapp.ui.dummies.DummiesFragment
import com.myapplication.bootcamp.carapp.ui.home.HomeFragment
import com.myapplication.bootcamp.carapp.ui.photo.PhotoFragment
import com.myapplication.bootcamp.carapp.ui.profile.ProfileFragment
import dagger.Component

@FragmentScope
@Component(
    dependencies = [ApplicationComponent::class],
    modules = [FragmentModule::class]
)
interface FragmentComponent {

    fun inject(fragment: DummiesFragment)

    fun inject(fragment: ProfileFragment)

    fun inject(fragment: PhotoFragment)

    fun inject(fragment: HomeFragment)
}