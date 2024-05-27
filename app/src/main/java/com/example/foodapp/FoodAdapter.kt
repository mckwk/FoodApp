package com.example.foodapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class FoodAdapter(private val onItemClick: (FoodItem) -> Unit) :
    RecyclerView.Adapter<FoodAdapter.FoodViewHolder>() {

    private val foodItems = mutableListOf<FoodItem>()

    fun submitList(items: List<FoodItem>) {
        foodItems.clear()
        foodItems.addAll(items)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FoodViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.food_item, parent, false)
        return FoodViewHolder(view)
    }

    override fun onBindViewHolder(holder: FoodViewHolder, position: Int) {
        val foodItem = foodItems[position]
        holder.bind(foodItem)
        holder.itemView.setOnClickListener {
            onItemClick(foodItem)
        }
    }

    override fun getItemCount(): Int = foodItems.size

    class FoodViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val foodNameTextView: TextView = itemView.findViewById(R.id.foodNameTextView)

        fun bind(foodItem: FoodItem) {
            foodNameTextView.text = foodItem.title
        }
    }
}
