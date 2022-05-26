package com.cahyadesthian.chystoryapp.screen.util

import androidx.datastore.core.DataStore
import androidx.datastore.dataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.map

class SessionDataPreference private constructor(private val sessionDataStore: DataStore<Preferences>) {

    private val keyToken = stringPreferencesKey("token")

    fun getStoredToken() = sessionDataStore.data.map {
        it[keyToken] ?: ""
    }

    suspend fun storeToken(userToken: String) {
        sessionDataStore.edit {
            it[keyToken] = userToken
        }
    }

    companion object {
        @Volatile
        private var DATASTOREINSTANCE: SessionDataPreference? = null

        fun getDataStoreInstance(dataStore: DataStore<Preferences>) = DATASTOREINSTANCE?: synchronized(this) {
            SessionDataPreference(dataStore)
        }.also { DATASTOREINSTANCE = it }
    }

}