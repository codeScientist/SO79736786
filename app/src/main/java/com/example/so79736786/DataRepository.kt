package com.example.so79736786

import android.util.Log
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onEmpty
import kotlinx.coroutines.flow.onSubscription

object DataRepository {
    fun getDataStream(): Flow<Int> {
        return flow {
            var count = 0
            while (true) {
                emit(count++)
                delay(6000)
            }
        }
        .onStart { Log.i("RGUPTACS", "DataRepository: Cold flow has started.") }
        .onEach { Log.i("RGUPTACS", "DataRepository: Cold flow has emitted.") }
        .onEmpty { Log.i("RGUPTACS", "DataRepository: Cold flow is Empty.") }
        .onCompletion { Log.i("RGUPTACS", "DataRepository: Cold flow has completed.") }
    }
}