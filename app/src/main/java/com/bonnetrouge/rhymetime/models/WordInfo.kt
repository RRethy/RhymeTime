package com.bonnetrouge.rhymetime.models

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import android.support.v7.util.DiffUtil

@Entity
data class WordRhymes(
        @PrimaryKey val word: String,
        var rhymes: List<String>? = null,
        @ColumnInfo(name = "near_rhymes") var nearRhymes: List<String>? = null,
        var homophones: List<String>? = null
)

data class WordInfo(
        val word: String,
        val score: Long
) {
    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<WordInfo>() {
            override fun areItemsTheSame(s1: WordInfo, s2: WordInfo) = s1 == s2

            override fun areContentsTheSame(s1: WordInfo, s2: WordInfo) = true
        }
    }
}
