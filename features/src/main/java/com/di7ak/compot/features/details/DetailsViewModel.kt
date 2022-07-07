package com.di7ak.compot.features.details

import androidx.lifecycle.viewModelScope
import com.di7ak.compot.common.BaseViewModel
import com.di7ak.compot.domain.models.TodoModel
import com.di7ak.compot.domain.usecase.DeleteTodoUseCase
import com.di7ak.compot.domain.usecase.FindTodoByIdUseCase
import com.di7ak.compot.features.details.model.DetailsUiAction
import com.di7ak.compot.features.details.model.DetailsUiEvent
import com.di7ak.compot.features.details.model.DetailsUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val findTodoByIdUseCase: FindTodoByIdUseCase,
    private val deleteTodoUseCase: DeleteTodoUseCase
): BaseViewModel<DetailsUiState, DetailsUiAction, DetailsUiEvent>(DetailsUiState()) {

    override fun obtainEvent(event: DetailsUiEvent) {
        when(event) {
            is DetailsUiEvent.LoadDetails -> loadDetails(event.id)

            is DetailsUiEvent.DeleteTodo -> deleteTodo(event.todo)
        }
    }

    private fun loadDetails(id: Int) {
        viewModelScope.launch {
            updateState { copy(status = com.di7ak.compot.common.UiStatus.Loading("load details")) }

            runCatching {
                findTodoByIdUseCase(id) ?: error("todo not found")
            }.onSuccess { todo ->
                updateState { copy(status = com.di7ak.compot.common.UiStatus.Ready, todo = todo) }
            }.onFailure {
                updateState { copy(status = com.di7ak.compot.common.UiStatus.Error(message = it.message ?: it.toString())) }
            }
        }
    }

    private fun deleteTodo(todo: TodoModel) {
        viewModelScope.launch {
            updateState { copy(status = com.di7ak.compot.common.UiStatus.Loading("deleting")) }

            runCatching {
                deleteTodoUseCase(todo)
            }.onSuccess {
                postAction(DetailsUiAction.Back)
            }.onFailure {
                updateState { copy(status = com.di7ak.compot.common.UiStatus.Error(message = it.message ?: it.toString())) }
            }
        }
    }
}