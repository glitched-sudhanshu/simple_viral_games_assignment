package com.example.svg.ui.fragments

import com.example.svg.R
import com.example.svg.databinding.FragmentHomeBinding
import com.example.svg.viewmodels.DogsViewModel

class HomeFragment : BaseFragment<FragmentHomeBinding, DogsViewModel>() {

  override fun getFragmentView() = R.layout.fragment_home

//  override fun getViewModel(): Class<DogsViewModel> = DogsViewModel::class.java
}