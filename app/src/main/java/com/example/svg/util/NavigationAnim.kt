package com.example.svg.util

import androidx.navigation.AnimBuilder
import com.example.svg.R

val defaultNavAnimationsBuilder: AnimBuilder.() -> Unit = {
  enter = R.anim.screen_open_enter
  exit = R.anim.screen_open_exit
  popEnter = R.anim.screen_close_enter
  popExit = R.anim.screen_close_exit
}