package com.example.medicalstoreuser.UI_Layer.ScreenState.UserState

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf


data class LoginScreenState(
    val email : MutableState<String> = mutableStateOf(""),
    val password : MutableState<String> = mutableStateOf("")
)