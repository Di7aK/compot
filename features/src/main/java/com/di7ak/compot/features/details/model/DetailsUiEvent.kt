package com.di7ak.compot.features.details.model

import com.di7ak.compot.domain.models.TodoModel
import com.di7ak.compot.common.UiEvent

sealed class DetailsUiEvent: UiEvent {
    data class LoadDetails(val id: Int) : DetailsUiEvent()
    data class DeleteTodo(val todo: TodoModel) : DetailsUiEvent()
}