package com.example.medicalstoreuser.FunctionResponseState.order

import com.example.medicalstoreuser.Data_Layer.Response.Order.MedicalOrderResponseItem
import retrofit2.Response

data class MedicalGetAllOrderState(
    val loading : Boolean = false,
    val data : Response<ArrayList<MedicalOrderResponseItem>>? = null,
    val error : String ?= null
)
