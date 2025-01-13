package com.example.medicalstoreuser.FunctionResponseState.order

import com.example.medicalstoreuser.Data_Layer.Response.ResponseStatus


data class MedicalOrderResponseState(
    val isLoading : Boolean = false,
    val data : ResponseStatus? = null,
    val error : String ?= null
)