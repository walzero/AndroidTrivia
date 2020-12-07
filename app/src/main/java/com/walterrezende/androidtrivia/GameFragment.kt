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

import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.walterrezende.androidtrivia.GameFragmentDirections.actionGameFragmentToGameOverFragment
import com.walterrezende.androidtrivia.GameFragmentDirections.actionGameFragmentToGameWonFragment
import com.walterrezende.androidtrivia.databinding.FragmentGameBinding
import com.walterrezende.androidtrivia.extensions.navigateTo
import com.walterrezende.androidtrivia.questions.AndroidTriviaGame
import com.walterrezende.androidtrivia.questions.Question
import com.walterrezende.androidtrivia.questions.Trivia

class GameFragment : BaseGameFragment<FragmentGameBinding>() {

    override val onViewInflated = { binding: FragmentGameBinding -> onBinding(binding) }
    override val layoutIdRes: Int = R.layout.fragment_game

    private val wonDirection by lazy {
        actionGameFragmentToGameWonFragment(numQuestions, questionIndex)
    }

    private val lostDirection by lazy {
        actionGameFragmentToGameOverFragment(numQuestions, questionIndex)
    }

    private val trivia: Trivia = AndroidTriviaGame()
    private val numQuestions = Math.min((trivia.getQuestions().size + 1) / 2, 3)
    lateinit var currentQuestion: Question
    lateinit var answers: MutableList<String>
    private var questionIndex = 0

    private fun onBinding(binding: FragmentGameBinding) {
// Shuffles the questions and sets the question index to the first question.
        randomizeQuestions()

        // Bind this fragment class to the layout
        binding.game = this

        // Set the onClickListener for the submitButton
        binding.submitButton.setOnClickListener @Suppress("UNUSED_ANONYMOUS_PARAMETER")
        { view: View -> onSubmitClicked(binding) }
    }

    private fun onSubmitClicked(binding: FragmentGameBinding) {
        val checkedId = binding.questionRadioGroup.checkedRadioButtonId
        // Do nothing if nothing is checked (id == -1)
        if (-1 != checkedId) {
            var answerIndex = 0
            when (checkedId) {
                R.id.secondAnswerRadioButton -> answerIndex = 1
                R.id.thirdAnswerRadioButton -> answerIndex = 2
                R.id.fourthAnswerRadioButton -> answerIndex = 3
            }
            // The first answer in the original question is always the correct one, so if our
            // answer matches, we have the correct answer.
            if (answers[answerIndex] == currentQuestion.answers[0]) {
                questionIndex++
                // Advance to the next question
                if (questionIndex < numQuestions) {
                    currentQuestion = trivia.getQuestion(questionIndex)
                    setQuestion()
                    binding.invalidateAll()
                } else {
                    navigateTo(wonDirection)
                }
            } else {
                navigateTo(lostDirection)
            }
        }
    }

    // randomize the questions and set the first question
    private fun randomizeQuestions() {
        trivia.getQuestions().shuffle()
        questionIndex = 0
        setQuestion()
    }

    // Sets the question and randomizes the answers.  This only changes the data, not the UI.
    // Calling invalidateAll on the FragmentGameBinding updates the data.
    private fun setQuestion() {
        currentQuestion = trivia.getQuestion(questionIndex)
        // randomize the answers into a copy of the array
        answers = currentQuestion.answers.toMutableList()
        // and shuffle them
        answers.shuffle()
        (activity as AppCompatActivity).supportActionBar?.title =
            getString(R.string.title_android_trivia_question, questionIndex + 1, numQuestions)
    }
}
