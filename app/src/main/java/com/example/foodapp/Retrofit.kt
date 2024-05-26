import com.example.foodapp.FoodSearchResponse
import com.example.foodapp.FoodInformationResponse
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

object RetrofitClient {
    private const val BASE_URL = "https://api.spoonacular.com/"

    private val moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()

    val api: SpoonacularApi by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
            .create(SpoonacularApi::class.java)
    }
}

interface SpoonacularApi {
    @GET("food/products/search")
    suspend fun searchFood(@Query("query") query: String,
                           @Query("apiKey") apiKey: String): FoodSearchResponse

    @GET("food/products/{id}")
    suspend fun getNutrition(
        @Path("id") id: Int,
        @Query("apiKey") apiKey: String,
        @Query("amount") amount: Int = 1,
        @Query("shoppingListUnits") shoppingListUnits: Int = 1
        ): FoodInformationResponse
}
