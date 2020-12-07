package com.walterrezende.androidtrivia.score

import android.app.Activity
import android.content.Intent
import androidx.core.app.ShareCompat
import com.walterrezende.androidtrivia.R

class GameScoreHandler : ScoreHandler {

    private val shareTextId by lazy { R.string.share_success_text }

    override fun shareScore(
        activity: Activity,
        correctAnswers: Int,
        totalQuestions: Int
    ) = activity.run { startActivity(buildScoreShareIntent(correctAnswers, totalQuestions)) }

    override fun scoreIsShareable(activity: Activity): Boolean = activity.run {
        val intent = buildScoreShareIntent(0, 0)
        return null == intent.resolveActivity(packageManager)
    }

    private fun Activity.buildScoreShareIntent(
        correctAnswers: Int,
        totalQuestions: Int
    ): Intent = ShareCompat.IntentBuilder.from(this)
        .setText(buildShareText(correctAnswers, totalQuestions))
        .setType(getString(R.string.mime_text))
        .intent

    private fun Activity.buildShareText(correctAnswers: Int, totalQuestions: Int) =
        getString(shareTextId, correctAnswers, totalQuestions)
}