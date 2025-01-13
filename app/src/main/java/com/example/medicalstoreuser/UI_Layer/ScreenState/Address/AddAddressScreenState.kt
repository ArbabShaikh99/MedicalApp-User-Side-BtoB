package com.example.medicalstoreuser.UI_Layer.ScreenState.Address

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf

data class AddAddressScreenState(
    val address : MutableState<String> = mutableStateOf(""),
    val city : MutableState<String> = mutableStateOf(""),
    val state : MutableState<String> = mutableStateOf(""),
    val street : MutableState<String> = mutableStateOf(""),
    val phoneNo : MutableState<String> = mutableStateOf(""),
    val pinCode : MutableState<String> = mutableStateOf(""),
    val fullName : MutableState<String> = mutableStateOf("")

)