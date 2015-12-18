package com.neumiiller.stand.views.activities

import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.Menu
import android.view.MenuItem
import android.widget.TextView
import com.neumiiller.stand.R
import com.neumiiller.stand.actor.MainActor
import com.neumiiller.stand.models.Content
import com.neumiiller.stand.models.Day
import com.neumiiller.stand.presenters.MainPresenter
import java.util.*

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener, MainActor {

    val presenter = MainPresenter(this, this)

    var fab: FloatingActionButton? = null
    var drawer: DrawerLayout? = null
    var helloWorld: TextView? = null
    var navigation: NavigationView? = null
    var toolbar: Toolbar? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        connectViews()
        initializeViews()
        updateViews()
    }

    private fun connectViews() {
        toolbar = findViewById(R.id.toolbar) as Toolbar
        fab = findViewById(R.id.fab) as FloatingActionButton
        helloWorld = findViewById(R.id.hello_world) as TextView
        drawer = findViewById(R.id.drawer_layout) as DrawerLayout
        navigation = findViewById(R.id.nav_view) as NavigationView
    }

    private fun initializeViews() {
        setSupportActionBar(toolbar)
        fab?.setOnClickListener { view ->
            val day = Day(Date(), Content(helloWorld?.text.toString() + "."))
            presenter.setDay(day)
        }
        val toggle = ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer?.setDrawerListener(toggle)
        toggle.syncState()
        navigation?.setNavigationItemSelectedListener(this)
    }

    override fun updateDay(day: Day) {
        helloWorld?.setText(day?.content?.raw)
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
