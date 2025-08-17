package com.example.so79736786

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onEmpty
import kotlinx.coroutines.flow.onSubscription
import kotlinx.coroutines.launch

class ProblemViewModel : ViewModel() {
    private val _uiState = MutableStateFlow("Loading...")
    val uiState = _uiState.asStateFlow()

    init {
        Log.i("RGUPTACS", "View model is initiated")
        monitorSubscriptions()
        viewModelScope.launch {
            DataRepository.getDataStream().collect { data ->
                // I might be doing other important work here, not just updating state
                Log.i("RGUPTACS", "LOG: Important work being done with data: $data")
                _uiState.value = "Item: $data"
            }
        }.invokeOnCompletion {
            Log.i("RGUPTACS", "view model job is cancelled")
        }
    }

    private fun monitorSubscriptions() {
        _uiState.subscriptionCount
            .onEach { count ->
                Log.i("RGUPTACS", "Active subscriber count: $count")
                if (count > 0) {
                    Log.i("RGUPTACS", "First subscriber joined, starting work...")
                } else {
                    Log.i("RGUPTACS", "Last subscriber left, stopping work.")
                }
            }
            .launchIn(viewModelScope) // Launch the collection in the viewModelScope
    }

    override fun onCleared() {
        super.onCleared()
        Log.i("RGUPTACS", "View model is cleared")
    }
}