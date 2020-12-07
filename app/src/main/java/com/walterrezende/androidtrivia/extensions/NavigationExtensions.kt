package com.walterrezende.androidtrivia.extensions

import android.view.View
import androidx.annotation.IdRes
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.NavDirections
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI

fun View.setOnClickNavigation(navDirection: NavDirections) {
    setOnClickListener { it?.findNavController()?.navigate(navDirection) }
}

fun Fragment.navigateTo(navDirection: NavDirections) {
    findNavController().navigate(navDirection)
}

fun AppCompatActivity.bindActionBarToNavigation(@IdRes navControllerId: Int) {
    val navController = findNavController(navControllerId)
    NavigationUI.setupActionBarWithNavController(this, navController)
}