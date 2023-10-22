package com.example.testsandroid.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.testsandroid.data.local.model.Profile

@Database(entities = [Profile::class], version = 1)
abstract class ProfileDatabase: RoomDatabase() {
    abstract fun profileDao(): ProfileDao
}