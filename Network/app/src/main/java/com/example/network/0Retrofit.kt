package com.example.network

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

fun main() {
    val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl("https://api.themoviedb.org/")
        .build()

    val api: RetrofitApi = retrofit.create(RetrofitApi::class.java)

 //   val response: Call<ResponseBody> = api.getMovie(550)
//    val response: Call<ResponseBody> = api.getMovie(version = 3)
//    val response: Call<ResponseBody> = api.getMovie(version = 3, id = 550)
//    val response: Call<ResponseBody> = api.getMovie(version = 3, id = 550, key = BuildConfig.API_KEY)
    val response: Call<ResponseBody> = api.searchMovie("spider")

    response.enqueue(object : Callback<ResponseBody> {
        override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
            print(
                "RESPONSE --> ${response.body()?.string()}"
            )
        }

        override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
            print("ERROR --> $t")
        }
    })
}
const val API_KEY= "d33a5213f2b97a4a90d910d493bbb1d1"

interface RetrofitApi {
    @GET("3/movie/550?api_key=${API_KEY}")
    fun getMovie(): Call<ResponseBody>

    @GET("3/movie/{id}?api_key=${API_KEY}")
    fun getMovie(@Path("id") id: Int): Call<ResponseBody>

    @GET("{version}/movie/{id}?api_key=${API_KEY}")
    fun getMovie(@Path("version") version: Int, @Path("id") id: Int): Call<ResponseBody>

    @GET("{version}/movie/{id}")
    fun getMovie(
        @Path("version") version: Int,
        @Path("id") id: Int,
        @Query("api_key") key: String
    ): Call<ResponseBody>

    @GET("{version}/search/movie?api_key=${API_KEY}&page=1&include_adult=false")
    fun searchMovie(@Query("query") searchString: String): Call<ResponseBody>
}