package com.example.mynews.data.manager

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import com.example.mynews.domain.manager.LocalUserManager
import com.example.mynews.util.Constants
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.util.prefs.Preferences

class LocalUserManagerImpl(
    private val context: Context
): LocalUserManager{
    override suspend fun saveAppEntry(){
        context.dataStore.edit {settings->
            settings[PreferencesKeys.APP_ENTRY]= true
        }
    }

    override fun readAppEntry(): Flow<Boolean> {
        return context.dataStore.data.map {preferences->
            preferences[PreferencesKeys.APP_ENTRY]?:false
        }
    }

}

private val Context.dataStore: DataStore<androidx.datastore.preferences.core.Preferences> by preferencesDataStore(
    name = Constants.USER_SETTINGS
)

private object PreferencesKeys{
    val APP_ENTRY = booleanPreferencesKey(name = Constants.APP_ENTRY)
}