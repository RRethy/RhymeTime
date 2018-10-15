package com.bonnetrouge.rhymetime.commons

import android.support.v7.widget.RecyclerView
import android.support.v7.widget.helper.ItemTouchHelper

class SimpleItemTouchHelper(listener: OnItemChangedListener)
    : ItemTouchHelper(SimpleItemTouchHelper(listener)) {

    private class SimpleItemTouchHelper(val listener: OnItemChangedListener) : ItemTouchHelper.Callback() {
        override fun getMovementFlags(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder): Int {
            val swipeFlags = ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
            return makeMovementFlags(0, swipeFlags)
        }

        override fun onMove(recyclerView: RecyclerView,
                            viewHolder: RecyclerView.ViewHolder,
                            target: RecyclerView.ViewHolder): Boolean {
            return true
        }

        override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
            listener.onItemStateChanged(viewHolder.adapterPosition, direction)
        }

        override fun isLongPressDragEnabled() = true

        override fun isItemViewSwipeEnabled() = true
    }

    interface OnItemChangedListener {
        fun onItemStateChanged(position: Int, direction: Int)
    }
}
