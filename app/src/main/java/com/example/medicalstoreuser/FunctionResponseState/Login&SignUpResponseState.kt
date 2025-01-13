package com.example.medicalstoreuser.FunctionResponseState

import com.example.medicalstoreuser.Data_Layer.Response.ResponseStatus

data class LoginSignUpResponseState(
    val isLoading : Boolean = false,
    val data : ResponseStatus? = null,
    val error : String ?= null
)