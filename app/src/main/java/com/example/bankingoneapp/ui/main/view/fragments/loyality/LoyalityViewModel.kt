package com.example.bankingoneapp.ui.main.view.fragments.loyality

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class LoyalityViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is Loyality Fragment"
    }
    val text: LiveData<String> = _text
}