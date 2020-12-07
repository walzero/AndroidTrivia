package com.walterrezende.androidtrivia

import android.os.Bundle
import android.view.*
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.walterrezende.androidtrivia.databinding.FragmentTitleBinding
import com.walterrezende.androidtrivia.extensions.setOnClickNavigation

class TitleFragment : Fragment() {

    private val titleToGameAction by lazy {
        TitleFragmentDirections.actionTitleFragmentToGameFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding: FragmentTitleBinding = inflateView(container)

        binding.playButton.setOnClickNavigation(titleToGameAction)
        setHasOptionsMenu(true)
        return binding.root
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

    private fun inflateView(container: ViewGroup?): FragmentTitleBinding =
        DataBindingUtil.inflate(
            layoutInflater,
            R.layout.fragment_title,
            container,
            false
        )
}