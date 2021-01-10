package com.project.sehatq_test_android.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.project.sehatq_test_android.R
import com.project.sehatq_test_android.adapter.CategoryAdapter
import com.project.sehatq_test_android.adapter.ProductAdapter
import com.project.sehatq_test_android.network.model.Category
import com.project.sehatq_test_android.network.model.ProductPromo
import com.project.sehatq_test_android.viewModel.CategoryViewModel
import kotlinx.android.synthetic.main.app_bar_home.view.*
import kotlinx.android.synthetic.main.fragment_home.view.*

class HomeFragment : Fragment(), View.OnClickListener {
    private lateinit var rootView: View
    private lateinit var viewModel: CategoryViewModel
    private lateinit var categoryAdapter: CategoryAdapter
    private lateinit var productAdapter: ProductAdapter
    private var categoryList: MutableList<Category> = mutableListOf()
    private var productList: MutableList<ProductPromo> = mutableListOf()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        rootView = inflater.inflate(R.layout.fragment_home, container, false)
        viewModel = ViewModelProvider(this).get(CategoryViewModel::class.java)

        viewModel.getCategoryList()
        viewModel.getProductList()

        categoryAdapter = CategoryAdapter(categoryList)
        rootView.rvCategory?.adapter = categoryAdapter
        rootView.rvCategory?.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)

        productAdapter = ProductAdapter(requireContext(), productList)
        rootView.rvProduct?.adapter = productAdapter
        rootView.rvProduct?.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)

        viewModel.showProgress.observe(requireActivity(), {
            if (it)
                rootView.progressDialog.visibility = View.VISIBLE
            else
                rootView.progressDialog.visibility = View.GONE
        })

        viewModel.categoryList.observe(requireActivity(), {
            categoryAdapter.setCategoryList(it)
        })

        viewModel.productList.observe(requireActivity(), {
            productAdapter.setProductList(it)
        })

        rootView.tvTitleToolbar.setOnClickListener(this)

        return rootView
    }

    override fun onClick(v: View?) {
        when(v) {
            rootView.tvTitleToolbar -> SearchProductActivity.startActivity(requireContext())
        }
    }
}