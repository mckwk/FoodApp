package com.example.foodapp

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.squareup.picasso.Picasso
import kotlinx.coroutines.launch

class NutritionDetailActivity : AppCompatActivity() {
    private lateinit var foodNameTextView: TextView
    private lateinit var caloriesTextView: TextView
    private lateinit var fatTextView: TextView
    private lateinit var proteinTextView: TextView
    private lateinit var carbsTextView: TextView
    private lateinit var ingredientsTextView: TextView

    private val apiKey = "336e4fb816ae44edb4d438e30596a403"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_nutrition_detail)

        foodNameTextView = findViewById(R.id.foodNameTextView)
        caloriesTextView = findViewById(R.id.caloriesTextView)
        fatTextView = findViewById(R.id.fatTextView)
        proteinTextView = findViewById(R.id.proteinTextView)
        carbsTextView = findViewById(R.id.carbsTextView)
        ingredientsTextView = findViewById(R.id.ingredientsTextView)

        val foodItemId = intent.getIntExtra("FOOD_ITEM_ID", -1)
        if (foodItemId != -1) {
            fetchNutritionData(foodItemId)
        }
    }

    private fun fetchNutritionData(foodItemId: Int) {
        lifecycleScope.launch {
            try {
                val nutritionResponse = RetrofitClient.api.getNutrition(foodItemId, apiKey)
                val nutrition = nutritionResponse.nutrition

                val foodName = nutritionResponse.title
                val caloriesNutrient = nutrition.nutrients.find { it.name.equals("Calories", ignoreCase = true) }
                val fatNutrient = nutrition.nutrients.find { it.name.equals("Fat", ignoreCase = true) }
                val proteinNutrient = nutrition.nutrients.find { it.name.equals("Protein", ignoreCase = true) }
                val carbsNutrient = nutrition.nutrients.find { it.name.equals("Carbohydrates", ignoreCase = true) }
                val ingredients = nutritionResponse.ingredients.joinToString(", ") { it.name }

                foodNameTextView.text = foodName
                ingredientsTextView.text = "Ingredients: " + ingredients

                caloriesNutrient?.let {
                    caloriesTextView.text = "Calories: ${it.amount} ${it.unit}"
                }
                fatNutrient?.let {
                    fatTextView.text = "Fat: ${it.amount} ${it.unit}"
                }
                proteinNutrient?.let {
                    proteinTextView.text = "Protein: ${it.amount} ${it.unit}"
                }
                carbsNutrient?.let {
                    carbsTextView.text = "Carbs: ${it.amount} ${it.unit}"
                }

            } catch (e: Exception) {
                caloriesTextView.text = "Error: ${e.message}"
                e.printStackTrace()
            }
        }
    }
}
