package com.myapplication.bootcamp.carapp.di.component

import com.myapplication.bootcamp.carapp.di.ViewModelScope
import com.myapplication.bootcamp.carapp.di.module.ViewHolderModule
import com.myapplication.bootcamp.carapp.ui.dummies.DummyItemViewHolder
import dagger.Component

@ViewModelScope
@Component(
    dependencies = [ApplicationComponent::class],
    modules = [ViewHolderModule::class]
)
interface ViewHolderComponent {

    fun inject(viewHolder: DummyItemViewHolder)
}