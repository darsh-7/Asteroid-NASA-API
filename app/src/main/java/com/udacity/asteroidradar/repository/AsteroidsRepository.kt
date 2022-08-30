package com.udacity.asteroidradar.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.udacity.asteroidradar.Asteroid
import com.udacity.asteroidradar.api.ApiMang
import com.udacity.asteroidradar.api.asAsteroidEntities
import com.udacity.asteroidradar.database.AsteroidDatabase
import com.udacity.asteroidradar.database.asAsteroids
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class AsteroidsRepository(private val database: AsteroidDatabase) {

    val asteroids: LiveData<List<Asteroid>> =
        Transformations.map(database.dao.getAll()) {
            it.asAsteroids()
        }

    suspend fun refreshAsteroids() {
        withContext(Dispatchers.IO) {

            val asteroids = ApiMang.getAsteroids()

            database.dao.insertAll(asteroids.asAsteroidEntities())
        }
    }
}