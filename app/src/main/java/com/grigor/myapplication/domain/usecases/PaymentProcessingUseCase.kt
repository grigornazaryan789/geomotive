package com.grigor.myapplication.domain.usecases

import com.grigor.myapplication.domain.dataModels.PaymentSuccessfulModel
import com.grigor.myapplication.domain.entities.PaymentModel

interface PaymentProcessingUseCase {
    suspend operator fun invoke(paymentModel: PaymentModel):PaymentSuccessfulModel?
}