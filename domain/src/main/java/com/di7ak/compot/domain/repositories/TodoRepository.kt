package com.di7ak.compot.domain.repositories

import com.di7ak.compot.domain.models.TodoModel

interface TodoRepository {

    suspend fun add(todo: TodoModel)

    suspend fun fetchAll(): List<TodoModel>

    suspend fun delete(todo: TodoModel)

    suspend fun findById(id: Int): TodoModel?
}