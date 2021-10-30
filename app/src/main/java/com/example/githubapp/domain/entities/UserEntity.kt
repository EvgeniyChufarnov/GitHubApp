package com.example.githubapp.domain.entities

import com.google.gson.annotations.SerializedName

data class UserEntity(
    @SerializedName("login") val login: String,
    @SerializedName("avatar_url") val avatarUrl: String,
)
