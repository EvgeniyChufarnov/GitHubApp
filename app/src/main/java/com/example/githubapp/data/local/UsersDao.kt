package com.example.githubapp.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.githubapp.domain.entities.UserEntity

@Dao
interface UsersDao {
    @Query("SELECT * FROM users")
    fun getUsers(): List<UserEntity>

    @Insert()
    fun cacheUsers(users: List<UserEntity>)

    @Query("DELETE FROM users")
    fun clear()
}