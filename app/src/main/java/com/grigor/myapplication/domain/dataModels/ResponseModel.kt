package com.grigor.myapplication.domain.dataModels

sealed class ResponseModel<T>{
    class Success<T>(val data: T) : ResponseModel<T>()
    class Error<E>(val error: Throwable) : ResponseModel<E>()
}
