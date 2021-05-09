package com.myapplication.bootcamp.carapp.ui.dummies

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.myapplication.bootcamp.carapp.data.model.Dummy
import com.myapplication.bootcamp.carapp.data.repository.DummyRepository
import com.myapplication.bootcamp.carapp.ui.base.BaseViewModel
import com.myapplication.bootcamp.carapp.utils.common.Resource
import com.myapplication.bootcamp.carapp.utils.common.Status
import com.myapplication.bootcamp.carapp.utils.network.NetworkHelper
import com.myapplication.bootcamp.carapp.utils.rx.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable


class DummiesViewModel(
    schedulerProvider: SchedulerProvider,
    compositeDisposable: CompositeDisposable,
    networkHelper: NetworkHelper,
    private val dummyRepository: DummyRepository
) : BaseViewModel(schedulerProvider, compositeDisposable, networkHelper) {

    private val dummyLiveData: MutableLiveData<Resource<List<Dummy>>> = MutableLiveData()

    fun getDummies(): LiveData<List<Dummy>> =
        Transformations.map(dummyLiveData) { it.data }

    fun isDummiesFetching(): LiveData<Boolean> =
        Transformations.map(dummyLiveData) { it.status == Status.LOADING }

    override fun onCreate() {
        if (dummyLiveData.value == null && checkInternetConnectionWithMessage()) {
            dummyLiveData.postValue(Resource.loading())
            compositeDisposable.add(
                dummyRepository.fetchDummy("MY_SAMPLE_DUMMY")
                    .subscribeOn(schedulerProvider.io())
                    .subscribe(
                        { dummyLiveData.postValue(Resource.success(it)) },
                        {
                            handleNetworkError(it)
                            dummyLiveData.postValue(Resource.error())
                        })
            )
        }
    }
}