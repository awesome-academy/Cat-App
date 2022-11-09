package com.example.catapp.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.catapp.data.model.responsemodel.favourite.FavouriteItem
import com.example.catapp.databinding.ItemImageLayoutBinding
import com.example.catapp.utils.loadImage

class FavImageAdapter(private val onClickItem: (FavouriteItem) -> Unit) : RecyclerView.Adapter<FavImageAdapter.ViewHolder>() {

    private val favList = mutableListOf<FavouriteItem>()

    fun setData(data: List<FavouriteItem>) {
        favList.clear()
        favList.addAll(data)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            ItemImageLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindData(favList[holder.adapterPosition])
    }

    override fun getItemCount(): Int {
        return favList.size
    }

    inner class ViewHolder(private val binding: ItemImageLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bindData(data: FavouriteItem) {
            val context = binding.root.context
            context.loadImage(data.image.url, binding.imageCat, false)
            binding.root.setOnClickListener { onClickItem(data) }
        }
    }
}
