package com.neumiiller.stand.db

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.neumiiller.stand.db.tables.DayTable
import com.neumiiller.stand.models.Content
import com.neumiiller.stand.models.Day
import java.util.*

/**
 * Created by qneumiiller on 12/18/15.
 */
class StandDB(context: Context) : SQLiteOpenHelper(context, StandDB.NAME, null, StandDB.VERSION) {
    companion object {
        private val NAME = StandDB::class.java.simpleName
        private val VERSION = 1
    }

    init {

    }

    private val dayTable = DayTable()
    private val tables = arrayOf(dayTable)

    override fun onCreate(db: SQLiteDatabase) {
        for (table in tables) {
            db.execSQL(table.create())
        }
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        for (table in tables) {
            db.execSQL(table.upgrade(oldVersion, newVersion))
        }
    }

    public fun addDay(day: Day): Long {
        return dayTable.set(writableDatabase, day)
    }

    fun getDay(time: Date): Day? {
        return dayTable.get(readableDatabase, time)
    }

    fun getDay(position: Int): Day {
        return dayTable.get(readableDatabase, position)
    }

    fun getDayCount(): Int {
        var count = dayTable.count(readableDatabase).toInt()

        if (count == 0) {
            var cal = Calendar.getInstance()
            cal.add(Calendar.YEAR, -1)
            addDay(Day(cal.time, Content(cal.time.time.toString())))
            cal.add(Calendar.YEAR, -1)
            addDay(Day(cal.time, Content(cal.time.time.toString())))
            cal.add(Calendar.YEAR, -1)
            addDay(Day(cal.time, Content(cal.time.time.toString())))
            cal.add(Calendar.YEAR, -1)
            addDay(Day(cal.time, Content(cal.time.time.toString())))
            cal.add(Calendar.YEAR, -1)
            addDay(Day(cal.time, Content(cal.time.time.toString())))
            count = dayTable.count(readableDatabase).toInt()
        }

        return count
    }
}