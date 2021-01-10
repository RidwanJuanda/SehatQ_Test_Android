package com.project.sehatq_test_android.network.model

class SearchProductListModel {
    private var titleProduct: String? = null
    private var priceProduct: String? = null
    private var imageProduct: String? = null

    fun getTitleProduct(): String {
        return titleProduct.toString()
    }

    fun setTitleProduct(titleNews: String) {
        this.titleProduct = titleNews
    }

    fun getPriceProduct(): String {
        return priceProduct.toString()
    }

    fun setPriceProduct(sourceNews: String) {
        this.priceProduct = sourceNews
    }

    fun getImgProduct(): String {
        return imageProduct.toString()
    }

    fun setImgProduct(imageProduct: String) {
        this.imageProduct = imageProduct
    }
}