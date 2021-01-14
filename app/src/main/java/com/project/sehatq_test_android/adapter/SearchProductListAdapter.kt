package com.project.sehatq_test_android.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.RecyclerView
import com.project.sehatq_test_android.R
import com.project.sehatq_test_android.network.model.SearchProductListModel
import com.project.sehatq_test_android.ui.productdetail.ProductDetailActivity
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_search_product_list.view.*
import kotlinx.android.synthetic.main.item_search_product_list.view.imgProduct
import java.util.*
import kotlin.collections.ArrayList

class SearchProductListAdapter(private val context: Context, private val dataList: ArrayList<SearchProductListModel>)
    : RecyclerView.Adapter<SearchProductListAdapter.MyViewHolder>(), Filterable {

    private var productListFiltered: List<SearchProductListModel> = dataList

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_search_product_list, parent, false))
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bindData(dataList[position])
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        @SuppressLint("SetTextI18n")
        fun bindData(data: SearchProductListModel) {
            itemView.tvTitleProduct.text = data.getTitleProduct()
            itemView.tvPriceProduct.text = data.getPriceProduct()
            Picasso.get().load(data.getImgProduct())
                .error(R.mipmap.ic_launcher_round)
                .placeholder(R.mipmap.ic_launcher_round).into(itemView.imgProduct)
            itemView.setOnClickListener {
                ProductDetailActivity.startActivity(context, dataList[adapterPosition].getTitleProduct(), "",
                    dataList[adapterPosition].getPriceProduct(), dataList[adapterPosition].getImgProduct())
            }
        }
    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(charSequence: CharSequence): FilterResults {
                val charString = charSequence.toString()
                productListFiltered = if (charString.isEmpty()) {
                    dataList
                } else {
                    val filteredList: ArrayList<SearchProductListModel> = ArrayList()
                    for (row in dataList) {
                        if (row.getTitleProduct().toLowerCase(Locale.ROOT)
                                .contains(charString.toLowerCase(Locale.ROOT)) || row.getTitleProduct()
                                .contains(charSequence)) {
                            filteredList.add(row)
                        }
                    }
                    filteredList
                }
                val filterResults = FilterResults()
                filterResults.values = productListFiltered
                return filterResults
            }

            override fun publishResults(charSequence: CharSequence?, filterResults: FilterResults) {
                productListFiltered = filterResults.values as ArrayList<SearchProductListModel>
                notifyDataSetChanged()
            }
        }
    }
}