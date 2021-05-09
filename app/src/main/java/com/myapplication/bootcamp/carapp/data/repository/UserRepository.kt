package com.myapplication.bootcamp.carapp.data.repository

import com.myapplication.bootcamp.carapp.data.local.db.DatabaseService
import com.myapplication.bootcamp.carapp.data.local.prefs.UserPreferences
import com.myapplication.bootcamp.carapp.data.model.Person
import com.myapplication.bootcamp.carapp.data.model.User
import com.myapplication.bootcamp.carapp.data.model.UserDetails
import com.myapplication.bootcamp.carapp.data.remote.NetworkService
import com.myapplication.bootcamp.carapp.data.remote.request.LoginRequest
import com.myapplication.bootcamp.carapp.data.remote.request.SignUpRequest
import com.myapplication.bootcamp.carapp.data.remote.response.VehicleResponse
import io.reactivex.Single
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserRepository @Inject constructor(
    private val networkService: NetworkService,
    private val databaseService: DatabaseService,
    private val userPreferences: UserPreferences
) {

    fun saveCurrentUser(person: Person) {
        userPreferences.setAuthKey(person.authToken)
        userPreferences.setPersonKey(person.key)
        userPreferences.setDisplayName(person.displayName)
    }

    fun removeCurrentUser() {
        userPreferences.removeAuthKey()
        userPreferences.removePersonKey()
        userPreferences.removeDisplayName()
    }

    fun getCurrentUser(): Person? {

        val authKey = userPreferences.getAuthKey()
        val personKey = userPreferences.getPersonKey()
        val displayName = userPreferences.getDisplayName()

        return if (authKey !== null && personKey != null && displayName != null)
            Person(authKey, personKey, displayName)
        else
            null
    }

    fun doUserLogin(email: String, password: String): Single<Person> =
        networkService.doLoginCall(LoginRequest(email, password))
            .map {
                Person(
                    it.authentication_token,
                    it.person.key,
                    it.person.display_name
                )
            }

    fun doUserSignUp(email: String, password: String,name:String): Single<Person> =
        networkService.doSignUpCall(SignUpRequest(name,email,password))
            .map {
                Person(
                    it.authentication_token,
                    it.person.key,
                    it.person.display_name
                )
            }

    fun doFetchUserProfile(personKey: String,authorization:String): Single<UserDetails> =
        networkService.doFetchUserProfileCall(personKey,authorization)
            .map {
                UserDetails(
                    it.created_at,
                    it.display_name,
                    it.email
                )
            }

    fun doFetchVehicles(authorization:String): Single<ArrayList<VehicleResponse>> =
        networkService.doFetchVehicles(authorization)
}