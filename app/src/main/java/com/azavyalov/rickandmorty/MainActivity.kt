package com.azavyalov.rickandmorty

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.azavyalov.rickandmorty.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}