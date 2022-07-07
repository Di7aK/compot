package com.di7ak.compot.features.edit.model

import androidx.compose.runtime.Immutable
import com.di7ak.compot.domain.models.TodoModel
import com.di7ak.compot.common.UiState

@Immutable
data class EditUiState(
    val status: com.di7ak.compot.common.UiStatus = com.di7ak.compot.common.UiStatus.Idle,
    val todo: TodoModel = TodoModel(),
    val title: String = ""
): UiState