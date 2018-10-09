package com.bonnetrouge.rhymetime.commons

import android.arch.persistence.room.TypeConverter

class TypeConverters {
    @TypeConverter
    fun stringToWordList(s: String?): List<String>? {
        return s?.split(DELIMETER)
    }

    @TypeConverter
    fun wordsListToString(list: List<String>?): String? {
        return list?.joinToString(separator = DELIMETER)
    }

    companion object {
        private const val DELIMETER = ", "
    }
}