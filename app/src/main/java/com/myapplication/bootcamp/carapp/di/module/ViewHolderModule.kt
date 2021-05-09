package com.myapplication.bootcamp.carapp.di.module

import androidx.lifecycle.LifecycleRegistry
import com.myapplication.bootcamp.carapp.di.ViewModelScope
import com.myapplication.bootcamp.carapp.ui.base.BaseItemViewHolder
import dagger.Module
import dagger.Provides

@Module
class ViewHolderModule(private val viewHolder: BaseItemViewHolder<*, *>) {

    @Provides
    @ViewModelScope
    fun provideLifecycleRegistry(): LifecycleRegistry = LifecycleRegistry(viewHolder)
}