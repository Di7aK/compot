package com.di7ak.compot.features.main.model

import com.di7ak.compot.domain.models.TodoModel
import com.di7ak.compot.common.UiEvent

sealed class MainUiEvent: UiEvent {
    object LoadTodos: MainUiEvent()
    object CreateTodo: MainUiEvent()
    data class OpenTodo(val todo: TodoModel): MainUiEvent()
    data class EditTodo(val todo: TodoModel): MainUiEvent()
}