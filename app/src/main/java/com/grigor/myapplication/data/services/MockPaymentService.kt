package com.grigor.myapplication.data.services

import com.grigor.myapplication.domain.dataModels.PaymentDataModel
import com.grigor.myapplication.domain.dataModels.PaymentSuccessfulModel
import com.grigor.myapplication.domain.dataModels.ResponseModel
import com.grigor.myapplication.domain.exceptions.PaymentExceptions
import kotlinx.coroutines.delay
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class MockPaymentService {
    suspend fun processPayment(paymentDataModel: PaymentDataModel): ResponseModel<PaymentSuccessfulModel> {
        delay(1500)
        return when {
            paymentDataModel.amount=="0"->{throw PaymentExceptions.AmountException}

            paymentDataModel.cardNumber?.all { it == '0'||it == ' ' } == true -> {
                throw PaymentExceptions.InvalidCardException
            }
            paymentDataModel.cvv == "000" -> {
                throw PaymentExceptions.InvalidCVVException
            }

            checkExpiration(paymentDataModel.expireDate)->{throw PaymentExceptions.ExpiryDateException}

            else -> ResponseModel.Success(PaymentSuccessfulModel("token","mm/yy"))
        }
    }

    private fun checkExpiration(expireDate: String?): Boolean {
        val simpleDateFormat = SimpleDateFormat("MM/yy", Locale.US)
        val date = simpleDateFormat.parse("$expireDate")
        val calendar = Calendar.getInstance()
        calendar.time=date?:return true
        calendar.set(Calendar.DAY_OF_MONTH,calendar.getActualMaximum(Calendar.DAY_OF_MONTH))
        calendar.set(Calendar.HOUR,calendar.getActualMaximum(Calendar.HOUR))
        calendar.set(Calendar.MINUTE,calendar.getActualMaximum(Calendar.MINUTE))
        return System.currentTimeMillis() >= calendar.timeInMillis
    }
}