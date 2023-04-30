package com.grigor.myapplication.domain.utils

import android.text.Editable
import android.text.InputFilter
import android.text.TextWatcher
import com.grigor.myapplication.domain.enums.CardType

class CreditCardTextWatcher(private val cardTypeListener: (CardType) -> Unit) : TextWatcher {

    private var current = ""
    private var userInput = ""

    override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}

    override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}

    override fun afterTextChanged(s: Editable) {
        if (s.toString() != current) {
            userInput = s.toString().filter { it.isDigit() }
            if (userInput.length <= 16) {
                current = userInput.chunked(4).joinToString(" ")
                s.filters = arrayOfNulls<InputFilter>(0)
            }
            s.replace(0, s.length, current, 0, current.length)
            identifyCardType()
        }
    }

    private fun identifyCardType() {
        val cardType =
            CardType.values().find { userInput.matches(it.pattern.toRegex()) } ?: CardType.UNKNOWN
        cardTypeListener(cardType)
    }
}
