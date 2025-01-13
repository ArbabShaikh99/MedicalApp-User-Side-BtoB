package com.example.medicalstoreuser.FunctionResponseState

import com.example.medicalstoreuser.Data_Layer.Response.GetAlluserResponseStatus
import retrofit2.Response

data class GetAllUserState(
    val loading:Boolean = false,
    val Error: String?=null,
    val data: Response<ArrayList<GetAlluserResponseStatus>>? = null,

    )
