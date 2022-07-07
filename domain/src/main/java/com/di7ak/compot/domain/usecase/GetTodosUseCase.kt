package com.di7ak.compot.domain.usecase

import com.di7ak.compot.domain.models.TodoModel
import com.di7ak.compot.domain.repositories.TodoRepository
import javax.inject.Inject

class GetTodosUseCase @Inject constructor(
    private val todoRepository: TodoRepository
) {

    suspend operator fun invoke(): List<TodoModel> {
        return todoRepository.fetchAll()
    }
}