package com.di7ak.compot.common

sealed class UiStatus {
    object Idle: UiStatus()
    data class Loading(val message: String) : UiStatus()
    object Ready : UiStatus()
    data class Error(val message: String) : UiStatus()
}