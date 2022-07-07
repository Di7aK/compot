package com.di7ak.compot.features.main.model

import androidx.compose.runtime.Immutable
import com.di7ak.compot.domain.models.TodoModel
import com.di7ak.compot.common.UiState

@Immutable
data class MainUiState(
    val status: com.di7ak.compot.common.UiStatus = com.di7ak.compot.common.UiStatus.Idle,
    val todos: List<TodoModel> = emptyList()
): UiState