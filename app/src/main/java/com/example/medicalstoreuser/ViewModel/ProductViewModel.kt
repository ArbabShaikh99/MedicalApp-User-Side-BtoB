package com.example.medicalstoreuser.ViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.medicalstoreuser.FunctionResponseState.product.getAllProductResponseState
import com.example.medicalstoreuser.Repo.repo
import com.example.medicalstoreuser.State.State
import com.example.medicalstoreuser.UI_Layer.ScreenState.SearchState.SearchScreenState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductViewModel @Inject constructor(val repo: repo) : ViewModel() {

    private val _getAllProductState = MutableStateFlow(getAllProductResponseState())
    val getAllProductState = _getAllProductState.asStateFlow()

    private val _searchTextFieldState = MutableStateFlow(SearchScreenState())
    val searchTextFieldState = _searchTextFieldState.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = SearchScreenState()
    )

    private val _getSpecificProduct = MutableStateFlow(getAllProductResponseState())
    val getSpecificProduct = _getSpecificProduct.asStateFlow()

    init {
        getAllProducts()
    }

    private fun getAllProducts() {
        viewModelScope.launch {
            repo.getAllProductRepo().collect {
                when (it) {
                    is State.Loading -> {
                        _getAllProductState.value = getAllProductResponseState(isLoading = true)
                    }

                    is State.Success -> {
                        _getAllProductState.value =
                            getAllProductResponseState(data = it.data.body())
                    }

                    is State.Error -> {
                        _getAllProductState.value = getAllProductResponseState(error = it.message)
                    }
                }
            }

        }
    }
        fun getSpecificProducts(productId: String) {
            viewModelScope.launch {
                repo.getSpecificProduct(productId = productId).collect {
                    when (it) {
                        is State.Loading -> {
                            _getSpecificProduct.value = getAllProductResponseState(isLoading = true)
                        }

                        is State.Success -> {
                            _getSpecificProduct.value =
                                getAllProductResponseState(data = it.data.body())
                        }

                        is State.Error -> {
                            _getSpecificProduct.value =
                                getAllProductResponseState(error = it.message)
                        }
                    }
                }
            }

        }

    }
