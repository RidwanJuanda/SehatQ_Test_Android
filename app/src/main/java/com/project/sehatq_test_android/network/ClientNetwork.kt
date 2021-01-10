package com.project.sehatq_test_android.network

import com.project.sehatq_test_android.network.model.DataResponse
import retrofit2.Call
import retrofit2.http.GET

/**
 * Created by Ridwan Juanda on 1/9/21.
 */

const val BASE_URL = "https://private-4639ce-ecommerce56.apiary-mock.com/"

interface ClientNetwork {

    @GET("home")
    fun getDataList(): Call<DataResponse>

//    @GET("{woeid}")
//    fun getWeather(@Path("woeid") woeid: Int): Call<WeatherResponse>

}