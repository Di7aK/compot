package com.di7ak.compot.features.edit.model

import com.di7ak.compot.domain.models.TodoModel
import com.di7ak.compot.common.UiEvent

sealed class EditUiEvent: UiEvent {
    object CreateTodo: EditUiEvent()
    data class EditTodo(val id: Int): EditUiEvent()
    object SaveTodo: EditUiEvent()
    data class UpdateTodo(val todo: TodoModel): EditUiEvent()
}