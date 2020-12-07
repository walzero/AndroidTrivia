package com.walterrezende.androidtrivia

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.NavDestination
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
        val navController = findNavController(navHostFragmentId)
        navController.addOnDestinationChangedListener { nc, nd, args -> validateDrawerMenu(nc, nd) }
    }

    private fun validateDrawerMenu(nc: NavController, nd: NavDestination) {
        val lockMode = when(nd.id) {
            nc.graph.startDestination -> DrawerLayout.LOCK_MODE_UNLOCKED
            else -> DrawerLayout.LOCK_MODE_LOCKED_CLOSED
        }

        binding.drawerLayout.setDrawerLockMode(lockMode)
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(navHostFragmentId)
        return NavigationUI.navigateUp(navController, binding.drawerLayout)
    }
}