package com.example.android.newsfeed

import android.content.Intent
import android.os.Bundle
import com.google.android.material.navigation.NavigationView
import com.google.android.material.tabs.TabLayout
import androidx.core.view.GravityCompat
import androidx.viewpager.widget.ViewPager
import androidx.drawerlayout.widget.DrawerLayout
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import android.view.Menu
import android.view.MenuItem
import com.example.android.newsfeed.adapter.CategoryFragmentPagerAdapter
import com.example.android.newsfeed.utils.Constants

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    private lateinit var viewPager: ViewPager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        val drawer: DrawerLayout = findViewById(R.id.drawer_layout)
        val toggle = ActionBarDrawerToggle(
            this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close
        )
        drawer.addDrawerListener(toggle)
        toggle.syncState()

        viewPager = findViewById(R.id.viewpager)

        val tabLayout: TabLayout = findViewById(R.id.sliding_tabs)
        tabLayout.setupWithViewPager(viewPager)
        tabLayout.tabGravity = TabLayout.GRAVITY_FILL

        val navigationView: NavigationView = findViewById(R.id.nav_view)
        navigationView.setNavigationItemSelectedListener(this)

        onNavigationItemSelected(navigationView.menu.getItem(0).setChecked(true))

        val pagerAdapter = CategoryFragmentPagerAdapter(this, supportFragmentManager)
        viewPager.adapter = pagerAdapter
    }

    override fun onBackPressed() {
        val drawer: DrawerLayout = findViewById(R.id.drawer_layout)
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.nav_home -> viewPager.currentItem = Constants.HOME
            R.id.nav_world -> viewPager.currentItem = Constants.WORLD
            R.id.nav_science -> viewPager.currentItem = Constants.SCIENCE
            R.id.nav_sport -> viewPager.currentItem = Constants.SPORT
            R.id.nav_environment -> viewPager.currentItem = Constants.ENVIRONMENT
            R.id.nav_society -> viewPager.currentItem = Constants.SOCIETY
            R.id.nav_fashion -> viewPager.currentItem = Constants.FASHION
            R.id.nav_business -> viewPager.currentItem = Constants.BUSINESS
            R.id.nav_culture -> viewPager.currentItem = Constants.CULTURE
        }

        val drawer: DrawerLayout = findViewById(R.id.drawer_layout)
        drawer.closeDrawer(GravityCompat.START)
        return true
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return if (item.itemId == R.id.action_settings) {
            val settingsIntent = Intent(this, SettingsActivity::class.java)
            startActivity(settingsIntent)
            true
        } else {
            super.onOptionsItemSelected(item)
        }
    }
}
