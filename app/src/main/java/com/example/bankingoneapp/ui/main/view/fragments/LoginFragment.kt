package com.example.bankingoneapp.ui.main.view.fragments

import android.app.KeyguardManager
import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.DialogInterface
import android.content.pm.PackageManager
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.os.CancellationSignal
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.biometric.BiometricManager
import androidx.biometric.BiometricPrompt
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.getSystemService
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.bankingoneapp.R
import com.example.bankingoneapp.databinding.FragmentLoginBinding


class LoginFragment : Fragment() {

    private lateinit var binding: FragmentLoginBinding
    private var cancellationSignal: CancellationSignal? = null

    // create an authenticationCallback
    private val authenticationCallback: BiometricPrompt.AuthenticationCallback
        get() = @RequiresApi(Build.VERSION_CODES.P)
        object : BiometricPrompt.AuthenticationCallback() {
            override fun onAuthenticationError(errorCode: Int, errString: CharSequence) {
                super.onAuthenticationError(errorCode, errString)
                notifyUser("Authentication Error : $errString")
            }

            override fun onAuthenticationSucceeded(result: BiometricPrompt.AuthenticationResult) {
                super.onAuthenticationSucceeded(result)
                notifyUser("Authentication Succeeded")

                // or start a new Activity

            }

        }


    @RequiresApi(Build.VERSION_CODES.P)
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_login, container, false)
        setOnClickListeners()
        biometricLoginHandling()
        return binding.root
    }


    private fun setOnClickListeners() {
        binding.forgotPassword.setOnClickListener {
            Toast.makeText(this.context, "Forgot Password", Toast.LENGTH_SHORT).show()
        }

        binding.loginButton.setOnClickListener {
            if (binding.username.text.toString().isEmpty() || binding.mainEdittext.text.toString()
                    .isEmpty()
            ) {

                Toast.makeText(this.context, "Username/Password is empty", Toast.LENGTH_SHORT)
                    .show()
            }

            if (binding.username.text.toString()
                    .isNotEmpty() && binding.mainEdittext.text.toString().isNotEmpty()
            ) {
                if (binding.mainEdittext.text.toString().equals("password", true)) {
                    val sharedPreferences =
                        activity?.getSharedPreferences("MySharedPref", MODE_PRIVATE)
                    sharedPreferences?.edit()?.putBoolean("isLoggedIn", true)?.apply()

                    // Move to Home Page
                } else {
                    Toast.makeText(
                        this.context,
                        "Please enter correct password",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }

        }

    }

    @RequiresApi(Build.VERSION_CODES.P)
    private fun biometricLoginHandling() {
        val sharedPreferences = activity?.getSharedPreferences("MySharedPref", MODE_PRIVATE)
        val isLoggedIn = sharedPreferences?.getBoolean("isLoggedIn", false)
        if (isLoggedIn == true) {
            binding.apply {
                biometricButton.visibility = View.VISIBLE
                biometricMessage.visibility = View.VISIBLE
                username.visibility = View.GONE
                passwordEdittext.visibility = View.GONE
                forgotPassword.visibility = View.GONE
                loginButton.visibility = View.GONE
            }

            checkBiometricSupport()

            binding.biometricButton.setOnClickListener {
                val biometricPromptInfo = BiometricPrompt.PromptInfo.Builder().apply {
                    setTitle("BankOne")
                        .setSubtitle("Authenticate with your fingerprint")
                        .setNegativeButtonText("Cancel")
                }.build()

                // start the authenticationCallback in mainExecutor
                val biometricPrompt = activity?.let { it1 ->
                    activity?.mainExecutor?.let { it2 ->
                        BiometricPrompt(
                            it1,
                            it2, authenticationCallback
                        )
                    }
                }
                biometricPrompt?.authenticate(biometricPromptInfo)
            }
        } else {
            binding.apply {
                biometricButton.visibility = View.GONE
                biometricMessage.visibility = View.GONE
                username.visibility = View.VISIBLE
                passwordEdittext.visibility = View.VISIBLE
                forgotPassword.visibility = View.VISIBLE
                loginButton.visibility = View.VISIBLE
            }
        }
    }

    // it will be called when authentication is cancelled by the user
    private fun getCancellationSignal(): CancellationSignal {
        cancellationSignal = CancellationSignal()
        cancellationSignal?.setOnCancelListener {
            notifyUser("Authentication was Cancelled by the user")
        }
        return cancellationSignal as CancellationSignal
    }

    // it checks whether the app the app has fingerprint permission
    @RequiresApi(Build.VERSION_CODES.M)
    private fun checkBiometricSupport(): Boolean {
        val keyguardManager =
            activity?.getSystemService(Context.KEYGUARD_SERVICE) as KeyguardManager
        if (!keyguardManager.isDeviceSecure) {
            notifyUser("Fingerprint authentication has not been enabled in settings")
            return false
        }
        if (this.context?.let {
                ActivityCompat.checkSelfPermission(
                    it,
                    android.Manifest.permission.USE_BIOMETRIC
                )
            } != PackageManager.PERMISSION_GRANTED) {
            notifyUser("Fingerprint Authentication Permission is not enabled")
            return false
        }
        return if (activity?.packageManager?.hasSystemFeature(PackageManager.FEATURE_FINGERPRINT) == true) {
            true
        } else true
    }

    private fun notifyUser(message: String) {
        Toast.makeText(this.context, message, Toast.LENGTH_SHORT).show()
    }

}