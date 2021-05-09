package com.myapplication.bootcamp.carapp.data.local.prefs

import android.content.SharedPreferences
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserPreferences @Inject constructor(private val prefs: SharedPreferences) {

    companion object {
        const val KEY_USER_ID = "PREF_KEY_USER_ID"
        const val KEY_USER_NAME = "PREF_KEY_USER_NAME"
        const val KEY_USER_EMAIL = "PREF_KEY_USER_EMAIL"
        const val KEY_ACCESS_TOKEN = "PREF_KEY_ACCESS_TOKEN"
        const val KEY_AUTH_TOKEN = "PREF_KEY_AUTH_TOKEN"
        const val KEY_PERSON_KEY = "PREF_KEY_PERSON_KEY"
        const val KEY_DISPLAY_NAME = "PREF_KEY_DISPLAY_NAME"
    }

    fun getUserId(): String? =
        prefs.getString(KEY_USER_ID, null)

    fun setUserId(userId: String) =
        prefs.edit().putString(KEY_USER_ID, userId).apply()

    fun getAuthKey(): String? =
        prefs.getString(KEY_AUTH_TOKEN, null)

    fun setAuthKey(authKey: String) =
        prefs.edit().putString(KEY_AUTH_TOKEN, authKey).apply()

    fun removeAuthKey() =
        prefs.edit().remove(KEY_AUTH_TOKEN).apply()

    fun getPersonKey(): String? =
        prefs.getString(KEY_PERSON_KEY, null)

    fun setPersonKey(personKey: String) =
        prefs.edit().putString(KEY_PERSON_KEY, personKey).apply()

    fun removePersonKey() =
        prefs.edit().remove(KEY_PERSON_KEY).apply()

    fun getDisplayName(): String? =
        prefs.getString(KEY_DISPLAY_NAME, null)

    fun setDisplayName(displayName: String) =
        prefs.edit().putString(KEY_DISPLAY_NAME, displayName).apply()

    fun removeDisplayName() =
        prefs.edit().remove(KEY_DISPLAY_NAME).apply()

    fun removeUserId() =
        prefs.edit().remove(KEY_USER_ID).apply()

    fun getUserName(): String? =
        prefs.getString(KEY_USER_NAME, null)

    fun setUserName(userName: String) =
        prefs.edit().putString(KEY_USER_NAME, userName).apply()

    fun removeUserName() =
        prefs.edit().remove(KEY_USER_NAME).apply()

    fun getUserEmail(): String? =
        prefs.getString(KEY_USER_EMAIL, null)

    fun setUserEmail(email: String) =
        prefs.edit().putString(KEY_USER_EMAIL, email).apply()

    fun removeUserEmail() =
        prefs.edit().remove(KEY_USER_EMAIL).apply()

    fun getAccessToken(): String? =
        prefs.getString(KEY_ACCESS_TOKEN, null)

    fun setAccessToken(token: String) =
        prefs.edit().putString(KEY_ACCESS_TOKEN, token).apply()

    fun removeAccessToken() =
        prefs.edit().remove(KEY_ACCESS_TOKEN).apply()
}