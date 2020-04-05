package com.jaykallen.searchapi.managers

// todo update the deprecated items

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager

object SharedPrefsManager {
    private lateinit var preferences: SharedPreferences
    private lateinit var sharedprefs: SharedPreferences.Editor

    fun setTeam(context: Context, team: String) {
        preferences = PreferenceManager.getDefaultSharedPreferences(context)
        sharedprefs = preferences.edit()
        sharedprefs.putString("Team", team).apply()
    }

    fun getTeam(context: Context): String {
        preferences = PreferenceManager.getDefaultSharedPreferences(context)
        return preferences.getString("Team", "Arsenal")?:"Arsenal"  // 2nd value is default value.
    }
}
