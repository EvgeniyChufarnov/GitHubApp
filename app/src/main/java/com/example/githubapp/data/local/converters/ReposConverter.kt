package com.example.githubapp.data.local.converters

import androidx.room.TypeConverter
import com.example.githubapp.data.local.entities.CachedRepos
import com.google.gson.Gson

class ReposConverter {
    @TypeConverter
    fun fromCachedRepos(json: String): CachedRepos {
        return Gson().fromJson(json, CachedRepos::class.java)
    }

    @TypeConverter
    fun toCachedRepos(cachedRepos: CachedRepos): String {
        return Gson().toJson(cachedRepos)
    }
}