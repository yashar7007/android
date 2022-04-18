package com.pg.homeworknetwork

import io.ktor.client.*
import io.ktor.client.engine.android.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import io.ktor.client.request.*
import kotlinx.serialization.json.Json as serialJson

class Api {
    private val client = HttpClient(Android){
        install(JsonFeature) {
            serializer = KotlinxSerializer(serialJson {
                prettyPrint = true
                isLenient = true
                ignoreUnknownKeys = true
            })
        }
    }

    suspend fun getListMovies(): Movies = client.get(
        host = BuildConfig.API_BASE_URL,
    ) {
        header("X-API-KEY", BuildConfig.API_KEY)
        parameter("page", 2)
    }



    suspend fun getMovieById(id: String): Movie = client.get(
        host = BuildConfig.API_BASE_URL,
        path = id
    ) {
        header("accept","application/json")
        header("X-API-KEY", BuildConfig.API_KEY)
    }
}