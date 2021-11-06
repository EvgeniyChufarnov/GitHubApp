package com.example.githubapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.githubapp.data.local.converters.ReposConverter
import com.example.githubapp.data.local.entities.ReposEntity
import com.example.githubapp.domain.entities.UserEntity

@Database(entities = [ReposEntity::class], version = 1)
@TypeConverters(ReposConverter::class)
abstract class ReposDb: RoomDatabase() {
    abstract fun reposDao(): ReposDao
}