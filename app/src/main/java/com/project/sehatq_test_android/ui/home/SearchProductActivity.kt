
package com.project.sehatq_test_android.ui.home

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.widget.doOnTextChanged
import androidx.recyclerview.widget.LinearLayoutManager
import com.project.sehatq_test_android.R
import com.project.sehatq_test_android.adapter.SearchProductListAdapter
import com.project.sehatq_test_android.network.model.SearchProductListModel
import kotlinx.android.synthetic.main.activity_search_product.*
import kotlinx.android.synthetic.main.app_bar_detail.*
import java.util.*
import kotlin.arrayOf

class SearchProductActivity : AppCompatActivity() {
    private var productList: ArrayList<SearchProductListModel>? = null
    private var productAdapter: SearchProductListAdapter? = null

    private val imgProductList = arrayOf(
        "https://source.unsplash.com/Xq1ntWruZQI/600x800",
        "https://source.unsplash.com/HN-5Z6AmxrM/600x800",
        "https://source.unsplash.com/buF62ewDLcQ/600x800",
        "https://source.unsplash.com/THozNzxEP3g/600x800",
        "https://source.unsplash.com/USrZRcRS2Lw/600x800",
        "https://source.unsplash.com/PeFk7fzxTdk/600x800",
        "https://source.unsplash.com/LrMWHKqilUw/600x800",
        "https://source.unsplash.com/HN-5Z6AmxrM/600x800",
        "https://source.unsplash.com/CdVAUADdqEc/600x800",
        "https://source.unsplash.com/AWh9C-QjhE4/600x800",
        "https://source.unsplash.com/Xq1ntWruZQI/600x800",
        "https://source.unsplash.com/HN-5Z6AmxrM/600x800",
        "https://source.unsplash.com/buF62ewDLcQ/600x800",
        "https://source.unsplash.com/THozNzxEP3g/600x800"
    )

    private val titleProductList = arrayOf(
        "Macbook Pro",
        "Iphone 7",
        "Iphone 7 Plus",
        "Iphone 8",
        "Iphone 8 Plus",
        "Macbook Pro Retina",
        "Macbook Air",
        "Monitor 32 inch",
        "Samsung Note",
        "Samsung Galaxy",
        "Samsung s8+",
        "Lenovo",
        "Asus",
        "Laptopm HP"
    )

    private val priceProductList = arrayOf(
        "Rp. 20.000.000",
        "Rp. 20.000.000",
        "Rp. 20.000.000",
        "Rp. 20.000.000",
        "Rp. 20.000.000",
        "Rp. 20.000.000",
        "Rp. 20.000.000",
        "Rp. 20.000.000",
        "Rp. 20.000.000",
        "Rp. 20.000.000",
        "Rp. 20.000.000",
        "Rp. 20.000.000",
        "Rp. 20.000.000",
        "Rp. 20.000.000"
    )

    companion object {
        fun startActivity(context: Context) {
            context.startActivity(Intent(context, SearchProductActivity::class.java))
        }
    }

//    private val search: String
//        get() = edtSearch.text.toString()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_product)
        initToolbar()
        productList = populateListProduct()

        productAdapter = SearchProductListAdapter(this, productList!!)
//        productAdapter?.searchProductAdapter(productList!!)
        rvSearchProduct?.adapter = productAdapter
        rvSearchProduct?.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        edtSearch.doOnTextChanged { text, _, _, _ ->
            productAdapter?.filter?.filter(text)
        }

    }

    private fun populateListProduct(): ArrayList<SearchProductListModel> {
        val list = ArrayList<SearchProductListModel>()
        for (i in imgProductList.indices) {
            val imageModel = SearchProductListModel()
            imageModel.setImgProduct(imgProductList[i])
            imageModel.setTitleProduct(titleProductList[i])
            imageModel.setPriceProduct(priceProductList[i])
            list.add(imageModel)
        }

        return list
    }

    private fun initToolbar() {
        setSupportActionBar(toolbar)
        val backArrow = ContextCompat.getDrawable(this, R.drawable.ic_back_black)
        supportActionBar?.setHomeAsUpIndicator(backArrow)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.setDisplayShowTitleEnabled(false)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}