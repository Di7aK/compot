package com.di7ak.compot.features.edit

import androidx.lifecycle.viewModelScope
import com.di7ak.compot.common.BaseViewModel
import com.di7ak.compot.domain.models.TodoModel
import com.di7ak.compot.domain.usecase.AddTodoUseCase
import com.di7ak.compot.domain.usecase.FindTodoByIdUseCase
import com.di7ak.compot.features.edit.model.EditUiAction
import com.di7ak.compot.features.edit.model.EditUiEvent
import com.di7ak.compot.features.edit.model.EditUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EditViewModel @Inject constructor(
    private val addTodoUseCase: AddTodoUseCase,
    private val findTodoByIdUseCase: FindTodoByIdUseCase
): BaseViewModel<EditUiState, EditUiAction, EditUiEvent>(EditUiState()) {

    override fun obtainEvent(event: EditUiEvent) {
        when(event) {
            is EditUiEvent.CreateTodo -> createTodo()
            is EditUiEvent.EditTodo -> loadTodo(event.id)
            is EditUiEvent.SaveTodo -> saveTodo()
            is EditUiEvent.UpdateTodo -> updateTodo(event.todo)
        }
    }

    private fun createTodo() {
        viewModelScope.launch {
            updateState { copy(status = com.di7ak.compot.common.UiStatus.Ready, title = "Create todo") }
        }
    }

    private fun loadTodo(id: Int) {
        viewModelScope.launch {
            updateState { copy(status = com.di7ak.compot.common.UiStatus.Loading("load todo")) }

            runCatching {
                findTodoByIdUseCase(id) ?: error("todo not found")
            }.onSuccess { todo ->
                updateState { copy(status = com.di7ak.compot.common.UiStatus.Ready, todo = todo, title = "Edit todo") }
            }.onFailure {
                updateState { copy(status = com.di7ak.compot.common.UiStatus.Error(it.message ?: it.toString())) }
            }
        }
    }

    private fun updateTodo(todo: TodoModel) {
        viewModelScope.launch {
            updateState { copy(todo = todo) }
        }
    }

    private fun saveTodo() {
        viewModelScope.launch {
            updateState { copy(status = com.di7ak.compot.common.UiStatus.Loading("save todo")) }

            runCatching {
                addTodoUseCase(uiState.value.todo)
            }.onSuccess {
                postAction(EditUiAction.Back)
            }.onFailure {
                updateState { copy(status = com.di7ak.compot.common.UiStatus.Error(it.message ?: it.toString())) }
            }
        }
    }
}