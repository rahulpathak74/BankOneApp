package com.example.bankingoneapp.ui.main.view.activities

import android.os.Bundle
import android.view.Menu
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.navigation.NavigationView
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.drawerlayout.widget.DrawerLayout
import androidx.appcompat.app.AppCompatActivity
import com.example.bankingoneapp.R
import com.example.bankingoneapp.databinding.ActivityBottomNavigationViewBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

class BottomNavigationViewActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityBottomNavigationViewBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityBottomNavigationViewBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        val drawerLayout: DrawerLayout = binding.drawerLayout
        val navView: NavigationView = binding.navView
        val navController = findNavController(R.id.nav_host_fragment_content_bottom_navigation_view)
        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow
            ), drawerLayout
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
//        navView.setupWithNavController(navController)
        navView.setNavigationItemSelectedListener { menuItem ->
            menuItem.isChecked = true
            binding.drawerLayout.closeDrawers()

            when (menuItem.itemId) {
                R.id.nav_gallery -> {
                    true
                }

                R.id.nav_home -> {
                    true
                }

                R.id.nav_slideshow -> {
                    true
                }

                R.id.nav_save -> {
                    true
                }

                R.id.nav_profile -> {
                    true
                }

                R.id.nav_barrow -> {
                    true
                }

                R.id.nav_logout -> {
                    true
                }

                else -> false
            }
        }
        val navBottomNavigationView: BottomNavigationView = binding.navBottonNavigationView

        val navBottomNavigationController =
            findNavController(R.id.nav_host_fragment_content_bottom_navigation_view)
//        // Passing each menu ID as a set of Ids because each
//        // menu should be considered as top level destinations.
//        val appBarConfiguration = AppBarConfiguration(setOf(
//            R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications))
        setupActionBarWithNavController(navBottomNavigationController, appBarConfiguration)
        navBottomNavigationView.setupWithNavController(navBottomNavigationController)
    }


    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_bottom_navigation_view)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }
}