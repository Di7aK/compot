package com.di7ak.compot.features.main

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.androidx.AndroidScreen
import cafe.adriel.voyager.hilt.getViewModel
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.di7ak.compot.common.UiStatus
import com.di7ak.compot.ui.components.*
import com.di7ak.compot.features.main.model.MainUiAction
import com.di7ak.compot.features.main.model.MainUiEvent
import com.di7ak.compot.features.main.views.MainScreenView

object MainScreen: AndroidScreen() {

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        val viewModel = getViewModel<MainViewModel>()
        val uiState by viewModel.uiState.collectAsState()

        Scaffold(
            topBar = {
                CenterAlignedTopAppBar(
                    title = { Text(text = "Todo") },
                )
            },
            floatingActionButton = {
                ExtendedFloatingActionButton(
                    onClick = { viewModel.setEvent(MainUiEvent.CreateTodo) },
                    icon = { Icon(imageVector = Icons.Outlined.Add, contentDescription = "Add") },
                    text = { Text("Add") }
                )
            },
            floatingActionButtonPosition = FabPosition.End
        ) { padding ->
            when (val status = uiState.status) {
                is UiStatus.Ready -> {
                    MainScreenView(
                        modifier = Modifier
                            .padding(padding)
                            .fillMaxSize(),
                        todos = uiState.todos,
                        onTodoClick = { todo -> viewModel.setEvent(MainUiEvent.OpenTodo(todo = todo)) },
                        onTodoEditClick = { todo -> viewModel.setEvent(MainUiEvent.EditTodo(todo = todo)) }
                    )
                }

                else -> {
                    UiStatusView(
                        modifier = Modifier
                            .padding(padding)
                            .fillMaxSize(),
                        status = status
                    )
                }
            }
        }

        LaunchedEffect(key1 = viewModel, block = {
            viewModel.uiAction.collect { action ->
                when (action) {
                    is MainUiAction.Navigate -> navigator.push(action.screen)
                }
            }
        })

        LaunchedEffect(key1 = Unit, block = {
            viewModel.setEvent(MainUiEvent.LoadTodos)
        })
    }
}
