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
import com.walterrezende.androidtrivia.GameOverFragmentDirections.actionGameOverFragmentToGameFragment
import com.walterrezende.androidtrivia.databinding.FragmentGameOverBinding
import com.walterrezende.androidtrivia.extensions.setOnClickNavigation

class GameOverFragment : BaseGameFragment<FragmentGameOverBinding>() {

    override val hasOptionMenu: Boolean = true
    override val layoutIdRes: Int = R.layout.fragment_game_over
    override val onViewInflated = { binding: FragmentGameOverBinding -> onBinding(binding) }
    override val onOptionsMenuInflated: (Menu, MenuInflater) -> Unit = { menu, _ ->
        hideShareMenuIfNotShareable(menu)
    }

    private val bundledArgs by lazy { GameOverFragmentArgs.fromBundle(requireArguments()) }
    private val tryAgainAction by lazy { actionGameOverFragmentToGameFragment() }

    private fun onBinding(binding: FragmentGameOverBinding) {
        binding.tryAgainButton.setOnClickNavigation(tryAgainAction)
    }

    override fun onOptionsItemSelected(
        item: MenuItem
    ): Boolean = bundledArgs.run {
        when (item.itemId) {
            R.id.share -> scoreHandler.shareScore(requireActivity(), numCorrect, numQuestions)
        }

        return super.onOptionsItemSelected(item)
    }

    private fun hideShareMenuIfNotShareable(menu: Menu) {
        if (scoreHandler.scoreIsShareable(requireActivity())) {
            menu.findItem(R.id.share)?.isVisible = false
        }
    }
}
