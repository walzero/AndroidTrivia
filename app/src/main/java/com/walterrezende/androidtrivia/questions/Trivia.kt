package com.walterrezende.androidtrivia.questions

interface Trivia {
    fun getQuestions(): MutableList<Question>
    fun getQuestion(index: Int): Question
}