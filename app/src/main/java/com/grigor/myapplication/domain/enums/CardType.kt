package com.grigor.myapplication.domain.enums

import androidx.annotation.DrawableRes
import com.grigor.myapplication.R

enum class CardType(@DrawableRes val icon: Int, val pattern: String) {

    VISA(R.drawable.ic_visa, "^4[0-9]{6,}$"),
    MASTER_CARD(
        R.drawable.ic_mastercard,
        "^5[1-5][0-9]{5,}$"
    ),
    UNKNOWN(R.drawable.ic_unknown_card,"")
}