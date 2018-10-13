package com.bonnetrouge.rhymetime.models

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import android.support.v7.util.DiffUtil

@Entity
data class WordFavorite(@PrimaryKey val word: String) {
    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<WordFavorite>() {
            override fun areItemsTheSame(s1: WordFavorite, s2: WordFavorite) = s1 == s2

            override fun areContentsTheSame(s1: WordFavorite, s2: WordFavorite) = true
        }
    }
}
