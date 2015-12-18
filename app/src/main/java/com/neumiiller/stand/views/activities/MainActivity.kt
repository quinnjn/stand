package com.neumiiller.stand.views.activities

import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v4.view.PagerTitleStrip
import android.support.v4.view.ViewPager
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.support.design.widget.TabLayout
import android.view.Menu
import android.view.MenuItem
import com.neumiiller.stand.R
import com.neumiiller.stand.actor.MainActor
import com.neumiiller.stand.adapters.DayPagerAdapter
import com.neumiiller.stand.db.StandDB
import com.neumiiller.stand.models.Day
import com.neumiiller.stand.presenters.MainPresenter
import java.util.*

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener, MainActor {

    private val presenter = MainPresenter(this, this)

    private var fab: FloatingActionButton? = null
    private var drawer: DrawerLayout? = null
    private var navigation: NavigationView? = null
    private var pager: ViewPager? = null
    private var pagerTitleStrip: PagerTitleStrip? = null
    private var tabs: TabLayout? = null
    private var toolbar: Toolbar? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        connectViews()
        initializeViews()
        updateViews()
    }

    private fun connectViews() {
        fab = findViewById(R.id.fab) as FloatingActionButton
        drawer = findViewById(R.id.drawer_layout) as DrawerLayout
        navigation = findViewById(R.id.nav_view) as NavigationView
        pager = findViewById(R.id.pager) as ViewPager
        tabs = findViewById(R.id.sliding_tab_strip) as TabLayout
        toolbar = findViewById(R.id.toolbar) as Toolbar
    }

    private fun initializeViews() {
        setSupportActionBar(toolbar)
        fab?.setOnClickListener { view ->
//            val day = Day(Date(), Content(helloWorld?.text.toString() + "."))
//            presenter.setDay(day)
        }
        val toggle = ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer?.setDrawerListener(toggle)
        toggle.syncState()
        navigation?.setNavigationItemSelectedListener(this)
        pager?.adapter = DayPagerAdapter(StandDB(this), supportFragmentManager)
        tabs?.setupWithViewPager(pager)
    }

    override fun updateDay(day: Day) {
    }

    private fun updateViews() {
        val day = presenter.getDay(Date())
        if(day != null) {
            updateDay(day)
        }
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
