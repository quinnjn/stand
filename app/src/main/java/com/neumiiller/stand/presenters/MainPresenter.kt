package com.neumiiller.stand.presenters

import android.app.DatePickerDialog
import android.content.Context
import android.widget.DatePicker
import com.neumiiller.stand.actor.MainActor
import com.neumiiller.stand.adapters.DayPagerAdapter
import com.neumiiller.stand.db.StandDB
import com.neumiiller.stand.listeners.OnDayChangeListener
import com.neumiiller.stand.models.Content
import com.neumiiller.stand.models.Day
import com.neumiiller.stand.views.activities.MainActivity
import java.util.*

/**
 * Created by qneumiiller on 12/18/15.
 */
class MainPresenter(activity: MainActivity) : OnDayChangeListener, DatePickerDialog.OnDateSetListener {

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
            pagerAdapter.notifyDataSetChanged()
            actor.setCurrentItem(
                    standDb.getDayPosition(day)
            )
        }
    }

    fun createDay() {
        val cal = Calendar.getInstance()
        actor.showDatePicker(
                cal.get(Calendar.YEAR),
                cal.get(Calendar.MONTH),
                cal.get(Calendar.DAY_OF_MONTH)
        );
    }

    override fun change(day: Day) {
        standDb.addDay(day)
    }

    override fun onDateSet(view: DatePicker?, year: Int, monthOfYear: Int, dayOfMonth: Int) {
        val cal = Calendar.getInstance()
        cal.set(year, monthOfYear, dayOfMonth)
        val date = cal.time;
        val day = Day(date, Content(""))
        setDay(day)
    }

}
