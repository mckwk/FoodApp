package com.example.foodapp
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class FoodInformationResponse(
    @Json(name = "id") val id: Int,
    @Json(name = "title") val title: String,
    @Json(name = "breadcrumbs") val breadcrumbs: List<String>,
    //@Json(name = "imageType") val imageType: String,
    //@Json(name = "badges") val badges: List<String>,
    //@Json(name = "importantBadges") val importantBadges: List<String>,
    @Json(name = "ingredientCount") val ingredientCount: Int,
    //@Json(name = "generatedText") val generatedText: String?, // Nullable
    @Json(name = "ingredientList") val ingredientList: String,
    @Json(name = "ingredients") val ingredients: List<Ingredient>,
    //@Json(name = "likes") val likes: Int,
    //@Json(name = "aisle") val aisle: String,
    @Json(name = "nutrition") val nutrition: Nutrition,
    //@Json(name = "price") val price: Double,
    //@Json(name = "servings") val servings: Servings,
    //@Json(name = "spoonacularScore") val spoonacularScore: Double
)

@JsonClass(generateAdapter = true)
data class Ingredient(
    @Json(name = "description") val description: String?,
    @Json(name = "name") val name: String,
    @Json(name = "safety_level") val safetyLevel: String? // Nullable
)

@JsonClass(generateAdapter = true)
data class Nutrition(
    @Json(name = "nutrients") val nutrients: List<Nutrient>,
    @Json(name = "caloricBreakdown") val caloricBreakdown: CaloricBreakdown
)

@JsonClass(generateAdapter = true)
data class Nutrient(
    @Json(name = "name") val name: String,
    @Json(name = "amount") val amount: Double,
    @Json(name = "unit") val unit: String,
    @Json(name = "percentOfDailyNeeds") val percentOfDailyNeeds: Double
)

@JsonClass(generateAdapter = true)
data class CaloricBreakdown(
    @Json(name = "percentProtein") val percentProtein: Double,
    @Json(name = "percentFat") val percentFat: Double,
    @Json(name = "percentCarbs") val percentCarbs: Double
)

//@JsonClass(generateAdapter = true)
//data class Servings(
//    @Json(name = "number") val number: Int,
//    @Json(name = "size") val size: Double,
//    @Json(name = "unit") val unit: String,
//    @Json(name = "raw") val raw: String
//)
