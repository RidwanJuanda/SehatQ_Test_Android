package com.project.sehatq_test_android.ui.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.project.sehatq_test_android.R
import com.project.sehatq_test_android.adapter.PurchaseHistoryAdapter
import com.project.sehatq_test_android.db.DatabaseHelper
import com.project.sehatq_test_android.ui.productdetail.PurchaseHistoryModel
import kotlinx.android.synthetic.main.fragment_profile.view.*

@Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
class ProfileFragment : Fragment() {
    private lateinit var rootView: View
    private var arrayList: ArrayList<PurchaseHistoryModel>? = null
    private var databaseHelper: DatabaseHelper? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        rootView = inflater.inflate(R.layout.fragment_profile, container, false)
        databaseHelper = DatabaseHelper(requireContext())
        displayPurchaseHistory()
        return rootView
    }

    //display notes list
    private fun displayPurchaseHistory() {
        arrayList = ArrayList(databaseHelper?.purchaseHistory)
        rootView.rvPurchaseHistory.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        rootView.rvPurchaseHistory.itemAnimator = DefaultItemAnimator()
        val adapter = PurchaseHistoryAdapter(requireContext(), arrayList!!)
        rootView.rvPurchaseHistory.adapter = adapter
    }
}