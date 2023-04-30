package com.grigor.myapplication.domain.exceptions

sealed interface PaymentExceptions{
    object AmountException : PaymentExceptions, Throwable(message = "Invalid amount")
    object InvalidCVVException : PaymentExceptions, Throwable(message = "Invalid cvv")
    object ExpiryDateException:PaymentExceptions, Throwable(message = "Invalid expiry date")
    object InvalidCardException:PaymentExceptions, Throwable(message = "Invalid card")

}