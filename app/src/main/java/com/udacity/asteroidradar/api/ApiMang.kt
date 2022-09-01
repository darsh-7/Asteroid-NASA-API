package com.udacity.asteroidradar.api

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import com.udacity.asteroidradar.Asteroid
import com.udacity.asteroidradar.Constants
import org.json.JSONObject
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import com.udacity.asteroidradar.PictureOfDay
import com.udacity.asteroidradar.database.AsteroidEntity
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiModel {

    @GET("https://api.nasa.gov/neo/rest/v1/feed?start_date=START_DATE&end_date=END_DATE&api_key="+Constants.BASE_URL)
    suspend fun getAsteroids(): String

    @GET("planetary/apod")
    suspend fun getPictureOfDay(@Query("api_key") apiKey: String) : PictureOfDay
}

object ApiMang {

    private val moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()

    private val retrofit = Retrofit.Builder()
        .addConverterFactory(ScalarsConverterFactory.create())
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .baseUrl(Constants.BASE_URL)
        .build()

    private val RetroModel = retrofit.create(ApiModel::class.java)


suspend fun getAsteroids() : List<Asteroid> {
    val response = RetroModel.getAsteroids()
    val JsonObject = JSONObject(response)

    return parseAsteroidsJsonResult(JsonObject)
}

    suspend fun getPictureOfDay() = RetroModel.getPictureOfDay(Constants.API_KEY)
}

fun List<Asteroid>.asAsteroidEntities() : List<AsteroidEntity> {
    return map {
        AsteroidEntity(
            id = it.id,
            codename = it.codename,
            closeApproachDate = it.closeApproachDate,
            absoluteMagnitude = it.absoluteMagnitude,
            estimatedDiameter = it.estimatedDiameter,
            relativeVelocity = it.relativeVelocity,
            distanceFromEarth = it.distanceFromEarth,
            isPotentiallyHazardous = it.isPotentiallyHazardous
        )
    }
}
