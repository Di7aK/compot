package com.di7ak.compot.data.repository

import com.di7ak.compot.data.datasource.TodoDataSource
import com.di7ak.compot.data.entity.toTodoEntity
import com.di7ak.compot.data.entity.toTodoModel
import com.di7ak.compot.domain.models.TodoModel
import com.di7ak.compot.domain.repositories.TodoRepository
import javax.inject.Inject

internal class TodoRepositoryImpl @Inject constructor(
    private val dataSource: TodoDataSource
): TodoRepository {

    override suspend fun add(todo: TodoModel) {
        dataSource.insert(todo.toTodoEntity())
    }

    override suspend fun fetchAll(): List<TodoModel> {
        return dataSource.fetchAll().map { it.toTodoModel() }
    }

    override suspend fun delete(todo: TodoModel) {
        dataSource.delete(todo.toTodoEntity())
    }

    override suspend fun findById(id: Int): TodoModel? {
        return dataSource.findById(id)?.toTodoModel()
    }
}