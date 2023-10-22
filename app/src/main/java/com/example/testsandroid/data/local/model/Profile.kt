package com.example.testsandroid.data.local.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName= "profile")
class Profile(
    @PrimaryKey var id: Int,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "gender") val gender: String
)
