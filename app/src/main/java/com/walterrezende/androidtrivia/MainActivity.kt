package com.walterrezende.androidtrivia

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.walterrezende.androidtrivia.databinding.ActivityMainBinding
import com.walterrezende.androidtrivia.extensions.bindActionBarToNavigation

class MainActivity : AppCompatActivity() {

    private val navHostFragmentId: Int = R.id.myNavHostFragment
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        bindActionBarToNavigation(navHostFragmentId, binding.drawerLayout, binding.navView)
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(navHostFragmentId)
        return NavigationUI.navigateUp(navController, binding.drawerLayout)
    }
}