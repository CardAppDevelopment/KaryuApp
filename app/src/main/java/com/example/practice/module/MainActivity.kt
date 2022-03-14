package com.example.practice.module

import android.os.Bundle
import androidx.navigation.findNavController

import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.practice.R
import com.example.practice.base.BaseActivity
import com.example.practice.databinding.ActivityMainBinding


class MainActivity : BaseActivity<ActivityMainBinding>(ActivityMainBinding::inflate) {

    private lateinit var instance:MainActivity

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initNavigationBar()

        instance=this

    }

    private fun initNavigationBar() {
        val navView: BottomNavigationView = viewBinding.navView
        val navController = findNavController(R.id.nav_host_fragment_activity_main)
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_home,
                R.id.navigation_history,
                R.id.navigation_pay,
                R.id.navigation_notifications,
                R.id.navigation_settings
            )
        )
//        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.labelVisibilityMode= BottomNavigationView.LABEL_VISIBILITY_LABELED
        navView.setupWithNavController(navController)

        navView.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.navigation_home -> {
                    findNavController(R.id.nav_host_fragment_activity_main).navigate(R.id.navigation_home)
                }
                R.id.navigation_history -> {
                    findNavController(R.id.nav_host_fragment_activity_main).navigate(R.id.navigation_history)
                }
                R.id.navigation_pay -> {
                    findNavController(R.id.nav_host_fragment_activity_main).navigate(R.id.navigation_pay)
                }
                R.id.navigation_notifications -> {
                    findNavController(R.id.nav_host_fragment_activity_main).navigate(R.id.navigation_notifications)
                }
                R.id.navigation_settings -> {
                    findNavController(R.id.nav_host_fragment_activity_main).navigate(R.id.navigation_settings)
                }
            }

            return@setOnNavigationItemSelectedListener false
        }
    }

    public fun getInstance():MainActivity{
        return instance
    }
}