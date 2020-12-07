package com.walterrezende.androidtrivia.score

import android.app.Activity

interface ScoreHandler {
    fun shareScore(activity: Activity, correctAnswers: Int, totalQuestions: Int)
    fun scoreIsShareable(activity: Activity): Boolean
}