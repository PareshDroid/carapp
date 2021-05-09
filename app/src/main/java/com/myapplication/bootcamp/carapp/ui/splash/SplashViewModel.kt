package com.myapplication.bootcamp.carapp.ui.splash

import android.content.Intent
import android.os.Handler
import androidx.lifecycle.MutableLiveData
import com.myapplication.bootcamp.carapp.data.repository.UserRepository
import com.myapplication.bootcamp.carapp.ui.base.BaseViewModel
import com.myapplication.bootcamp.carapp.utils.common.Event
import com.myapplication.bootcamp.carapp.utils.network.NetworkHelper
import com.myapplication.bootcamp.carapp.utils.rx.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable


class SplashViewModel(
    schedulerProvider: SchedulerProvider,
    compositeDisposable: CompositeDisposable,
    networkHelper: NetworkHelper,
    val userRepository: UserRepository
) : BaseViewModel(schedulerProvider, compositeDisposable, networkHelper) {

    private val SPLASH_DISPLAY_LENGTH:Long = 1000

    // Event is used by the view model to tell the activity to launch another Activity
    // view model also provided the Bundle in the event that is needed for the Activity
    val launchMain: MutableLiveData<Event<Map<String, String>>> = MutableLiveData()
    val launchLogin: MutableLiveData<Event<Map<String, String>>> = MutableLiveData()

    override fun onCreate() {


        Handler().postDelayed(Runnable { /* Create an Intent that will start the Menu-Activity. */
            // Empty map of key and serialized value is passed to Activity in Event that is needed by the other Activity
            if (userRepository.getCurrentUser() != null)
                launchMain.postValue(Event(emptyMap()))
            else
                launchLogin.postValue(Event(emptyMap()))
        }, SPLASH_DISPLAY_LENGTH)
    }
}