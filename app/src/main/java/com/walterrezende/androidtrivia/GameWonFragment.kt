/*
 * Copyright 2018, The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.walterrezende.androidtrivia

import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import com.walterrezende.androidtrivia.GameWonFragmentDirections.actionGameWonFragmentToGameFragment
import com.walterrezende.androidtrivia.databinding.FragmentGameWonBinding
import com.walterrezende.androidtrivia.extensions.setOnClickNavigation

class GameWonFragment : BaseGameFragment<FragmentGameWonBinding>() {

    override val hasOptionMenu: Boolean = true
    override val layoutIdRes: Int = R.layout.fragment_game_won
    override val menuIdRes: Int = R.menu.winner_menu
    override val onViewInflated = { binding: FragmentGameWonBinding -> onBinding(binding) }
    override val onOptionsMenuInflated: (Menu, MenuInflater) -> Unit = { menu, _ ->
        hideShareMenuIfNotShareable(menu)
    }

    private val bundledArgs by lazy { GameWonFragmentArgs.fromBundle(requireArguments()) }
    private val tryAgainAction by lazy { actionGameWonFragmentToGameFragment() }

    private fun onBinding(binding: FragmentGameWonBinding) {
        binding.nextMatchButton.setOnClickNavigation(tryAgainAction)
    }

    override fun onOptionsItemSelected(
        item: MenuItem
    ): Boolean = with(bundledArgs) {
        when (item.itemId) {
            R.id.share -> scoreHandler.shareScore(requireActivity(), numCorrect, numQuestions)
        }

        return super.onOptionsItemSelected(item)
    }

    private fun hideShareMenuIfNotShareable(menu: Menu) = scoreHandler.run{
        if (scoreHandler.scoreIsShareable(requireActivity())) {
            menu.findItem(R.id.share)?.isVisible = false
        }
    }
}
