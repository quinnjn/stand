package com.neumiiller.stand.presenters

import android.content.Context
import android.view.View
import android.widget.Toast
import com.neumiiller.stand.actor.MainActor
import com.neumiiller.stand.adapters.DayPagerAdapter
import com.neumiiller.stand.db.StandDB
import com.neumiiller.stand.listeners.OnDayChangeListener
import com.neumiiller.stand.models.Day
import com.neumiiller.stand.views.activities.MainActivity
import java.util.*

/**
 * Created by qneumiiller on 12/18/15.
 */
class MainPresenter(activity: MainActivity) : OnDayChangeListener {
    val pagerAdapter: DayPagerAdapter
    private val context: Context
    private val actor: MainActor
    private val standDb: StandDB

    init {
        context = activity
        actor = activity
        standDb = StandDB(context)
        pagerAdapter = DayPagerAdapter(standDb, activity.supportFragmentManager)
    }

    public fun getDay(time: Date): Day? {
        return standDb.getDay(time)
    }

    fun setDay(day: Day) {
        standDb.addDay(day)
        val updatedDay = standDb.getDay(day.time)
        if (updatedDay != null) {
            actor.updateDay(updatedDay)
        }
    }

    fun createDay() {
        Toast.makeText(context, "yo", Toast.LENGTH_SHORT).show()
    }

    override fun change(day: Day) {
        standDb.addDay(day)
    }

}
