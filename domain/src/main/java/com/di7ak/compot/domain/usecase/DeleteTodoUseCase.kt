package com.di7ak.compot.domain.usecase

import com.di7ak.compot.domain.models.TodoModel
import com.di7ak.compot.domain.repositories.TodoRepository
import javax.inject.Inject

class DeleteTodoUseCase @Inject constructor(
    private val todoRepository: TodoRepository
) {

    suspend operator fun invoke(todo: TodoModel) {
        return todoRepository.delete(todo)
    }
}