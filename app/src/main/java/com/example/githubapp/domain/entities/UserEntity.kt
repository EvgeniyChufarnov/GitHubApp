package com.example.githubapp.domain.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "users")
data class UserEntity(
    @PrimaryKey
    @ColumnInfo(name = "login")
    @SerializedName("login")
    val login: String,

    @ColumnInfo(name = "avatar_url")
    @SerializedName("avatar_url")
    val avatarUrl: String,
)
