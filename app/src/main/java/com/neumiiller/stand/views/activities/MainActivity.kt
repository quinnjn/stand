package com.neumiiller.stand.views.activities

import android.app.DatePickerDialog
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.design.widget.NavigationView
import android.support.design.widget.TabLayout
import android.support.v4.view.GravityCompat
import android.support.v4.view.ViewPager
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.Menu
import android.view.MenuItem
import com.neumiiller.stand.R
import com.neumiiller.stand.actor.MainActor
import com.neumiiller.stand.presenters.MainPresenter

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener, MainActor {

    private val presenter = MainPresenter(this)

    private val fab by lazy { findViewById(R.id.fab) as FloatingActionButton }
    private val drawer by lazy { findViewById(R.id.drawer_layout) as DrawerLayout }
    private val navigation by lazy { findViewById(R.id.nav_view) as NavigationView }
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
        val toggle = ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer.setDrawerListener(toggle)
        toggle.syncState()
        navigation.setNavigationItemSelectedListener(this)
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

    override fun onBackPressed() {
        val drawer = findViewById(R.id.drawer_layout) as DrawerLayout
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        val id = item.itemId

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true
        }

        return super.onOptionsItemSelected(item)
    }

    @SuppressWarnings("StatementWithEmptyBody")
    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.
        val id = item.itemId

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        val drawer = findViewById(R.id.drawer_layout) as DrawerLayout
        drawer.closeDrawer(GravityCompat.START)
        return true
    }
}
