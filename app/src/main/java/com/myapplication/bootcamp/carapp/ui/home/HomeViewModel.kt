package com.myapplication.bootcamp.carapp.ui.home

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.myapplication.bootcamp.carapp.data.model.Person
import com.myapplication.bootcamp.carapp.data.remote.response.VehicleResponse
import com.myapplication.bootcamp.carapp.data.repository.UserRepository
import com.myapplication.bootcamp.carapp.ui.base.BaseViewModel
import com.myapplication.bootcamp.carapp.utils.common.Event
import com.myapplication.bootcamp.carapp.utils.common.Resource
import com.myapplication.bootcamp.carapp.utils.network.NetworkHelper
import com.myapplication.bootcamp.carapp.utils.rx.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable

class HomeViewModel(
    schedulerProvider: SchedulerProvider,
    compositeDisposable: CompositeDisposable,
    networkHelper: NetworkHelper,
    private val userRepository: UserRepository
) : BaseViewModel(schedulerProvider, compositeDisposable, networkHelper) {


    val loggingIn: MutableLiveData<Boolean> = MutableLiveData()

    val vehicleList: MutableLiveData<Resource<List<VehicleResponse>>> = MutableLiveData()

    val vehicles = ArrayList<VehicleResponse>()

    private val person: Person = userRepository.getCurrentUser()!!

    override fun onCreate() {

        loggingIn.postValue(true)
        compositeDisposable.addAll(
            userRepository.doFetchVehicles(person.authToken)
                .subscribeOn(schedulerProvider.io())
                .subscribe(
                    {
                        vehicles.addAll(it)
                        loggingIn.postValue(false)
                    },
                    {
                        handleNetworkError(it)
                        loggingIn.postValue(false)
                    }
                )
        )
    }

    fun onMapReady(){
        vehicleList.postValue(Resource.success(vehicles))
    }
}