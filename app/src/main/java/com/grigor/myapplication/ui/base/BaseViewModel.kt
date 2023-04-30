package com.grigor.myapplication.ui.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

abstract class BaseViewModel :ViewModel(){

    private val baseExceptionSharedFlow = MutableSharedFlow<Throwable?>(1)
    val mBaseExceptionSharedFlow get() = baseExceptionSharedFlow.distinctUntilChanged()

    private val baseLoadingSharedFlow = MutableSharedFlow<Boolean>(1)
    val mBaseLoadingSharedFlow get() = baseLoadingSharedFlow.distinctUntilChanged()

    protected val coroutineExceptionHandler =
        CoroutineExceptionHandler { _: CoroutineContext, throwable: Throwable ->
            viewModelScope.launch(Dispatchers.IO) {
                showHideLoading(false)
                baseExceptionSharedFlow.emit(throwable)
                baseExceptionSharedFlow.emit(null)
            }
        }

    protected suspend fun showHideLoading(isLoading:Boolean){
        baseLoadingSharedFlow.emit(isLoading)
    }
}