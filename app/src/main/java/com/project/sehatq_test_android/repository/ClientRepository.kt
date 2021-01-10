package com.project.sehatq_test_android.repository

import android.app.Application
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import com.google.gson.Gson
import com.project.sehatq_test_android.network.BASE_URL
import com.project.sehatq_test_android.network.ClientNetwork
import com.project.sehatq_test_android.network.model.Category
import com.project.sehatq_test_android.network.model.DataResponse
import com.project.sehatq_test_android.network.model.ProductPromo
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ClientRepository(val application: Application) {
    val showProgress = MutableLiveData<Boolean>()
    val categoryList = MutableLiveData<List<Category>>()
    val productList = MutableLiveData<List<ProductPromo>>()

    fun changeState() {
        showProgress.value = !(showProgress.value != null && showProgress.value!!)
    }

    fun getCategoryList() {
        showProgress.value = true
        val retrofit = Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).build()
        val service = retrofit.create(ClientNetwork::class.java)

        service.getDataList().enqueue(object : Callback<DataResponse>{
            override fun onFailure(call: Call<DataResponse>, t: Throwable) {
                showProgress.value = false
                Toast.makeText(application,"Error wile accessing the API", Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(call: Call<DataResponse>, response: Response<DataResponse>) {
                Log.e("RJ" , "category List Response : ${Gson().toJson(response.body()?.get(0)?.data?.category)}")
                categoryList.value = response.body()?.get(0)?.data?.category
                showProgress.value = false
            }
        })
    }

    fun getProductList() {
        showProgress.value = true
        val retrofit = Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).build()
        val service = retrofit.create(ClientNetwork::class.java)

        service.getDataList().enqueue(object : Callback<DataResponse>{
            override fun onFailure(call: Call<DataResponse>, t: Throwable) {
                showProgress.value = false
                Toast.makeText(application,"Error wile accessing the API", Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(call: Call<DataResponse>, response: Response<DataResponse>) {
                Log.e("RJ" , "product List Response : ${Gson().toJson(response.body()?.get(0)?.data?.productPromo)}")
                productList.value = response.body()?.get(0)?.data?.productPromo
                showProgress.value = false
            }
        })
    }
}