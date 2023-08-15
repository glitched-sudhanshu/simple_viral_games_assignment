package com.example.myapplication.ui.fragments

import com.example.myapplication.R
import com.example.myapplication.databinding.FragmentFirstBinding
import com.example.myapplication.viewmodels.DogsViewModel

class FirstFragment : BaseFragment<FragmentFirstBinding, DogsViewModel>() {

  override fun getFragmentView() = R.layout.fragment_first

//  override fun getViewModel(): Class<DogsViewModel> = DogsViewModel::class.java
}