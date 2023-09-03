package com.example.svg.ui.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.svg.application.MyApplication
import com.example.svg.databinding.ActivityMainBinding
import com.example.svg.viewmodels.DogsViewModel
import com.example.svg.viewmodels.DogsViewModelProviderFactory

class MainActivity : AppCompatActivity() {

  private lateinit var binding: ActivityMainBinding

  lateinit var viewModel: DogsViewModel

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    val viewModelProviderFactory = DogsViewModelProviderFactory((application as MyApplication).repository)
    viewModel = ViewModelProvider(this, viewModelProviderFactory)[DogsViewModel::class.java]

    binding = ActivityMainBinding.inflate(layoutInflater)
    setContentView(binding.root)

  }
}