package com.grigor.myapplication.domain.repositories

import com.grigor.myapplication.domain.dataModels.PaymentDataModel
import com.grigor.myapplication.domain.dataModels.PaymentSuccessfulModel
import com.grigor.myapplication.domain.dataModels.ResponseModel

interface PaymentRepository {
  suspend fun processPayment(paymentDataModel: PaymentDataModel):ResponseModel<PaymentSuccessfulModel>
}