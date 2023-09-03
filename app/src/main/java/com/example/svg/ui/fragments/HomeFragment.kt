package com.example.svg.ui.fragments

import android.os.Bundle
import android.view.View
import com.example.svg.R
import com.example.svg.databinding.FragmentHomeBinding
import com.example.svg.viewmodels.DogsViewModel

class HomeFragment : BaseFragment<FragmentHomeBinding, DogsViewModel>() {

  override fun getFragmentView() = R.layout.fragment_home
  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)

    binding.btnGenerateDogs.setOnClickListener{
      navigateTo(R.id.action_homeFragment_to_generateDogsFragment)
    }

    binding.btnRecentlyGeneratedDogs.setOnClickListener {
      navigateTo(R.id.action_homeFragment_to_recentlyGeneratedDogsFragment)
    }
  }
  //  override fun getViewModel(): Class<DogsViewModel> = DogsViewModel::class.java
}