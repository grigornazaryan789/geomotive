package com.grigor.myapplication.ui.activities

import android.view.LayoutInflater
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import com.grigor.myapplication.R
import com.grigor.myapplication.databinding.ActivityCardPaymentsBinding
import com.grigor.myapplication.domain.entities.PaymentModel
import com.grigor.myapplication.domain.utils.CreditCardTextWatcher
import com.grigor.myapplication.ui.base.BaseActivity
import com.grigor.myapplication.ui.base.BaseViewModel
import com.grigor.myapplication.ui.viewmodels.CardPaymentsViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class CardPaymentsActivity : BaseActivity<ActivityCardPaymentsBinding>() {

    override val dataBindingInflater: (LayoutInflater) -> ActivityCardPaymentsBinding
        get() = ActivityCardPaymentsBinding::inflate

    private val mViewModel by viewModel<CardPaymentsViewModel>()

    override fun initViews() {
        initMonthsSpinner()
        initYearsSpinner()
    }

    override fun initListeners() = with(mBinding) {
        cardNumber.addTextChangedListener(CreditCardTextWatcher {
            cardType.setImageResource(it.icon)
        })
        confirmButton.setOnClickListener {
            confirmButtonClicked()
        }
    }

    override fun initViewModels(viewModel: BaseViewModel?) {
        super.initViewModels(mViewModel)
        lifecycleScope.launch(Dispatchers.Main) {
            mViewModel.mPaymentProcessingSharedFlow.collect {
                if(it!=null)
                Toast.makeText(this@CardPaymentsActivity, getString(R.string.successful), Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun initMonthsSpinner() = with(mBinding) {
        val items = (1..12).map { String.format("%02d", it) }
        expirationMonth.adapter =
            ArrayAdapter(this@CardPaymentsActivity, R.layout.spinner_item, items)
    }

    private fun initYearsSpinner() = with(mBinding) {
        val items = (23..26).toList()
        expirationYear.adapter =
            ArrayAdapter(this@CardPaymentsActivity, R.layout.spinner_item, items)
    }

    private fun confirmButtonClicked() = with(mBinding) {
        when {
            amountContainer.amountEditText.text.isNullOrBlank() -> amountContainer.amountEditText.error =
                getString(R.string.invalidAmount)

            cardNumber.length() < 19 -> cardNumber.error = getString(R.string.invalidCardNumber)
            cvvNumber.length() < 3 -> cvvNumber.error = getString(R.string.invalidCvv)
            cardHolderName.text.isNullOrBlank() -> cardHolderName.error =
                getString(R.string.invalidName)

            else -> {
                val amount = amountContainer.amountEditText.text.toString()
                val cardNumber = cardNumber.text.toString()
                val holderName = cardHolderName.text.toString()
                val expirationDate =
                    "${expirationMonth.selectedItem}/${expirationYear.selectedItem}"
                val cvv = cvvNumber.text.toString()
                mViewModel.processPayment(
                    PaymentModel(
                        cardNumber,
                        cvv,
                        holderName,
                        amount,
                        expirationDate
                    )
                )
            }
        }
    }
}