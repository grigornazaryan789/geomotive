package com.grigor.myapplication.domain.usecase_impls

import com.grigor.myapplication.domain.dataModels.PaymentSuccessfulModel
import com.grigor.myapplication.domain.dataModels.ResponseModel
import com.grigor.myapplication.domain.entities.PaymentModel
import com.grigor.myapplication.domain.mappers.mapToPaymentDataModel
import com.grigor.myapplication.domain.repositories.PaymentRepository
import com.grigor.myapplication.domain.usecases.PaymentProcessingUseCase

class PaymentProcessingUseCaseImpl(private val paymentRepository: PaymentRepository) : PaymentProcessingUseCase {

    override suspend fun invoke(paymentModel: PaymentModel): PaymentSuccessfulModel? {
       val paymentDataModel= paymentModel.mapToPaymentDataModel()
        return when(val response=paymentRepository.processPayment(paymentDataModel)){
            is ResponseModel.Success -> {
                return response.data
            }
            is ResponseModel.Error -> {
                throw response.error
            }
        }
    }
}