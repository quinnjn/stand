package com.neumiiller.stand.db.tables

import android.content.ContentValues
import android.database.DatabaseUtils
import android.database.sqlite.SQLiteDatabase
import com.neumiiller.stand.extensions.normalize
import com.neumiiller.stand.models.Content
import com.neumiiller.stand.models.Day
import java.util.*

/**
 * Created by qneumiiller on 12/18/15.
 */
class DayTable : Table {
    companion object {
        private val KEY_TIME = "time"
        private val KEY_CONTENT = "content"
    }

    override fun getTableParameters() = "$KEY_TIME LONG PRIMARY KEY, $KEY_CONTENT TEXT"

    override fun getTableName() = "DayTable"

    override fun initializeTable(wDb: SQLiteDatabase) {
        if(get(wDb, Date()) == null) {
            set(wDb, Day(Date(), Content("")))
        }
    }

    public fun set(wDb: SQLiteDatabase, day: Day): Long {
        val time = day.time
        val values = ContentValues();
        var id:Long = -1

        time.normalize()
        values.put(KEY_CONTENT, day.content.raw)
        values.put(KEY_TIME, time.time)

        val rowsEffected = wDb.update(getTableName(), values, "$KEY_TIME = ${time.time}", null)
        if(rowsEffected < 1) {
            id = wDb.insert(getTableName(), null, values)
        }

        return id
    }

    fun get(db: SQLiteDatabase, position: Int): Day {
        val query = "SELECT * FROM ${getTableName()} ORDER BY $KEY_TIME DESC LIMIT $position,1"
        val cursor = db.rawQuery(query, null);
        if(cursor == null || cursor.count == 0) {
           return Day(Date(), Content(""))
        }
        cursor.moveToFirst()
        val date = Date(cursor.getLong(cursor.getColumnIndex(KEY_TIME)))
        val content = Content(cursor.getString(cursor.getColumnIndex(KEY_CONTENT)))
        return Day(date, content)
    }

    fun get(db: SQLiteDatabase, time: Date): Day? {
        time.normalize()
        val query = "SELECT * FROM ${getTableName()} WHERE $KEY_TIME == ${time.time}"
        val cursor = db.rawQuery(query, null);
        if(cursor == null || cursor.count == 0) {
            return null
        }
        cursor.moveToFirst()
        val date = Date(cursor.getLong(cursor.getColumnIndex(KEY_TIME)))
        val content = Content(cursor.getString(cursor.getColumnIndex(KEY_CONTENT)))
        return Day(date, content)
    }

    fun count(db: SQLiteDatabase): Long {
        return DatabaseUtils.queryNumEntries(db, getTableName())
    }
}
