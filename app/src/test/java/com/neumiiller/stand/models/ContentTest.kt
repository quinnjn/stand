package com.neumiiller.stand.models

import org.junit.Test
import kotlin.test.assertEquals

/**
 * Created by qneumiiller on 12/18/15.
 */
class ContentTest {

    @Test fun constructor() {
        val raw = "raw"
        val pad = Content(raw)

        assertEquals(raw, pad.raw)
    }
}