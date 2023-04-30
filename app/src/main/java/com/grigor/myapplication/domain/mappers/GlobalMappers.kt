package com.grigor.myapplication.domain.mappers

import com.grigor.myapplication.BuildConfig
import com.grigor.myapplication.domain.dataModels.AppVersion
import com.grigor.myapplication.domain.dataModels.PaymentDataModel
import com.grigor.myapplication.domain.entities.PaymentModel

fun PaymentModel.mapToPaymentDataModel(): PaymentDataModel {
    return PaymentDataModel(
        cardNumber, cvv, holderName, amount, expireDate,
        AppVersion(BuildConfig.VERSION_CODE, BuildConfig.VERSION_NAME)
    )
}