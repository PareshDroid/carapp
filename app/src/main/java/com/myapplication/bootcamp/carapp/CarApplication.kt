package com.myapplication.bootcamp.carapp

import android.app.Application
import com.jakewharton.threetenabp.AndroidThreeTen
import com.myapplication.bootcamp.carapp.di.component.ApplicationComponent
import com.myapplication.bootcamp.carapp.di.component.DaggerApplicationComponent
import com.myapplication.bootcamp.carapp.di.module.ApplicationModule

class CarApplication : Application() {

    lateinit var applicationComponent: ApplicationComponent

    override fun onCreate() {
        super.onCreate()
        AndroidThreeTen.init(this)
        injectDependencies()
    }

    private fun injectDependencies() {
        applicationComponent = DaggerApplicationComponent
            .builder()
            .applicationModule(ApplicationModule(this))
            .build()
        applicationComponent.inject(this)
    }

    // Needed to replace the component with a test specific one
    fun setComponent(applicationComponent: ApplicationComponent) {
        this.applicationComponent = applicationComponent
    }
}