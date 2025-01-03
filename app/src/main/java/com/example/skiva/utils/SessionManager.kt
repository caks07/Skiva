package com.example.skiva.utils

import android.content.Context
import android.content.SharedPreferences

class SessionManager(context: Context) {
    private val sharedPreferences: SharedPreferences =
        context.getSharedPreferences("USER_SESSION", Context.MODE_PRIVATE)

    fun saveUserId(userId: String) {
        sharedPreferences.edit().putString("USER_ID", userId).apply()
    }

    fun getUserId(): String? {
        return sharedPreferences.getString("USER_ID", null)
    }

    fun saveUserName(userName: String) {
        sharedPreferences.edit().putString("USER_NAME", userName).apply()
    }

    fun getUserName(): String? {
        return sharedPreferences.getString("USER_NAME", null)
    }

    fun clearSession() {
        sharedPreferences.edit().clear().apply()
    }
}
