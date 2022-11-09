package com.example.catapp.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.catapp.data.model.responsemodel.breeds.Rating
import com.example.catapp.databinding.ItemRatingLayoutBinding

class RatingAdapter : RecyclerView.Adapter<RatingAdapter.ViewHolder>() {

    private var ratingList = mutableListOf<Rating>()

    fun setData(list: MutableList<Rating>) {
        ratingList.clear()
        ratingList.addAll(list)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            ItemRatingLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindData(ratingList[holder.adapterPosition])
    }

    override fun getItemCount(): Int {
        return ratingList.size
    }

    inner class ViewHolder(private val binding: ItemRatingLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bindData(data: Rating) {
            binding.textRatingType.text = data.type
            binding.ratingBar.rating = data.rating.toFloat()
        }
    }
}
