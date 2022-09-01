package com.udacity.asteroidradar.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.udacity.asteroidradar.Asteroid
import com.udacity.asteroidradar.databinding.NewAsteroidBinding

class AsteroidViewHolder private constructor(private val binding: NewAsteroidBinding)
    : RecyclerView.ViewHolder(binding.root) {


    fun bind(item: Asteroid, clickListener: AsteroidClickListener) {
        binding.asteroid = item
        binding.clickListener = clickListener
        binding.executePendingBindings()
    }

    companion object {
        fun create(parent: ViewGroup) : AsteroidViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val binding = NewAsteroidBinding.inflate(layoutInflater, parent, false)
            return AsteroidViewHolder(binding)
        }
    }
}