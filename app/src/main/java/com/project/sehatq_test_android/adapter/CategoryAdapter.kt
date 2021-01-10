package com.project.sehatq_test_android.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.project.sehatq_test_android.R
import com.project.sehatq_test_android.network.model.Category
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_category_list.view.*

class CategoryAdapter(private var dataArrayList: List<Category>) : RecyclerView.Adapter<CategoryAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_category_list, parent, false))
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bindData(dataArrayList[position])
    }

    fun setCategoryList(list: List<Category>){
        this.dataArrayList = list
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return dataArrayList.size
    }

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        @SuppressLint("SetTextI18n")
        fun bindData(data: Category) {
            itemView.tvCategoryName.text = data.name
            Picasso.get().load(data.imageUrl)
                .error(R.mipmap.ic_launcher_round)
                .placeholder(R.mipmap.ic_launcher_round).into(itemView.imgCategory)
        }
    }
}
