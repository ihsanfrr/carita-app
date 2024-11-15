package com.ihsanfrr.carita.data.pref

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

@Suppress("UNCHECKED_CAST")
class SessionViewModelFactory(
    private val pref: CaritaPreferences
) : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SessionViewModel::class.java)) {
            return SessionViewModel(pref) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
    }

    companion object {
        @Volatile
        private var instance: SessionViewModelFactory? = null
        fun getInstance(context: Context, pref: CaritaPreferences): SessionViewModelFactory =
            instance ?: synchronized(this) {
                instance ?: SessionViewModelFactory(pref)
            }.also { instance = it }
    }
}