package com.di7ak.compot.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.di7ak.compot.data.datasource.TodoDataSource
import com.di7ak.compot.data.entity.TodoEntity

@Database(
    entities = [
        TodoEntity::class
    ],
    version = 1,
    exportSchema = true
)
internal abstract class AppDatabase : RoomDatabase() {
    abstract fun getTodoDao(): TodoDataSource
}