package com.james.dailyquotes

import androidx.annotation.DrawableRes

data class QuoteEntity(
    val quotes: String,
    @DrawableRes val imageResourceId: Int
)