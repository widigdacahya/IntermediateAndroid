package com.cahyadesthian.chystoryapp.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("remote_key")
data class RemoteKeyEntity(
    @PrimaryKey
    val id: String,
    val prevKey:Int?,
    val nextKey:Int?
)
