package com.example.medicalstoreuser.FunctionResponseState.product

import com.example.medicalstoreuser.Data_Layer.Response.product.getAllProductResponseItem
import retrofit2.Response

data class getAllProductResponseState(
val isLoading : Boolean = false,
    val data :ArrayList<getAllProductResponseItem>? = null,
    val error : String? =null
)
