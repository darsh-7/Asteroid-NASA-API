package com.udacity.asteroidradar.api

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import com.udacity.asteroidradar.Asteroid
import com.udacity.asteroidradar.Constants
import org.json.JSONObject
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory

object ApiMang {

    private val moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()

    private val retrofit = Retrofit.Builder()
        .addConverterFactory(ScalarsConverterFactory.create())
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .baseUrl(Constants.BASE_URL)
        .build()

    private val RetroModel : ApiModel by lazy {
        retrofit.create(ApiModel::class.java)
    }

suspend fun getAsteroids() : List<Asteroid> {
    val responseStr = RetroModel.getAsteroids("","", Constants.API_KEY)
    val responseJsonObject = JSONObject(responseStr)

    return parseAsteroidsJsonResult(responseJsonObject)
}

    suspend fun getPictureOfDay() = RetroModel.getPictureOfDay(Constants.API_KEY)
}