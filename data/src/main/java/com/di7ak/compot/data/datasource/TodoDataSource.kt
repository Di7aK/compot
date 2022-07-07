package com.di7ak.compot.data.datasource

import androidx.room.*
import com.di7ak.compot.data.entity.TodoEntity

@Dao
internal interface TodoDataSource {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(todoEntity: TodoEntity)

    @Delete
    suspend fun delete(todoEntity: TodoEntity)

    @Query("SELECT * FROM todos")
    suspend fun fetchAll(): List<TodoEntity>

    @Query("SELECT * FROM todos where id = :id")
    suspend fun findById(id: Int): TodoEntity?
}