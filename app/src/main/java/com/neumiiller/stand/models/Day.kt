package com.neumiiller.stand.models

import java.util.*

/**
 * Created by qneumiiller on 12/18/15.
 */
class Day(date: Date, val content: Content) {
    private val date: Date

    init {
        this.date = normalize(date)
    }

    private fun normalize(date: Date): Date {
        val calendar = Calendar.getInstance()
        calendar.time = date
        calendar.set(Calendar.HOUR_OF_DAY, 0)
        calendar.set(Calendar.MINUTE, 0)
        calendar.set(Calendar.SECOND, 0)
        return calendar.time
    }
}
