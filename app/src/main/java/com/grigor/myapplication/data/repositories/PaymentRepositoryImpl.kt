package com.grigor.myapplication.data.repositories

import com.grigor.myapplication.data.services.MockPaymentService
import com.grigor.myapplication.domain.dataModels.PaymentDataModel
import com.grigor.myapplication.domain.dataModels.PaymentSuccessfulModel
import com.grigor.myapplication.domain.dataModels.ResponseModel
import com.grigor.myapplication.domain.repositories.PaymentRepository

class PaymentRepositoryImpl(private val mockPaymentService:MockPaymentService):PaymentRepository {

    override suspend fun processPayment(paymentDataModel: PaymentDataModel): ResponseModel<PaymentSuccessfulModel> {
      return mockPaymentService.processPayment(paymentDataModel)
    }
}