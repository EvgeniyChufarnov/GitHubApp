package com.example.githubapp.domain.entities

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class RepoEntity(
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String,
    @SerializedName("forks") val forks: Int
) : Parcelable