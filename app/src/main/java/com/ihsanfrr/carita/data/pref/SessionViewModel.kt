package com.ihsanfrr.carita.data.pref

import kotlinx.coroutines.launch
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope

class SessionViewModel(private val pref: CaritaPreferences) : ViewModel() {
    fun saveSession(token: String, name: String) {
        viewModelScope.launch {
            pref.persistTokenDetails(token, name)
        }
    }

    fun getToken(): LiveData<String> {
        return pref.retrieveToken().asLiveData()
    }

    fun getName(): LiveData<String> {
        return pref.retrieveUserName().asLiveData()
    }

    fun logout() {
        viewModelScope.launch {
            pref.clearToken()
            pref.clearUserName()
        }
    }
}