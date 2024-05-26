package com.example.foodapp
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.launch
import android.content.Context
import android.view.inputmethod.InputMethodManager


class MainActivity : AppCompatActivity() {
    private lateinit var searchEditText: EditText
    private lateinit var searchButton: Button
    private lateinit var foodRecyclerView: RecyclerView
    private lateinit var foodAdapter: FoodAdapter

    private val apiKey = "336e4fb816ae44edb4d438e30596a403"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        searchEditText = findViewById(R.id.searchEditText)
        searchButton = findViewById(R.id.searchButton)
        foodRecyclerView = findViewById(R.id.foodRecyclerView)

        foodAdapter = FoodAdapter { foodItem -> showNutritionInfo(foodItem) }
        foodRecyclerView.layoutManager = LinearLayoutManager(this)
        foodRecyclerView.adapter = foodAdapter

        searchButton.setOnClickListener {
            val query = searchEditText.text.toString()
            if (query.isNotEmpty()) {
                hideKeyboard()
                searchFood(query)
            } else {
                Toast.makeText(this, "Please enter a food name", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun searchFood(query: String) {
        lifecycleScope.launch {
            try {
                val response = RetrofitClient.api.searchFood(query, apiKey)
                foodAdapter.submitList(response.products)
            } catch (e: Exception) {
                Toast.makeText(this@MainActivity, "Error: ${e.message}", Toast.LENGTH_SHORT).show()
            }

        }
    }

    private fun showNutritionInfo(foodItem: FoodItem) {
        lifecycleScope.launch {
            try {
                val nutrition = RetrofitClient.api.getNutrition(foodItem.id, apiKey)
                // Extracting calories
                val caloriesNutrient = nutrition.nutrition.nutrients.find { it.name.equals("Calories", ignoreCase = true) }
                if (caloriesNutrient != null) {
                    // Displaying calories
                    Toast.makeText(this@MainActivity, "Calories: ${caloriesNutrient.amount} ${caloriesNutrient.unit}", Toast.LENGTH_LONG).show()
                } else {
                    Toast.makeText(this@MainActivity, "Calories information not available", Toast.LENGTH_SHORT).show()
                }
            } catch (e: Exception) {
                Toast.makeText(this@MainActivity, "Error: ${e.message}", Toast.LENGTH_SHORT).show()
            }
        }
    }
    private fun hideKeyboard() {
        val inputMethodManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(currentFocus?.windowToken, 0)
    }


}
