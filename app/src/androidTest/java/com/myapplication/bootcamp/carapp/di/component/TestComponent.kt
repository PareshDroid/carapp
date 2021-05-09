package com.myapplication.bootcamp.carapp.di.component

import com.myapplication.bootcamp.carapp.di.module.ApplicationTestModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [ApplicationTestModule::class])
interface TestComponent : ApplicationComponent {
}