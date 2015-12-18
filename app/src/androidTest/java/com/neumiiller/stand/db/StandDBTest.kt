package com.neumiiller.stand.db

import android.test.AndroidTestCase
import android.test.RenamingDelegatingContext
import com.neumiiller.stand.models.Content
import com.neumiiller.stand.models.Day
import java.util.*

/**
 * Created by qneumiiller on 12/18/15.
 */
class StandDBTest : AndroidTestCase() {

    private var db: StandDB? = null

    override fun setUp() {
        super.setUp()
        context = RenamingDelegatingContext(getContext(), "test")
        db = StandDB(context)
    }

    public fun testAddAndGetDay() {
        val cal = Calendar.getInstance()
        cal.set(1, 1, 1)

        val time = cal.time
        val expectedDay = Day(time, Content("abc123"))
        val id = db?.addDay(expectedDay)

        val actualDay = db?.getDay(time)

        assertEquals(expectedDay, actualDay)
    }

    public fun testGetNothing() {
        val cal = Calendar.getInstance()
        cal.set(0, 0, 0, 0, 0, 0)

        val time = cal.time
        val actualDay = db?.getDay(time)

        assertNull(actualDay)
    }
}