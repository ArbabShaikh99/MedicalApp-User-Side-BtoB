package com.example.medicalstoreuser.Data_Layer.Response

data class LoginResponse(
    val status: String,
    val data: LoginData,
    val message: String
)

data class LoginData(
    val user: GetAlluserResponseStatus,
    val message: String
)


