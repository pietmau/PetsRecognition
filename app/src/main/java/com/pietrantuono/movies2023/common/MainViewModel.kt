package com.pietrantuono.movies2023.common

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import java.util.function.Consumer
import kotlin.coroutines.CoroutineContext
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

abstract class RedditViewModel<ViewState, UserAction>(
    private val coroutineContext: CoroutineContext,
) : ViewModel(), Consumer<UserAction> {

    abstract val _viewState: MutableStateFlow<ViewState>
    val viewState: Flow<ViewState>
        get() = _viewState

    protected fun launch(block: suspend CoroutineScope.() -> Unit) {
        viewModelScope.launch(coroutineContext) {
            try {
                block()
            } catch (e: Exception) {
                // TODO: Handle error
            }
        }
    }
}