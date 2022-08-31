package com.udacity.asteroidradar.main

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.udacity.asteroidradar.Asteroid
import com.udacity.asteroidradar.PictureOfDay
import com.udacity.asteroidradar.api.ApiMang
import com.udacity.asteroidradar.database.AsteroidDatabase
import com.udacity.asteroidradar.repository.Repo
import kotlinx.coroutines.launch

class MainViewModel(app: Application) : AndroidViewModel(app) {

    private val database = AsteroidDatabase.getInstance(app)
    private val repository = Repo(database)
    val asteroids = repository.asteroids

    var img = MutableLiveData<String>()

    private val _navToDetailFrag = MutableLiveData<Asteroid?>()
    val navToDetailFrag
        get() = _navToDetailFrag

    init {
        refreshAsteroids()
        getPictureOfDay()
    }


    fun onAsteroidItemClick(data: Asteroid) {
        _navToDetailFrag.value = data
    }

    fun onDetailFragmentNavigated() {
        _navToDetailFrag.value = null
    }

    private fun refreshAsteroids() {
        viewModelScope.launch {
            try {
                repository.refreshAsteroids()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        Log.i("MainViewModel","in my view")
    }

    private fun getPictureOfDay() {
        viewModelScope.launch {
            try {
//                val  pictureOfDay = ApiMang.getPictureOfDay().await()
                val  pictureOfDay = ApiMang.getPictureOfDay()
                Log.i("MainViewModel","img url : "+pictureOfDay.url)

                img.value = pictureOfDay.url


            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}