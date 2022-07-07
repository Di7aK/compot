package com.di7ak.compot.root

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import cafe.adriel.voyager.navigator.Navigator
import com.di7ak.compot.features.main.MainScreen
import com.di7ak.compot.ui.theme.CompotTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            CompotTheme {
                Navigator(MainScreen)
            }
        }
    }
}