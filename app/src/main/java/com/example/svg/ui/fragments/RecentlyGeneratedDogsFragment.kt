package com.example.svg.ui.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.svg.R
import com.example.svg.application.MyApplication
import com.example.svg.databinding.FragmentRecentlyGeneratedDogsBinding
import com.example.svg.ui.adapters.DogsAdapter
import com.example.svg.util.ResourceV2
import com.example.svg.viewmodels.DogsViewModel
import com.example.svg.viewmodels.DogsViewModelProviderFactory

class RecentlyGeneratedDogsFragment :
  BaseFragment<FragmentRecentlyGeneratedDogsBinding, DogsViewModel>() {

  private val viewModel: DogsViewModel by viewModels {
    DogsViewModelProviderFactory((requireActivity().application as MyApplication).repository)
  }

  override fun getFragmentView() = R.layout.fragment_recently_generated_dogs

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)

    viewModel.getCachedDogs()
    setupLoadingAnim(binding.animationView)


    binding.rvCachedDogs.layoutManager =
      LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
    val adapter = DogsAdapter(requireContext())
    binding.rvCachedDogs.adapter = adapter

    viewModel.cachedDogs.observe(viewLifecycleOwner) {
      when (it) {
        is ResourceV2.Loading -> binding.isLoading = true

        is ResourceV2.Success -> {
          binding.isLoading = false
          binding.isEmpty = it.data.isEmpty()
          adapter.dogsList(it.data)
        }

        is ResourceV2.Error -> binding.isLoading = false
      }
    }

  }
//  override fun getViewModel() = DogsViewModel::class.java
}