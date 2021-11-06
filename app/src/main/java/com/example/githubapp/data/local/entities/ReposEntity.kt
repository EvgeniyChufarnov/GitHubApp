package com.example.githubapp.data.local.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.githubapp.domain.entities.RepoEntity

@Entity(tableName = "repos")
data class ReposEntity(
    @PrimaryKey
    @ColumnInfo(name = "owner")
    val owner: String,
    @ColumnInfo(name = "repos")
    val cachedRepos: CachedRepos
)

data class CachedRepos(
    val value: List<RepoEntity>
)