package com.devuz.foundation_android.screens.detail

import DetailResponse
import com.devuz.foundation_android.utils.Status

data class DetailState(
    val status: Status= Status.Loading,
    val character: DetailResponse?=null,
    val errorMessage: String=""
)