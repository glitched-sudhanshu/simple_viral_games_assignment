package com.example.svg.ui.fragments

import com.example.svg.R
import com.example.svg.databinding.FragmentSecondBinding
import com.example.svg.viewmodels.DogsViewModel

class SecondFragment : BaseFragment<FragmentSecondBinding, DogsViewModel>() {

  override fun getFragmentView() = R.layout.fragment_second

//  override fun getViewModel() = DogsViewModel::class.java
}