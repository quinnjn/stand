package com.neumiiller.stand.db.tables

import android.database.sqlite.SQLiteDatabase

/**
 * Created by qneumiiller on 12/18/15.
 */
interface Table {
    public fun create() = "CREATE TABLE " + "${getTableName()} (${getTableParameters()})"
    public fun upgrade(oldVersion: Int, newVersion: Int) = "DROP TABLE IF EXISTS ${getTableName()}"
    fun getTableName(): String
    fun getTableParameters(): String
    fun initializeTable(wDb: SQLiteDatabase)
}