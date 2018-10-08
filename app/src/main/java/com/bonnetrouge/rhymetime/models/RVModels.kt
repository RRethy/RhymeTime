package com.bonnetrouge.rhymetime.models

import android.support.v7.util.DiffUtil

data class Suggestion(
        val word: String,
        val score: Long
) {
    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Suggestion>() {
            override fun areItemsTheSame(s1: Suggestion, s2: Suggestion) = s1 == s2

            override fun areContentsTheSame(s1: Suggestion, s2: Suggestion) = true
        }
    }
}
