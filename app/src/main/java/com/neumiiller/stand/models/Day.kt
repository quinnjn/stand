package com.neumiiller.stand.models

import java.util.*

/**
 * Created by qneumiiller on 12/18/15.
 */
class Day(date: Date, val content: Content) {
    val time: Date

    init {
        time = normalize(date)
    }

    private fun normalize(date: Date): Date {
        val calendar = Calendar.getInstance()
        calendar.time = date
        calendar.set(Calendar.HOUR_OF_DAY, 0)
        calendar.set(Calendar.MINUTE, 0)
        calendar.set(Calendar.SECOND, 0)
        calendar.set(Calendar.MILLISECOND, 0)
        return calendar.time
    }

    override fun equals(other: Any?): Boolean{
        if (this === other) return true
        if (other?.javaClass != javaClass) return false

        other as Day

        if (!content.equals(other.content)) return false
        if (!time.equals(other.time)) return false

        return true
    }

    override fun hashCode(): Int{
        var result = content.hashCode()
        result += 31 * result + time.hashCode()
        return result
    }


}
