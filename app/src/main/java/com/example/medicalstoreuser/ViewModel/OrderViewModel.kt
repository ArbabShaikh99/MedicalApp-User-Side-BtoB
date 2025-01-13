package com.example.medicalstoreuser.ViewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.medicalstoreuser.Data_Layer.Response.Order.MedicalOrderResponseItem
import com.example.medicalstoreuser.FunctionResponseState.order.MedicalGetAllOrderState
import com.example.medicalstoreuser.FunctionResponseState.order.MedicalOrderResponseState
import com.example.medicalstoreuser.Repo.repo
import com.example.medicalstoreuser.State.State
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject



@HiltViewModel
class  OrderViewmodel @Inject constructor(
    private val repo: repo
) : ViewModel() {

    private val _createOrder = MutableStateFlow(MedicalOrderResponseState())
    val createOrder = _createOrder.asStateFlow()

    private val _getAllUserOrders = MutableStateFlow(MedicalGetAllOrderState())
    val getAllUserOrders = _getAllUserOrders.asStateFlow()

    fun createOrder(
        orderList: List<MedicalOrderResponseItem>
    ) {
        viewModelScope.launch {
            repo.createOrder(orderList = orderList).collect {
                when (it) {
                    is State.Loading -> {
                        _createOrder.value = MedicalOrderResponseState(isLoading = true)
                    }
                    is State.Success -> {
                        _createOrder.value = MedicalOrderResponseState(data = it.data.body())
                    }
                    is State.Error -> {
                        _createOrder.value = MedicalOrderResponseState(error = it.message)
                    }
                }
            }

        }
    }

    fun getAllUserOrders(
        userId: String
    ) {
        viewModelScope.launch {
            repo.getAllUserOrders(userId).collect {
                when (it) {
                    is State.Loading -> {
                        _getAllUserOrders.value = MedicalGetAllOrderState(loading = true)
                    }
                    is State.Success -> {
                        Log.d("@TAG", "getAllUserOrders: ${it.data.body()?.size}")
                        _getAllUserOrders.value = MedicalGetAllOrderState(data = it.data)
                    }
                    is State.Error -> {
                        _getAllUserOrders.value = MedicalGetAllOrderState(error = it.message)
                    }
                }
            }
        }
    }
}