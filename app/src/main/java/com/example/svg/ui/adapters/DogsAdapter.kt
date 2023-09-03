package com.example.svg.ui.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.svg.R
import com.example.svg.databinding.SavedDogItemBinding
import com.example.svg.domain.models.Dogs

class DogsAdapter(private val context: Context) :
  RecyclerView.Adapter<DogsAdapter.ViewHolder>() {

  private var dogs : List<Dogs> = listOf()

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DogsAdapter.ViewHolder {
    val binding: SavedDogItemBinding =
      SavedDogItemBinding.inflate(LayoutInflater.from(context), parent, false)
    return ViewHolder(binding)
  }

  override fun onBindViewHolder(holder: DogsAdapter.ViewHolder, position: Int) {
    Glide.with(context)
      .load(dogs[position].imageUrl)
      .placeholder(R.drawable.empty_dog)
      .into(holder.ivDog)

    holder.tvNumber.text = "${position+1}/${dogs.size}"
  }

  override fun getItemCount() = dogs.size

  class ViewHolder(view: SavedDogItemBinding) : RecyclerView.ViewHolder(view.root) {

    val ivDog = view.ivDog
    val tvNumber = view.tvNumber
  }

  fun dogsList(list : List<Dogs>){
    dogs = list
    notifyDataSetChanged()
  }
}