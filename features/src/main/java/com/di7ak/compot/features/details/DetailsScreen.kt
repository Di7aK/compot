package com.di7ak.compot.features.details

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.androidx.AndroidScreen
import cafe.adriel.voyager.hilt.getViewModel
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.di7ak.compot.common.UiStatus
import com.di7ak.compot.ui.components.UiStatusView
import com.di7ak.compot.features.details.model.DetailsUiAction
import com.di7ak.compot.features.details.model.DetailsUiEvent
import com.di7ak.compot.features.details.views.DetailsScreenView

class DetailsScreen(private val id: Int): AndroidScreen() {

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        val viewModel = getViewModel<DetailsViewModel>()
        val uiState by viewModel.uiState.collectAsState()

        Scaffold(
            topBar = {
                CenterAlignedTopAppBar(
                    title = { Text(text = uiState.todo.name) },
                    actions = {
                        IconButton(onClick = { viewModel.setEvent(DetailsUiEvent.DeleteTodo(uiState.todo)) }) {
                            Icon(
                                imageVector = Icons.Outlined.Delete,
                                contentDescription = "Delete",
                                tint = MaterialTheme.colorScheme.onSurface
                            )
                        }
                    }
                )
            }
        ) { padding ->
            when (val status = uiState.status) {
                is UiStatus.Ready -> {
                    DetailsScreenView(modifier = Modifier.padding(padding).fillMaxSize(), todo = uiState.todo)
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
                    is DetailsUiAction.Back -> navigator.pop()
                }
            }
        })

        LaunchedEffect(key1 = id, block = {
            viewModel.setEvent(DetailsUiEvent.LoadDetails(id))
        })
    }
}