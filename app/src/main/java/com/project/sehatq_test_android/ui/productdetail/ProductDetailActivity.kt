package com.project.sehatq_test_android.ui.productdetail

import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.project.sehatq_test_android.R
import com.project.sehatq_test_android.db.DatabaseHelper
import com.project.sehatq_test_android.ui.profile.PurchaseHistoryActivity
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_product_detail.*

class ProductDetailActivity : AppCompatActivity(), View.OnClickListener {
    private var databaseHelper: DatabaseHelper? = null
    private var title: String = ""
    private var desc: String = ""
    private var price: String = ""
    private var image: String = ""
    companion object {
        private const val TITLE = "title"
        private const val DESC = "desc"
        private const val PRICE = "price"
        private const val IMAGE = "image"

        fun startActivity(context: Context, productTitle: String, productDescription: String, productPrice: String, productImage: String) {
            Intent(context, ProductDetailActivity::class.java).apply {
                putExtra(TITLE, productTitle)
                putExtra(DESC, productDescription)
                putExtra(PRICE, productPrice)
                putExtra(IMAGE, productImage)
                context.startActivity(this)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_detail)
        btnBuy.setOnClickListener(this)
        imgLike.setOnClickListener(this)
        imgLiked.setOnClickListener(this)
        imgBack.setOnClickListener(this)
        imgShare.setOnClickListener(this)
        databaseHelper = DatabaseHelper(this)

        title = intent.getStringExtra(TITLE) ?: ""
        desc = intent.getStringExtra(DESC) ?: ""
        price = intent.getStringExtra(PRICE) ?: ""
        tvProductTitle.text = title
        tvProductDesc.text = desc
        tvProductPrice.text = price
        image = intent.getStringExtra(IMAGE) ?: ""
        if (image.isNotEmpty()) {
            Picasso.get().load(image)
                .error(R.mipmap.ic_launcher)
                .placeholder(R.mipmap.ic_launcher).into(imgProduct)
        }
    }

    @RequiresApi(Build.VERSION_CODES.P)
    override fun onClick(v: View?) {
        when(v) {
            imgBack -> finish()
            imgShare -> {
                val sendIntent = Intent().apply {
                    action = Intent.ACTION_SEND
                    putExtra(Intent.EXTRA_TEXT, image)
                    putExtra(Intent.EXTRA_TITLE, title)
                    type = "text/plain"
                }
                val shareIntent = Intent.createChooser(sendIntent, null)
                startActivity(shareIntent)
            }
            btnBuy -> {
               databaseHelper?.addPurchaseHistory(title, price, image)
                PurchaseHistoryActivity.startActivity(this)
            }

            imgLike -> {
                imgLike.visibility = View.GONE
                imgLiked.visibility = View.VISIBLE
            }
            imgLiked -> {
                imgLiked.visibility = View.GONE
                imgLike.visibility = View.VISIBLE
            }
        }
    }
}