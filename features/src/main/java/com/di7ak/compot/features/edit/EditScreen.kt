package com.di7ak.compot.features.edit

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Check
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.androidx.AndroidScreen
import cafe.adriel.voyager.hilt.getViewModel
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.di7ak.compot.common.UiStatus
import com.di7ak.compot.ui.components.UiStatusView
import com.di7ak.compot.features.edit.model.EditUiAction
import com.di7ak.compot.features.edit.model.EditUiEvent
import com.di7ak.compot.features.edit.views.EditScreenView

class EditScreen(private val id: Int = 0): AndroidScreen() {

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        val viewModel = getViewModel<EditViewModel>()
        val uiState by viewModel.uiState.collectAsState()

        Scaffold(
            topBar = {
                CenterAlignedTopAppBar(
                    title = { Text(text = uiState.title) },
                    actions = {
                        IconButton(onClick = { viewModel.setEvent(EditUiEvent.SaveTodo) }) {
                            Icon(
                                imageVector = Icons.Outlined.Check,
                                contentDescription = "Save",
                                tint = MaterialTheme.colorScheme.onSurface
                            )
                        }
                    }
                )
            },
        ) { padding ->
            when (val status = uiState.status) {
                is UiStatus.Ready -> {
                    EditScreenView(
                        modifier = Modifier
                            .padding(padding)
                            .fillMaxSize(),
                        todo = uiState.todo
                    ) { todo ->
                        viewModel.setEvent(EditUiEvent.UpdateTodo(todo))
                    }
                }

                else -> {
                    UiStatusView(
                        modifier = Modifier
                            .padding(padding),
                        status = status
                    )
                }
            }
        }

        LaunchedEffect(key1 = viewModel, block = {
            viewModel.uiAction.collect { action ->
                when (action) {
                    is EditUiAction.Back -> navigator.pop()
                }
            }
        })

        LaunchedEffect(key1 = id, block = {
            if (id == 0) viewModel.setEvent(EditUiEvent.CreateTodo)
            else viewModel.setEvent(EditUiEvent.EditTodo(id))
        })
    }
}