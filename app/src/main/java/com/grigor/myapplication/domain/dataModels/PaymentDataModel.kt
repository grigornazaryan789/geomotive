package com.grigor.myapplication.domain.dataModels

import androidx.annotation.Keep

@Keep
data class PaymentDataModel(val cardNumber: String?,
                            val cvv: String?,
                            val holderName: String?,
                            val amount: String?,
                            val expireDate: String?, val version: AppVersion)