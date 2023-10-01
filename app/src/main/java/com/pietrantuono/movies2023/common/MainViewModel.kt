package com.pietrantuono.movies2023.common

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import java.util.function.Consumer
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

abstract class RedditViewModel<ViewState, UserAction> @Inject constructor(
    private val coroutineContext: CoroutineContext,
) : ViewModel(), Consumer<UserAction> {

    abstract val _uiState: MutableStateFlow<ViewState>
    val uiState: Flow<ViewState>
        get() = _uiState

    protected fun updateState(reducer: ViewState.() -> ViewState) {
        val currentState = _uiState.value ?: return
        val newState = currentState.reducer()
        if (newState != currentState) {
            _uiState.value = newState
        }
    }

    protected fun launch(block: suspend CoroutineScope.() -> Unit) {
        viewModelScope.launch(coroutineContext) {
            try {
                block()
            } catch (e: Exception) {
                // TODO
            }
        }
    }
}