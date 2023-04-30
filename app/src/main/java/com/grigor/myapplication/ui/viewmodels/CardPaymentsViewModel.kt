package com.grigor.myapplication.ui.viewmodels

import androidx.lifecycle.viewModelScope
import com.grigor.myapplication.domain.dataModels.PaymentSuccessfulModel
import com.grigor.myapplication.domain.entities.PaymentModel
import com.grigor.myapplication.domain.usecases.PaymentProcessingUseCase
import com.grigor.myapplication.ui.base.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch

class CardPaymentsViewModel(private val paymentProcessingUseCase: PaymentProcessingUseCase) :
    BaseViewModel() {

    private val paymentProcessingSharedFlow = MutableSharedFlow<PaymentSuccessfulModel?>()
    val mPaymentProcessingSharedFlow get() = paymentProcessingSharedFlow

    fun processPayment(paymentModel: PaymentModel) {
        viewModelScope.launch(Dispatchers.IO + coroutineExceptionHandler) {
            showHideLoading(true)
            val response = paymentProcessingUseCase.invoke(paymentModel)
            paymentProcessingSharedFlow.emit(response)
            paymentProcessingSharedFlow.emit(null)
            showHideLoading(false)
        }
    }
}