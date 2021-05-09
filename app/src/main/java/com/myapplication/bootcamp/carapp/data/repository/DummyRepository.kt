package com.myapplication.bootcamp.carapp.data.repository

import com.myapplication.bootcamp.carapp.data.local.db.DatabaseService
import com.myapplication.bootcamp.carapp.data.model.Dummy
import com.myapplication.bootcamp.carapp.data.remote.NetworkService
import com.myapplication.bootcamp.carapp.data.remote.request.DummyRequest
import io.reactivex.Single
import javax.inject.Inject

class DummyRepository @Inject constructor(
    private val networkService: NetworkService,
    private val databaseService: DatabaseService
) {

    fun fetchDummy(id: String): Single<List<Dummy>> =
        networkService.doDummyCall(DummyRequest(id)).map { it.data }

}