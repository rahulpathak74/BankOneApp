package com.example.bankingoneapp.ui.main.view.fragments

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.example.bankingoneapp.R
import com.example.bankingoneapp.databinding.FragmentSplashBinding

class SplashFragment: Fragment() {

    private lateinit var binding: FragmentSplashBinding


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_splash,container,false)
        val loginFragment = LoginFragment()
        Handler(Looper.getMainLooper()).postDelayed({
            activity?.supportFragmentManager?.beginTransaction()?.replace(android.R.id.content, loginFragment)?.commitNow()
        },
            3000)
        return binding.root
    }
}