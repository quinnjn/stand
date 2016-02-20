package com.neumiiller.stand.actor

/**
 * Created by qneumiiller on 12/18/15.
 */
interface MainActor {
    fun setCurrentItem(i: Int)

    open fun showDatePicker(year: Int, month: Int, day: Int)
}