package com.bonnetrouge.rhymetime.models

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity
data class WordFavorite(@PrimaryKey val word: String)
