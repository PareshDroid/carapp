package com.myapplication.bootcamp.carapp.ui.dummies

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.myapplication.bootcamp.carapp.R
import com.myapplication.bootcamp.carapp.di.component.FragmentComponent
import com.myapplication.bootcamp.carapp.ui.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_dummies.*
import javax.inject.Inject

class DummiesFragment : BaseFragment<DummiesViewModel>() {

    companion object {

        const val TAG = "DummiesFragment"

        fun newInstance(): DummiesFragment {
            val args = Bundle()
            val fragment = DummiesFragment()
            fragment.arguments = args
            return fragment
        }
    }

    @Inject
    lateinit var linearLayoutManager: LinearLayoutManager

    @Inject
    lateinit var dummiesAdapter: DummiesAdapter

    override fun provideLayoutId(): Int = R.layout.fragment_dummies

    override fun injectDependencies(fragmentComponent: FragmentComponent) {
        fragmentComponent.inject(this)
    }

    override fun setupObservers() {
        super.setupObservers()
        viewModel.getDummies().observe(this, Observer {
            it?.run { dummiesAdapter.appendData(this) }
        })
    }

    override fun setupView(view: View) {
        rv_dummy.layoutManager = linearLayoutManager
        rv_dummy.adapter = dummiesAdapter
    }

}