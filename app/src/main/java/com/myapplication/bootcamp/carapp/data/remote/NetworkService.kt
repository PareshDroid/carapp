package com.myapplication.bootcamp.carapp.data.remote

import com.myapplication.bootcamp.carapp.data.remote.request.DummyRequest
import com.myapplication.bootcamp.carapp.data.remote.request.LoginRequest
import com.myapplication.bootcamp.carapp.data.remote.request.SignUpRequest
import com.myapplication.bootcamp.carapp.data.remote.response.DummyResponse
import com.myapplication.bootcamp.carapp.data.remote.response.LoginResponse
import com.myapplication.bootcamp.carapp.data.remote.response.UserProfileResponse
import com.myapplication.bootcamp.carapp.data.remote.response.VehicleResponse
import com.myapplication.bootcamp.carapp.utils.common.Constants
import io.reactivex.Single
import retrofit2.http.*
import javax.inject.Singleton

@Singleton
interface NetworkService {


    @POST(Endpoints.DUMMY)
    fun doDummyCall(
        @Body request: DummyRequest,
        @Header(Networking.HEADER_API_KEY) apiKey: String = Networking.API_KEY // default value set when Networking create is called
    ): Single<DummyResponse>

    /*
     * Example to add other headers
     *
     *  @POST(Endpoints.DUMMY_PROTECTED)
        fun sampleDummyProtectedCall(
            @Body request: DummyRequest,
            @Header(Networking.HEADER_USER_ID) userId: String, // pass using UserRepository
            @Header(Networking.HEADER_ACCESS_TOKEN) accessToken: String, // pass using UserRepository
            @Header(Networking.HEADER_API_KEY) apiKey: String = Networking.API_KEY // default value set when Networking create is called
        ): Single<DummyResponse>
     */

    @POST(Endpoints.LOGIN)
    fun doLoginCall(
        @Body request: LoginRequest,
        @Header(Networking.HEADER_CONTENT_TYPE) apiKey: String = Constants.CONTENT_TYPE_APPLICATION_JSON
    ): Single<LoginResponse>

    @POST(Endpoints.SIGN_UP)
    fun doSignUpCall(
        @Body request: SignUpRequest,
        @Header(Networking.HEADER_CONTENT_TYPE) apiKey: String = Constants.CONTENT_TYPE_APPLICATION_JSON
    ): Single<LoginResponse>

    @GET(Endpoints.FETCH_PROFILE)
    fun doFetchUserProfileCall(
        @Path("person_key") personKey:String,
        @Header(Networking.HEADER_AUTHORIZATION) authToken: String,
        @Header(Networking.HEADER_CONTENT_TYPE) apiKey: String = Constants.CONTENT_TYPE_APPLICATION_JSON
    ): Single<UserProfileResponse>

    @GET(Endpoints.FETCH_VEHICLES)
    fun doFetchVehicles(
        @Header(Networking.HEADER_AUTHORIZATION) authToken: String,
        @Header(Networking.HEADER_CONTENT_TYPE) apiKey: String = Constants.CONTENT_TYPE_APPLICATION_JSON
    ): Single<ArrayList<VehicleResponse>>
}