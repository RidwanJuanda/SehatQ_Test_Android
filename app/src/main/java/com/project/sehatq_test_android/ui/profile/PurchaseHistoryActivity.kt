package com.project.sehatq_test_android.ui.profile

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.project.sehatq_test_android.R
import com.project.sehatq_test_android.adapter.PurchaseHistoryAdapter
import com.project.sehatq_test_android.db.DatabaseHelper
import com.project.sehatq_test_android.ui.productdetail.PurchaseHistoryModel
import kotlinx.android.synthetic.main.activity_purchase_history.*
import kotlinx.android.synthetic.main.app_bar_detail.*

@Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
class PurchaseHistoryActivity : AppCompatActivity() {
    private var arrayList: ArrayList<PurchaseHistoryModel>? = null
    private var databaseHelper: DatabaseHelper? = null

    companion object {
        fun startActivity(context: Context) {
            context.startActivity(Intent(context, PurchaseHistoryActivity::class.java))
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_purchase_history)
        initToolbar()
        databaseHelper = DatabaseHelper(this)
        displayPurchaseHistory()
    }

    //display notes list
    private fun displayPurchaseHistory() {
        arrayList = ArrayList(databaseHelper?.purchaseHistory)
        rvPurchaseHistory.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        rvPurchaseHistory.itemAnimator = DefaultItemAnimator()
        val adapter = PurchaseHistoryAdapter(this, arrayList!!)
        rvPurchaseHistory.adapter = adapter
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