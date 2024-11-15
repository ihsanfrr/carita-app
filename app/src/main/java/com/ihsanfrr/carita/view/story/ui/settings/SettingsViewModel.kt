package com.ihsanfrr.carita.view.story.ui.settings

import kotlinx.coroutines.launch
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.ihsanfrr.carita.data.pref.CaritaPreferences

class SettingsViewModel(private val pref: CaritaPreferences) : ViewModel() {
    fun getLanguageSettings(): LiveData<String> {
        return pref.fetchLanguageSetting().asLiveData()
    }

    fun saveLanguageSettings(language: String) {
        viewModelScope.launch {
            pref.persistLanguageSetting(language)
        }
    }
}