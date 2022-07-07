package com.di7ak.compot.domain.usecase

import com.di7ak.compot.domain.models.TodoModel
import com.di7ak.compot.domain.repositories.TodoRepository
import javax.inject.Inject

class FindTodoByIdUseCase @Inject constructor(
    private val todoRepository: TodoRepository
) {

    suspend operator fun invoke(id: Int): TodoModel? {
        return todoRepository.findById(id)
    }
}