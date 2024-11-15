package com.ihsanfrr.carita.data.pref

import java.util.Locale
import android.content.Context
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.runBlocking
import androidx.datastore.core.DataStore
import kotlinx.coroutines.flow.firstOrNull
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import androidx.datastore.preferences.core.stringPreferencesKey

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "CaritaPreferences")

class CaritaPreferences private constructor(private val dataStore: DataStore<Preferences>) {
    private val languageKey = stringPreferencesKey("language_setting")
    private val tokenKey = stringPreferencesKey("token")
    private val nameKey = stringPreferencesKey("name")

    fun fetchLanguageSetting(): Flow<String> =
        dataStore.data.map { it[languageKey] ?: "en" }

    suspend fun persistLanguageSetting(newLanguage: String) {
        dataStore.edit { prefs ->
            prefs[languageKey] = newLanguage
        }
    }

    fun fetchLanguageSettingSync(): String = runBlocking {
        dataStore.data.firstOrNull()?.get(languageKey) ?: Locale.getDefault().language
    }

    suspend fun persistTokenDetails(tokenValue: String, userName: String) {
        dataStore.edit { prefs ->
            prefs[tokenKey] = tokenValue
            prefs[nameKey] = userName
        }
    }

    fun retrieveToken(): Flow<String> =
        dataStore.data.map { it[tokenKey] ?: "" }

    fun retrieveUserName(): Flow<String> =
        dataStore.data.map { it[nameKey] ?: "" }

    suspend fun clearToken() {
        dataStore.edit { prefs ->
            prefs.remove(nameKey)
        }
    }

    suspend fun clearUserName() {
        dataStore.edit { prefs ->
            prefs.remove(nameKey)
        }
    }

    companion object {
        @Volatile
        private var instance: CaritaPreferences? = null
        fun initialize(dataStore: DataStore<Preferences>): CaritaPreferences =
            instance ?: synchronized(this) {
                instance ?: CaritaPreferences(dataStore).also { instance = it }
            }
    }
}