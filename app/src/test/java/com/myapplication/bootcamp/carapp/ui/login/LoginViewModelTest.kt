package com.myapplication.bootcamp.carapp.ui.login

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.myapplication.bootcamp.carapp.R
import com.myapplication.bootcamp.carapp.data.model.User
import com.myapplication.bootcamp.carapp.data.repository.UserRepository
import com.myapplication.bootcamp.carapp.utils.common.Event
import com.myapplication.bootcamp.carapp.utils.common.Resource
import com.myapplication.bootcamp.carapp.utils.network.NetworkHelper
import com.myapplication.bootcamp.carapp.utils.rx.TestSchedulerProvider
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.TestScheduler
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.doReturn
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class LoginViewModelTest {

    @Test
    fun givenServerResponse200_whenLogin_shouldLaunchDummyActivity() {

    }



}