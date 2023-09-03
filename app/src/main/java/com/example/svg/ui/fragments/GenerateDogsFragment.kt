package com.example.svg.ui.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import com.example.svg.R
import com.example.svg.application.MyApplication
import com.example.svg.databinding.FragmentGenerateDogsBinding
import com.example.svg.util.ResourceV2
import com.example.svg.viewmodels.DogsViewModel
import com.example.svg.viewmodels.DogsViewModelProviderFactory

class GenerateDogsFragment : BaseFragment<FragmentGenerateDogsBinding, DogsViewModel>() {

  private val viewModel: DogsViewModel by viewModels {
    DogsViewModelProviderFactory((requireActivity().application as MyApplication).repository)
  }

  override fun getFragmentView() = R.layout.fragment_generate_dogs

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)

    binding.btnGenerate.setOnClickListener{
      viewModel.getRandomDog()
    }

    setupLoadingAnim(binding.animationView)

    viewModel.latestRandomDog.observe(viewLifecycleOwner){
      when(it)
      {
        is ResourceV2.Loading -> binding.isLoading = true
        is ResourceV2.Success -> {
          binding.isLoading = false
          Glide.with(requireContext())
            .load(it.data.imageUrl)
            .centerCrop()
            .placeholder(R.drawable.empty_dog)
            .into(binding.ivGeneratedDog)
        }
        is ResourceV2.Error -> binding.isLoading = false
      }
    }
  }

//  override fun getViewModel() = DogsViewModel::class.java
}