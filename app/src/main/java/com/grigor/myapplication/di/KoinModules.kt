package com.grigor.myapplication.di

import com.grigor.myapplication.data.repositories.PaymentRepositoryImpl
import com.grigor.myapplication.data.services.MockPaymentService
import com.grigor.myapplication.domain.repositories.PaymentRepository
import com.grigor.myapplication.domain.usecase_impls.PaymentProcessingUseCaseImpl
import com.grigor.myapplication.domain.usecases.PaymentProcessingUseCase
import com.grigor.myapplication.ui.viewmodels.CardPaymentsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelsModule = module {
    viewModel { CardPaymentsViewModel(get()) }
}

val useCaseModule = module {
    factory<PaymentProcessingUseCase> { PaymentProcessingUseCaseImpl(get()) }
}

val repositoryModule = module { factory<PaymentRepository> {PaymentRepositoryImpl(get())  } }

val servicesModule = module { factory {MockPaymentService()  } }