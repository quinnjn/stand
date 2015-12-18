package com.neumiiller.stand.extensions

/**
 * Created by qneumiiller on 12/18/15.
 */

fun Integer.fromLong(long: Long) {
//    var int: Int
//    if (long < Integer.MIN_VALUE) {
//        int = Integer.MIN_VALUE
//    } else if (long > Integer.MAX_VALUE) {
//        int = Integer.MAX_VALUE
//    } else {
//        int = Integer.valueOf(long.toString())
//    }
//    return int
}

fun Long.toInt(): Int {
    if (this < Integer.MIN_VALUE) return Integer.MIN_VALUE
    if (this > Integer.MAX_VALUE) return Integer.MAX_VALUE
    return Integer.valueOf(toString())
}