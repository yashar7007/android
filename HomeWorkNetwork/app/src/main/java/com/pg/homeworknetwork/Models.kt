package com.pg.homeworknetwork

import io.ktor.http.cio.*
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonArray
import kotlinx.serialization.json.JsonObject
import org.json.JSONArray
import org.json.JSONTokener

@Serializable
data class Movies (
    @SerialName("totalPages")
    val page: Int,
    @SerialName("items")
    val movies: MutableList<Movie>
)

@Serializable
data class Movie (
    @SerialName("kinopoiskId")
    val id: String,
    @SerialName("nameRu")
    val title: String?,
    @SerialName("posterUrl")
    val image: String,
    @SerialName("nameOriginal")
    val fullTitle: String?,
    @SerialName("genres")
    val genres: JsonArray,
    @SerialName("ratingImdb")
    val imDbRating: Double?,
    @SerialName("year")
    val year: String?
)