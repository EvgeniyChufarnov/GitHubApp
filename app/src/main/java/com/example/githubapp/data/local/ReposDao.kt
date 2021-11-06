package com.example.githubapp.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.githubapp.data.local.entities.ReposEntity

@Dao
interface ReposDao {
    @Query("SELECT * FROM repos WHERE owner = :owner")
    fun getRepos(owner: String): ReposEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun cacheRepos(repos: ReposEntity)
}