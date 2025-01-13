package com.example.medicalstoreuser.FunctionResponseState.User

import com.example.medicalstoreuser.Data_Layer.Response.ResponseStatus


data class UpdateUserResponseState(
    val isLoading : Boolean = false,
    val data : ResponseStatus? = null,
    val error : String ?= null
)