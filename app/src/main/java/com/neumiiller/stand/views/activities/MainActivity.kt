package com.neumiiller.stand.views.activities

import android.app.DatePickerDialog
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.design.widget.TabLayout
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import com.neumiiller.stand.R
import com.neumiiller.stand.actor.MainActor
import com.neumiiller.stand.presenters.MainPresenter

class MainActivity : AppCompatActivity(), MainActor {

    private val presenter = MainPresenter(this)

    private val fab by lazy { findViewById(R.id.fab) as FloatingActionButton }
    private val pager by lazy { findViewById(R.id.pager) as ViewPager }
    private val tabs by lazy { findViewById(R.id.sliding_tab_strip) as TabLayout }
    private val toolbar by lazy { findViewById(R.id.toolbar) as Toolbar }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initializeViews()
    }

    private fun initializeViews() {
        setSupportActionBar(toolbar)
        fab.setOnClickListener { view ->
            presenter.createDay()
        }
        pager.adapter = presenter.pagerAdapter
        tabs.setupWithViewPager(pager)
    }

    override fun setCurrentItem(i: Int) {
        pager.adapter.notifyDataSetChanged()
        pager.invalidate()
        tabs.setupWithViewPager(pager)
        pager.currentItem = i
    }

    override fun showDatePicker(year:Int, month:Int, day:Int) {
        val datePicker = DatePickerDialog(this, presenter, year, month, day)
        datePicker.show()
    }
}
