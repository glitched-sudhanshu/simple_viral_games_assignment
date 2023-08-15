package com.example.svg.ui.fragments

import com.example.svg.R
import com.example.svg.databinding.FragmentFirstBinding
import com.example.svg.viewmodels.DogsViewModel

class FirstFragment : BaseFragment<FragmentFirstBinding, DogsViewModel>() {

  override fun getFragmentView() = R.layout.fragment_first

//  override fun getViewModel(): Class<DogsViewModel> = DogsViewModel::class.java
}