package com.example.medicalstoreuser.UI_Layer.Screens.Search

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.medicalstoreuser.Data_Layer.Response.product.getAllProductResponseItem
import com.example.medicalstoreuser.UI_Layer.Navigation.ProductDetailScreenRoute
import com.example.medicalstoreuser.UI_Layer.Screens.Search.Compnents.SearchItemsCard
import com.example.medicalstoreuser.UI_Layer.Screens.Search.Compnents.SearchTopBar
import com.example.medicalstoreuser.ViewModel.ProductViewModel

@Composable
fun SearchScreenUI(
    productViewModel: ProductViewModel,
    navController: NavController
) {



val getAllProductState = productViewModel.getAllProductState.collectAsState()
val searchTextState by productViewModel.searchTextFieldState.collectAsState()
    var getAllProductList = listOf<getAllProductResponseItem>()

    when {
        getAllProductState.value.isLoading -> {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }
        }

        getAllProductState.value.data != null -> {
            getAllProductList = getAllProductState.value.data!!

            Log.d("AllProduct", "HomeScreen: ${getAllProductState.value.data!!.size}")

            val filterProductList = getAllProductList.filter {
                it.product_name.lowercase()
                    .contains(searchTextState.searchTextValue.value.lowercase())
                        ||
                        it.product_category.lowercase()
                            .contains(searchTextState.searchTextValue.value.lowercase())
            }.filter {
                it.product_stock > 0
            }
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.BottomEnd
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color.White),
                    horizontalAlignment = Alignment.Start,
                    verticalArrangement = Arrangement.Top
                ){
                    SearchTopBar(
                        searchText = searchTextState.searchTextValue.value,
                        onValueChange ={
                            searchTextState.searchTextValue.value = it
                        }
                    )
                    Spacer(Modifier.height(8.dp))
                    LazyColumn(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(Color.White),
                            //.padding(bottom = 70.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Top
                    ) {
                        items(getAllProductList){
                            SearchItemsCard(it,
                                onClick = {
                                    navController.navigate(
                                        ProductDetailScreenRoute(
                                            productName = it.product_name,
                                            productImageId = it.product_image_id,
                                            productPrice = it.product_price,
                                            productRating = it.product_rating.toFloat(),
                                            productStock = it.product_stock,
                                            productDescription = it.product_description,
                                            productPower = it.product_power,
                                            productCategory = it.product_category,
                                            productExpiryDate = it.product_expiry_date,
                                            productId = it.product_id
                                        )
                                    )
                                })
                        }

                    }

                }
        }
            }

        getAllProductState.value.error != null -> {
            Log.d("AllProduct", "HomeScreen: ${getAllProductState.value.error}")
        }
    }


        }