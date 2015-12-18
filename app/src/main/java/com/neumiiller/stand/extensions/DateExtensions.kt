package com.neumiiller.stand.extensions

import java.util.*

/**
 * Created by qneumiiller on 12/18/15.
 */
fun Date.normalize() {
    val cal = Calendar.getInstance()
    cal.set(Calendar.HOUR, 0)
    cal.set(Calendar.MINUTE, 0)
    cal.set(Calendar.SECOND, 0)
    cal.set(Calendar.MILLISECOND, 0)
    time = cal.time.time
}
