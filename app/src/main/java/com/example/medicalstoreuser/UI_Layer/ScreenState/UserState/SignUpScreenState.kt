package com.example.medicalstoreuser.UI_Layer.ScreenState.UserState

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf

data class SignUpScreenState(
    val userName : MutableState<String> = mutableStateOf(""),
    val mobileNo : MutableState<String> = mutableStateOf(""),
    val email : MutableState<String> = mutableStateOf(""),
    val password : MutableState<String> = mutableStateOf(""),
    val address : MutableState<String> = mutableStateOf(""),
    val pinCode : MutableState<String> = mutableStateOf(""),
)