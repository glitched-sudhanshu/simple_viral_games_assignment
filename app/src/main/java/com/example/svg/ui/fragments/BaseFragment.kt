package com.example.svg.ui.fragments


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import java.lang.IllegalStateException

abstract class BaseFragment<VB : ViewDataBinding, VM : ViewModel> : Fragment() {

  private var _binding : VB? = null

  val binding: VB
    get() = _binding as VB

//  protected lateinit var viewModel : VM

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
    _binding = DataBindingUtil.inflate(
      inflater,
      getFragmentView(),
      container,
      false
    )

//    viewModel = ViewModelProvider(this).get(getViewModel())


    if(_binding == null) throw IllegalStateException("Binding variable could not be null")

    return binding.root
  }

  abstract fun getFragmentView() : Int

//  abstract fun getViewModel(): Class<VM>

  override fun onDestroyView() {
    super.onDestroyView()
    _binding = null
  }
}