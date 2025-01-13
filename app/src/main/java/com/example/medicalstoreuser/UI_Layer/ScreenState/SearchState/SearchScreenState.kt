package com.example.medicalstoreuser.UI_Layer.ScreenState.SearchState

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf


data class SearchScreenState(
    val searchTextValue: MutableState<String> = mutableStateOf("")
)
