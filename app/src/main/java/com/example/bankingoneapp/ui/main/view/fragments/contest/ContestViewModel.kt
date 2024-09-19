package com.example.bankingoneapp.ui.main.view.fragments.contest

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ContestViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is Contest Fragment"
    }
    val text: LiveData<String> = _text
}