package com.neumiiller.stand.models

import org.junit.Test
import java.util.*
import kotlin.test.assertEquals
import kotlin.test.assertNotEquals

/**
 * Created by qneumiiller on 12/18/15.
 */
class DayTest {
    @Test fun constructor() {
        val date = Date()
        val content = Content("raw")
        val day = Day(date, content)

        assertEquals(content, day.content)
    }
}
