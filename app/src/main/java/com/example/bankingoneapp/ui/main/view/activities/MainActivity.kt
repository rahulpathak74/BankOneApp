package com.example.bankingoneapp.ui.main.view.activities

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.bankingoneapp.R
import com.example.bankingoneapp.ui.main.view.fragments.SplashFragment

class MainActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val splashFragment = SplashFragment()
        if(savedInstanceState == null) {
            supportFragmentManager.beginTransaction().replace(android.R.id.content, splashFragment).commitNow()
        }
    }



}