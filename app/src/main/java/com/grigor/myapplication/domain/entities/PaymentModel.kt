package com.grigor.myapplication.domain.entities

data class PaymentModel(
    val cardNumber: String?,
    val cvv: String?,
    val holderName: String?,
    val amount: String?,
    val expireDate: String?
)