package com.example.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.ui.databinding.ActivitySearchBinding

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivitySearchBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}