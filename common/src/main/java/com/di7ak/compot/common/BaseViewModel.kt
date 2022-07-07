package com.di7ak.compot.common

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

abstract class BaseViewModel<State: UiState, Action: UiAction, Event: UiEvent>(initialState: State) : ViewModel() {
    private val _uiState = MutableStateFlow(initialState)
    val uiState = _uiState.asStateFlow()

    private val _uiAction = Channel<Action>()
    val uiAction = _uiAction.receiveAsFlow()

    private val uiEvent = MutableSharedFlow<Event>()

    init {
        subscribeEvents()
    }

    private fun subscribeEvents() {
        uiEvent.onEach(::obtainEvent).launchIn(viewModelScope)
    }

    protected fun updateState(reducer: State.() -> State) {
        _uiState.value = reducer(_uiState.value)
    }

    protected fun postAction(action: Action) = viewModelScope.launch {
        _uiAction.send(action)
    }

    fun setEvent(event: Event) {
        viewModelScope.launch {
            uiEvent.emit(event)
        }
    }

    protected abstract fun obtainEvent(event: Event)
}

interface UiState

interface UiAction

interface UiEvent