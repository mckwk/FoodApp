package com.example.foodapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso

class FoodAdapter(private val onItemClick: (FoodItem) -> Unit) : RecyclerView.Adapter<FoodAdapter.FoodViewHolder>() {
    private val foodList = mutableListOf<FoodItem>()

    fun submitList(list: List<FoodItem>) {
        foodList.clear()
        foodList.addAll(list)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FoodViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.food_list_item, parent, false)
        return FoodViewHolder(view)
    }

    override fun onBindViewHolder(holder: FoodViewHolder, position: Int) {
        holder.bind(foodList[position])
    }

    override fun getItemCount() = foodList.size

    inner class FoodViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val foodImageView: ImageView = itemView.findViewById(R.id.foodImageView)
        private val foodTitleTextView: TextView = itemView.findViewById(R.id.foodTitleTextView)

        fun bind(foodItem: FoodItem) {
            foodTitleTextView.text = foodItem.title
            Picasso.get().load(foodItem.title+'.'+foodItem.imageType).into(foodImageView)
            itemView.setOnClickListener { onItemClick(foodItem) }
        }
    }
}
