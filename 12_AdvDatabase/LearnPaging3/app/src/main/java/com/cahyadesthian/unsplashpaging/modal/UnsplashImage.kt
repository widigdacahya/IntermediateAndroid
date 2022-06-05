package com.cahyadesthian.unsplashpaging.modal

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.cahyadesthian.unsplashpaging.util.Constants.UNSPLASH_IMAGE_TABLE
import kotlinx.serialization.Serializable

@Serializable
@Entity(tableName = UNSPLASH_IMAGE_TABLE)
data class UnsplashImage(
    @PrimaryKey(autoGenerate = false)
    val id: String,
    @Embedded
    val urls: Urls,
    val likes: Int,
    @Embedded
    val user: User
)
