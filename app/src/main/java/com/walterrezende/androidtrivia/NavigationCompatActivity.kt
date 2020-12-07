package com.walterrezende.androidtrivia

import android.os.Bundle
import androidx.annotation.IdRes
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import com.walterrezende.androidtrivia.extensions.bindActionBarToNavigation

abstract class NavigationCompatActivity : AppCompatActivity() {

    @get:IdRes
    abstract val navHostFragmentId: Int

    override fun onPostCreate(savedInstanceState: Bundle?) {
        bindActionBarToNavigation(navHostFragmentId)
        super.onPostCreate(savedInstanceState)
    }

    override fun onSupportNavigateUp(): Boolean {
        return findNavController(navHostFragmentId).navigateUp()
    }
}