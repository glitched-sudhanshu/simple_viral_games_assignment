package com.example.myapplication.ui.fragments

import com.example.myapplication.R
import com.example.myapplication.databinding.FragmentSecondBinding
import com.example.myapplication.viewmodels.DogsViewModel

class SecondFragment : BaseFragment<FragmentSecondBinding, DogsViewModel>() {

  override fun getFragmentView() = R.layout.fragment_second

//  override fun getViewModel() = DogsViewModel::class.java
}