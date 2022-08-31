package com.udacity.asteroidradar.main

import com.udacity.asteroidradar.Asteroid

class AsteroidClickListener(val clickListener: (Asteroid) -> Unit) {
    fun onClick(data: Asteroid) = clickListener(data)
}