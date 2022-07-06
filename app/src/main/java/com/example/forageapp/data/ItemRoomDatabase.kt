package com.example.forageapp.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.forageapp.model.Item

@Database(entities =[Item::class], version = 1, exportSchema = false)
abstract class ItemRoomDatabase : RoomDatabase() {
    abstract val foragableDao: ItemDao
}