package com.myapplication.bootcamp.carapp.ui.profile

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.myapplication.bootcamp.carapp.data.model.Person
import com.myapplication.bootcamp.carapp.data.repository.UserRepository
import com.myapplication.bootcamp.carapp.ui.base.BaseViewModel
import com.myapplication.bootcamp.carapp.utils.common.Event
import com.myapplication.bootcamp.carapp.utils.common.TimeUtils
import com.myapplication.bootcamp.carapp.utils.network.NetworkHelper
import com.myapplication.bootcamp.carapp.utils.rx.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable
import org.threeten.bp.Duration
import org.threeten.bp.LocalDateTime

class ProfileViewModel(
    schedulerProvider: SchedulerProvider,
    compositeDisposable: CompositeDisposable,
    networkHelper: NetworkHelper,
    private val userRepository: UserRepository
) : BaseViewModel(schedulerProvider, compositeDisposable, networkHelper) {


    val launchLogin: MutableLiveData<Event<Map<String, String>>> = MutableLiveData()

    val loggingIn: MutableLiveData<Boolean> = MutableLiveData()

    val name: MutableLiveData<String> = MutableLiveData()
    val days: MutableLiveData<String> = MutableLiveData()

    private val person: Person = userRepository.getCurrentUser()!! // should not be used without logged in user

    override fun onCreate() {
        if (checkInternetConnectionWithMessage()) {
            loggingIn.postValue(true)
            compositeDisposable.addAll(
                userRepository.doFetchUserProfile(person.key,person.authToken)
                    .subscribeOn(schedulerProvider.io())
                    .subscribe(
                        {
                            name.postValue(it.name)
                            days.postValue(TimeUtils.getDateDifference(it.createdAt))
                            loggingIn.postValue(false)

                        },
                        {
                            handleNetworkError(it)
                            loggingIn.postValue(false)
                        }
                    )
            )
        }
    }

    fun onLoggedOut() {
        userRepository.removeCurrentUser()
        launchLogin.postValue(Event(emptyMap()))
    }
}