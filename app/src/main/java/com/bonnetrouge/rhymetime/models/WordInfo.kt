package com.bonnetrouge.rhymetime.models

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import android.support.v7.util.DiffUtil
import com.bonnetrouge.rhymetime.R

@Entity
data class WordRhymes(
        @PrimaryKey val word: String,
        var rhymes: List<String>? = null,
        @ColumnInfo(name = "near_rhymes") var nearRhymes: List<String>? = null,
        var homophones: List<String>? = null
) {
    fun toLists(): List<WordList> {
        val list = arrayListOf<WordList>()
        rhymes?.let { list.add(WordList(R.string.rhymes, R.drawable.bg_outline_rhymes, rhymes!!)) }
        nearRhymes?.let { list.add(WordList(R.string.near_rhymes, R.drawable.bg_outline_near_rhymes, nearRhymes!!)) }
        homophones?.let { list.add(WordList(R.string.homophones, R.drawable.bg_outline_homophones, homophones!!)) }
        return list
    }
}

data class WordList(val title: Int, val bg: Int, val words: List<String>) {
    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<WordList>() {
            override fun areItemsTheSame(s1: WordList, s2: WordList) = s1 == s2

            override fun areContentsTheSame(s1: WordList, s2: WordList) = true
        }
    }
}

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
