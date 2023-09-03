package com.example.svg.ui.fragments

import androidx.fragment.app.Fragment
import com.example.svg.R
import com.example.svg.databinding.FragmentRecentlyGeneratedDogsBinding
import com.example.svg.viewmodels.DogsViewModel


class RecentlyGeneratedDogsFragment : BaseFragment<FragmentRecentlyGeneratedDogsBinding, DogsViewModel>() {

  override fun getFragmentView() = R.layout.fragment_recently_generated_dogs

//  override fun getViewModel() = DogsViewModel::class.java
}