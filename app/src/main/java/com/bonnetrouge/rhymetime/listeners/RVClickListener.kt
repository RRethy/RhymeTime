package com.bonnetrouge.rhymetime.listeners

interface RVClickListener<T> {
    fun onItemClick(data: T, index: Int)
}
