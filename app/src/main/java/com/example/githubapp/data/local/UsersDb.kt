package com.example.githubapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.githubapp.domain.entities.UserEntity

@Database(entities = [UserEntity::class], version = 1)
abstract class UsersDb: RoomDatabase() {
    abstract fun usersDao(): UsersDao
}