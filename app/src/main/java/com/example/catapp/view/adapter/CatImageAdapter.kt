package com.example.catapp.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.catapp.data.model.responsemodel.Cat
import com.example.catapp.databinding.ItemImageLayoutBinding
import com.example.catapp.utils.loadImage

class CatImageAdapter(private val onClickItem: (Cat) -> Unit) : RecyclerView.Adapter<CatImageAdapter.ViewHolder>() {

    private val catList = mutableListOf<Cat>()

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
        holder.bindData(catList[holder.adapterPosition])
    }

    override fun getItemCount(): Int {
        return catList.size
    }

    inner class ViewHolder(private val binding: ItemImageLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bindData(data: Cat) {
            val context = binding.root.context
            context.loadImage(data.url, binding.imageCat, false)
            binding.root.setOnClickListener { onClickItem(data) }
        }
    }
}
