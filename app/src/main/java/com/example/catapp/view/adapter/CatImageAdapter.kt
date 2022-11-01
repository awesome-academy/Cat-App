package com.example.catapp.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.catapp.data.model.responsemodel.Cat
import com.example.catapp.databinding.ItemImageLayoutBinding

class CatImageAdapter(val context: Context) : RecyclerView.Adapter<CatImageAdapter.ViewHolder>() {

    private val catList = mutableListOf<Cat>()

    inner class ViewHolder(private val binding: ItemImageLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bindData(data: Cat, position: Int) {
            Glide.with(context).load(data.url).into(binding.imageCat);
        }
    }

    fun setData(data: List<Cat>) {
        catList.clear()
        catList.addAll(data)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            ItemImageLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindData(catList[holder.adapterPosition], position)
    }

    override fun getItemCount(): Int {
        return catList.size
    }
}
