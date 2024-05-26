package com.example.foodapp

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class FoodItem(
    @Json(name = "id") val id: Int,
    @Json(name = "title") val title: String,
    @Json(name = "imageType") val imageType: String
)

@JsonClass(generateAdapter = true)
data class FoodSearchResponse(
    @Json(name = "products") val products: List<FoodItem>,
    @Json(name = "totalProducts") val totalProducts: Int,
    @Json(name = "type") val type: String,
    @Json(name = "offset") val offset: Int,
    @Json(name = "number") val number: Int
)

