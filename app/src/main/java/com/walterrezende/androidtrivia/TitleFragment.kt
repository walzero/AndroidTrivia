package com.walterrezende.androidtrivia

import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.walterrezende.androidtrivia.databinding.FragmentTitleBinding
import com.walterrezende.androidtrivia.extensions.setOnClickNavigation

class TitleFragment : BaseGameFragment<FragmentTitleBinding>() {

    override val onViewInflated = { binding: FragmentTitleBinding -> onBinding(binding) }
    override val hasOptionMenu: Boolean = true
    override val layoutIdRes: Int = R.layout.fragment_title

    private val titleToGameAction by lazy {
        TitleFragmentDirections.actionTitleFragmentToGameFragment()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.overflow_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return choseANavDestination(item) or super.onOptionsItemSelected(item)
    }

    private fun choseANavDestination(item: MenuItem): Boolean = view?.run {
        NavigationUI.onNavDestinationSelected(item, findNavController())
    } ?: false

    private fun onBinding(binding: FragmentTitleBinding) {
        binding.playButton.setOnClickNavigation(titleToGameAction)
    }
}