package com.example.myapplication.ui.activities

import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat
import androidx.lifecycle.ViewModelProvider
import com.example.myapplication.R
import com.example.myapplication.application.MyApplication
import com.example.myapplication.databinding.ActivityMainBinding
import com.example.myapplication.viewmodels.DogsViewModel
import com.example.myapplication.viewmodels.DogsViewModelProviderFactory

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