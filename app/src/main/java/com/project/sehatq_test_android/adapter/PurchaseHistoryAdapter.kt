package com.project.sehatq_test_android.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.project.sehatq_test_android.R
import com.project.sehatq_test_android.adapter.PurchaseHistoryAdapter.viewHolder
import com.project.sehatq_test_android.ui.productdetail.PurchaseHistoryModel
import com.squareup.picasso.Picasso
import java.util.*

/**
 * Created by Ridwan Juanda on 1/10/21.
 */
class PurchaseHistoryAdapter(private var context: Context, private var arrayList: ArrayList<PurchaseHistoryModel>) : RecyclerView.Adapter<viewHolder>() {
    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): viewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_purchase_history_list, viewGroup, false)
        return viewHolder(view)
    }

    override fun onBindViewHolder(holder: viewHolder, position: Int) {
        holder.title.text = arrayList[position].title
        holder.price.text = arrayList[position].des
        if (arrayList[position].imageUrl.isNotEmpty()) {
            Picasso.get().load(arrayList[position].imageUrl)
                .error(R.mipmap.ic_launcher_round)
                .placeholder(R.mipmap.ic_launcher_round).into(holder.imgProduct)
        }
    }

    override fun getItemCount(): Int {
        return arrayList.size
    }

    inner class viewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var title: TextView = itemView.findViewById(R.id.tvTitleProduct)
        var price: TextView = itemView.findViewById(R.id.tvPriceProduct)
        var imgProduct: ImageView = itemView.findViewById(R.id.imgProduct)
    }
}