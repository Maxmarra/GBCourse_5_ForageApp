package com.example.forageapp.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.forageapp.model.Forageable

@Database(
    entities =[Forageable::class],
    version = 1,
    exportSchema = false
)
abstract class ForageDatabase : RoomDatabase() {
    abstract val foragableDao: ForageableDao
}