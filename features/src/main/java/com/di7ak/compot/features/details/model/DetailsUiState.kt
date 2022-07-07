package com.di7ak.compot.features.details.model

import androidx.compose.runtime.Immutable
import com.di7ak.compot.common.UiState
import com.di7ak.compot.domain.models.TodoModel

@Immutable
data class DetailsUiState(
    val status: com.di7ak.compot.common.UiStatus = com.di7ak.compot.common.UiStatus.Idle,
    val todo: TodoModel = TodoModel()
) : UiState