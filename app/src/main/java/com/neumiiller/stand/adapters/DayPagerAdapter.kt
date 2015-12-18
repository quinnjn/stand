package com.neumiiller.stand.adapters

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import com.neumiiller.stand.db.StandDB
import com.neumiiller.stand.models.Day
import com.neumiiller.stand.views.fragments.DayPageFragment
import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by qneumiiller on 12/18/15.
 */
class DayPagerAdapter(private val standDB: StandDB, fm: FragmentManager) : FragmentStatePagerAdapter(fm) {

    private val formatter = SimpleDateFormat("EEE MMM dd")
    private val cal = Calendar.getInstance()
    private var fragment: DayPageFragment? = null

    override fun getItem(position: Int): Fragment? {
        fragment = DayPageFragment(getDay(position))
        return fragment
    }

    override fun getCount(): Int {
        return standDB.getDayCount();
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return formatter.format(getDay(position)?.time)
    }

    fun getDay(position: Int): Day? {
        return standDB.getDay(position)
    }
}
