package com.example.so79736786

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onEmpty
import kotlinx.coroutines.flow.onStart

@Composable
fun MyScreen(viewModel: ProblemViewModel = viewModel()) {

    val uiState by viewModel.uiState
        .onEach { Log.i("RGUPTACS", "MyScreen: State flow has emitted.") }
        .onStart { Log.i("RGUPTACS", "MyScreen: State flow has started.") }
        .onCompletion { Log.i("RGUPTACS", "MyScreen: State flow has completed.") }
        .onEmpty { Log.i("RGUPTACS", "MyScreen: State flow is Empty.") }
        .collectAsState("Loading...")

//    val uiState by viewModel.uiState.collectAsState()

    DisposableEffect(Unit) {
        Log.i("RGUPTACS", "MyScreen: My Screen entered the composition")
        onDispose {
            Log.i("RGUPTACS", "MyScreen: My Screen removed from the composition")
        }
    }

    Column (
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = uiState, style = MaterialTheme.typography.headlineLarge)
    }
}