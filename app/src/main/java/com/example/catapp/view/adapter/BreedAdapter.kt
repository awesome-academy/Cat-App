package com.example.catapp.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.catapp.data.model.responsemodel.breeds.BreedItem
import com.example.catapp.databinding.ItemBreedLayoutBinding
import com.example.catapp.utils.loadImage

class BreedAdapter(private val onClickItem: (BreedItem) -> Unit) :
    RecyclerView.Adapter<BreedAdapter.ViewHolder>() {

    private val breedList = mutableListOf<BreedItem>()

    fun setData(data: List<BreedItem>) {
        breedList.clear()
        breedList.addAll(data)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            ItemBreedLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindData(breedList[holder.adapterPosition])
    }

    override fun getItemCount(): Int {
        return breedList.size
    }

    inner class ViewHolder(private val binding: ItemBreedLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bindData(data: BreedItem) {
            val context = binding.root.context
            context.loadImage(data.image.url, binding.imgSample, true)
            binding.textBreedName.text = data.name
            binding.textBreedOriginContent.text = data.origin
            binding.textBreedDetailContent.text = data.description
            binding.root.setOnClickListener { onClickItem(data) }
        }
    }
}
