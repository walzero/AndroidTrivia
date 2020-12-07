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

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.walterrezende.androidtrivia.databinding.FragmentGameWonBinding
import com.walterrezende.androidtrivia.extensions.setOnClickNavigation


class GameWonFragment : Fragment() {

    private val bundledArgs by lazy { GameWonFragmentArgs.fromBundle(requireArguments()) }

    private val numCorrect by lazy { bundledArgs.numCorrect }
    private val numQuestions by lazy { bundledArgs.numQuestions }

    private val tryAgainAction by lazy {
        GameWonFragmentDirections.actionGameWonFragmentToGameFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        // Inflate the layout for this fragment
        val binding: FragmentGameWonBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_game_won, container, false
        )

        Toast.makeText(
            requireContext(),
            "questions: $numQuestions; right: $numCorrect",
            Toast.LENGTH_SHORT
        ).show()

        binding.nextMatchButton.setOnClickNavigation(tryAgainAction)

        return binding.root
    }
}
