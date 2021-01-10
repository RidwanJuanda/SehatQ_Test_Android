package com.project.sehatq_test_android.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.project.sehatq_test_android.network.model.Category
import com.project.sehatq_test_android.network.model.ProductPromo
import com.project.sehatq_test_android.repository.ClientRepository

class CategoryViewModel(application: Application) : AndroidViewModel(application) {
    val repository  = ClientRepository(application)
    val showProgress : LiveData<Boolean>
    val categoryList : LiveData<List<Category>>
    val productList : LiveData<List<ProductPromo>>

    init {
        this.showProgress = repository.showProgress
        this.categoryList = repository.categoryList
        this.productList = repository.productList
    }

    fun changeState(){
        repository.changeState()
    }

    fun getCategoryList(){
        repository.getCategoryList()
    }

    fun getProductList(){
        repository.getProductList()
    }
}