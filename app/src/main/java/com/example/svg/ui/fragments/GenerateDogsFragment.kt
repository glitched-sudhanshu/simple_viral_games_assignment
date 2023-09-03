package com.example.svg.ui.fragments

import com.example.svg.R
import com.example.svg.databinding.FragmentGenerateDogsBinding
import com.example.svg.viewmodels.DogsViewModel

class GenerateDogsFragment : BaseFragment<FragmentGenerateDogsBinding, DogsViewModel>() {

  override fun getFragmentView() = R.layout.fragment_generate_dogs

//  override fun getViewModel() = DogsViewModel::class.java
}