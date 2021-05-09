package com.myapplication.bootcamp.carapp.ui.profile

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import com.myapplication.bootcamp.carapp.R
import com.myapplication.bootcamp.carapp.di.component.FragmentComponent
import com.myapplication.bootcamp.carapp.ui.base.BaseFragment
import com.myapplication.bootcamp.carapp.ui.login.LoginActivity
import com.myapplication.bootcamp.carapp.ui.main.MainActivity
import com.myapplication.bootcamp.carapp.utils.common.Event
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_signup.*
import kotlinx.android.synthetic.main.fragment_profile.*

class ProfileFragment : BaseFragment<ProfileViewModel>() {

    companion object {

        const val TAG = "ProfileFragment"

        fun newInstance(): ProfileFragment {
            val args = Bundle()
            val fragment = ProfileFragment()
            fragment.arguments = args
            return fragment
        }
    }

    override fun provideLayoutId(): Int = R.layout.fragment_profile

    override fun injectDependencies(fragmentComponent: FragmentComponent) {
        fragmentComponent.inject(this)
    }

    override fun setupObservers() {
        super.setupObservers()

        viewModel.launchLogin.observe(this, Observer<Event<Map<String, String>>> {
            it.getIfNotHandled()?.run {
                startActivity(Intent(activity, LoginActivity::class.java))
                activity?.finish()
            }
        })

        viewModel.name.observe(this, Observer {
            tv_name.setText(it)
        })

        viewModel.days.observe(this, Observer {
            tv_profile_days.setText(it+" days old")
        })

        viewModel.loggingIn.observe(this, Observer {
            pb_profile_loading.visibility = if (it) View.VISIBLE else View.GONE
        })

    }

    override fun setupView(view: View) {

        bt_logout.setOnClickListener {
            viewModel.onLoggedOut()
        }
    }

}