package com.example.check.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.check.models.Todo

@Database(entities = [Todo::class], version = 1, exportSchema = true)
abstract class TodoDataBase: RoomDatabase() {
    abstract fun todoDao(): TodoDao
}