package com.cahyadesthian.peoplelit.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "people_table")
data class People(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val firstName: String,
    val lastName: String,
    val age: Int
)
