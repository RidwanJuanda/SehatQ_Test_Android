package com.project.sehatq_test_android.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.project.sehatq_test_android.R
import com.project.sehatq_test_android.network.model.ProductPromo
import com.project.sehatq_test_android.ui.productdetail.ProductDetailActivity
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_product_list.view.*

class ProductAdapter(private val context: Context, private var dataArrayList: List<ProductPromo>) : RecyclerView.Adapter<ProductAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_product_list, parent, false))
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bindData(dataArrayList[position])
    }

    fun setProductList(list: List<ProductPromo>){
        this.dataArrayList = list
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return dataArrayList.size - 1
    }

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        @SuppressLint("SetTextI18n")
        fun bindData(data: ProductPromo) {
            itemView.tvProductTitle.text = data.title
            Picasso.get().load(data.imageUrl)
                .error(R.mipmap.ic_launcher_round)
                .placeholder(R.mipmap.ic_launcher_round).into(itemView.imgProduct)

            itemView.setOnClickListener {
                ProductDetailActivity.startActivity(context, dataArrayList[adapterPosition].title, dataArrayList[adapterPosition].description,
                    dataArrayList[adapterPosition].price, dataArrayList[adapterPosition].imageUrl)
            }

            itemView.imgLike.setOnClickListener {
                itemView.imgLike.visibility = View.GONE
                itemView.imgLiked.visibility = View.VISIBLE
            }

            itemView.imgLiked.setOnClickListener {
                itemView.imgLiked.visibility = View.GONE
                itemView.imgLike.visibility = View.VISIBLE
            }
        }
    }
}