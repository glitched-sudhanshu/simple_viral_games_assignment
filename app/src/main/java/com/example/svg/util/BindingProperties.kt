package com.example.svg.util

import android.view.View
import androidx.databinding.BindingAdapter

@BindingAdapter("visibleIf")
fun setVisibleIf(view: View, visible: Boolean) {
  view.visibility = if (visible) View.VISIBLE else View.GONE
}

@BindingAdapter("invisibleIf")
fun setInvisibleIf(view: View, visible: Boolean) {
  view.visibility = if (visible) View.INVISIBLE else View.VISIBLE
}