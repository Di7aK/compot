package com.di7ak.compot.features.main

import androidx.lifecycle.viewModelScope
import com.di7ak.compot.domain.models.TodoModel
import com.di7ak.compot.domain.usecase.GetTodosUseCase
import com.di7ak.compot.common.BaseViewModel
import com.di7ak.compot.features.details.DetailsScreen
import com.di7ak.compot.features.edit.EditScreen
import com.di7ak.compot.features.main.model.MainUiAction
import com.di7ak.compot.features.main.model.MainUiEvent
import com.di7ak.compot.features.main.model.MainUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getTodosUseCase: GetTodosUseCase
): BaseViewModel<MainUiState, MainUiAction, MainUiEvent>(MainUiState()) {

    override fun obtainEvent(event: MainUiEvent) {
        when(event) {
            is MainUiEvent.LoadTodos -> loadList()
            is MainUiEvent.OpenTodo -> openTodo(event.todo)
            is MainUiEvent.CreateTodo -> createTodo()
            is MainUiEvent.EditTodo -> editTodo(event.todo)
        }
    }

    private fun loadList() {
        viewModelScope.launch {
            updateState { copy(status = com.di7ak.compot.common.UiStatus.Loading("load todos")) }

            runCatching {
                getTodosUseCase()
            }.onSuccess { todos ->
                updateState { copy(status = com.di7ak.compot.common.UiStatus.Ready, todos = todos) }
            }.onFailure {
                updateState { copy(status = com.di7ak.compot.common.UiStatus.Error(it.message ?: it.toString())) }
            }
        }
    }

    private fun openTodo(todo: TodoModel) {
        viewModelScope.launch {
            postAction(MainUiAction.Navigate(DetailsScreen(todo.id)))
        }
    }

    private fun editTodo(todo: TodoModel) {
        viewModelScope.launch {
            postAction(MainUiAction.Navigate(EditScreen(todo.id)))
        }
    }

    private fun createTodo() {
        viewModelScope.launch {
            postAction(MainUiAction.Navigate(EditScreen()))
        }
    }
}