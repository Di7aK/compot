package com.di7ak.compot.features.main.model

import cafe.adriel.voyager.core.screen.Screen
import com.di7ak.compot.common.UiAction

sealed class MainUiAction: UiAction {
    data class Navigate(val screen: Screen): MainUiAction()
}