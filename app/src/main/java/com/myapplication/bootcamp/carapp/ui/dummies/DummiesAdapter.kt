package com.myapplication.bootcamp.carapp.ui.dummies

import android.view.ViewGroup
import androidx.lifecycle.Lifecycle
import com.myapplication.bootcamp.carapp.data.model.Dummy
import com.myapplication.bootcamp.carapp.ui.base.BaseAdapter

class DummiesAdapter(
    parentLifecycle: Lifecycle,
    private val dummies: ArrayList<Dummy>
) : BaseAdapter<Dummy, DummyItemViewHolder>(parentLifecycle, dummies) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = DummyItemViewHolder(parent)
}