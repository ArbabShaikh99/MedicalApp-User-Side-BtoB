package com.example.medicalstoreuser.UI_Layer.Screens.Home

import PreferenceManager
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.medicalstoreuser.Data_Layer.Response.product.getAllProductResponseItem
import com.example.medicalstoreuser.R
import com.example.medicalstoreuser.UI_Layer.Navigation.ProductDetailScreenRoute
import com.example.medicalstoreuser.UI_Layer.Screens.Home.Components.CategoryItemCard
import com.example.medicalstoreuser.UI_Layer.Screens.Home.Components.HomeTopBar
import com.example.medicalstoreuser.UI_Layer.Screens.Home.Components.ImageSlider
import com.example.medicalstoreuser.UI_Layer.Screens.Home.Components.UserRecentSeeProduct
import com.example.medicalstoreuser.UI_Layer.Screens.Home.Components.categoryList
import com.example.medicalstoreuser.ViewModel.ProductViewModel
import com.example.medicalstoreuser.ViewModel.UserViewModel
import com.example.medicalstoreuser.ui.theme.softWhiteColor

@Composable
fun HomeScreenUI(
    navController: NavController,
    userViewModel: UserViewModel,
    productViewModel: ProductViewModel
) {


    val getAllProductState = productViewModel.getAllProductState.collectAsState()
    var getAllProductList = listOf<getAllProductResponseItem>()

    when {
        getAllProductState.value.isLoading -> {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }
        }

        getAllProductState.value.data != null -> {
            Log.d("AllProduct", "HomeScreen: ${getAllProductState.value.data!!.size}")

            getAllProductList = getAllProductState.value.data!!
            var searchText by remember {
                mutableStateOf("")
            }
            val filterProductList = getAllProductList.filter {
                it.product_name.lowercase()
                    .contains(searchText.lowercase()) || it.product_category.lowercase()
                    .contains(searchText.lowercase())
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
                ) {
                    HomeTopBar(
                        userName = "Arbab Shaikh ",
                        searchText = searchText,
                        onValueChange = {
                            searchText = it
                        }
                    )
                    Spacer(Modifier.height(10.dp))

                    LazyColumn(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(Color.White),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Top
                    ) {
                        item {
                            Column(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .padding(start = 8.dp, end = 8.dp, top = 8.dp, bottom = 70.dp),
                                horizontalAlignment = Alignment.CenterHorizontally,
                                verticalArrangement = Arrangement.Center
                            ) {
                                ImageSlider()
                                Spacer(Modifier.height(8.dp))

                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.SpaceBetween,
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Text(
                                        text = "Categories", style = TextStyle(
                                            color = Color.Black,
                                            fontSize = 16.sp,
                                            fontWeight = FontWeight.Medium,
                                            fontFamily = FontFamily(Font(R.font.roboto_medium))
                                        )
                                    )
                                }
                                Spacer(Modifier.height(8.dp))

                                  LazyRow (modifier = Modifier.fillMaxWidth()){
                                      items(categoryList){
                                          CategoryItemCard(
                                              itemName = it.itemName,
                                              itemImage = it.itemImage
                                          ){

                                          }
                                      }
                                  }

                                Spacer(Modifier.height(10.dp))
                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.SpaceBetween,
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Text(
                                        text = "Recent See..", style = TextStyle(
                                            color = Color.Black,
                                            fontSize = 16.sp,
                                            fontWeight = FontWeight.Medium,
                                            fontFamily = FontFamily(Font(R.font.roboto_medium))
                                        )
                                    )
                                    Box(
                                        modifier = Modifier
                                            .padding(end = 8.dp)
                                            .shadow(shape = CircleShape, elevation = 1.dp)
                                            .background(softWhiteColor)
                                            .padding(4.dp),
                                        contentAlignment = Alignment.Center
                                    ) {
                                        Text(
                                            text = "see more", style = TextStyle(
                                                color = Color.Black,
                                                fontSize = 12.sp,
                                                fontWeight = FontWeight.Medium,
                                                fontFamily = FontFamily(Font(R.font.roboto_medium))
                                            )
                                        )
                                    }
                                }
                                Spacer(Modifier.height(9.dp))

                                LazyRow (modifier = Modifier.fillMaxSize()){
                                    items(filterProductList){
                                        UserRecentSeeProduct(
                                            itemImage = it.product_image_id,
                                            price = it.product_price,
                                            rating = it.product_rating.toFloat(),
                                            itemName = it.product_name
                                        ){
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
                                        }
                                    }
                                }
                                Spacer(Modifier.height(8.dp))

                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.SpaceBetween,
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Text(
                                        text = "last Week ,Most Sell Products", style = TextStyle(
                                            color = Color.Black,
                                            fontSize = 16.sp,
                                            fontWeight = FontWeight.Medium,
                                            fontFamily = FontFamily(Font(R.font.roboto_medium))
                                        )
                                    )
                                    Box(
                                        modifier = Modifier
                                            .padding(end = 8.dp)
                                            .shadow(shape = CircleShape, elevation = 1.dp)
                                            .background(softWhiteColor)
                                            .padding(4.dp),
                                        contentAlignment = Alignment.Center
                                    ) {
                                        Text(
                                            text = "see more", style = TextStyle(
                                                color = Color.Black,
                                                fontSize = 12.sp,
                                                fontWeight = FontWeight.Medium,
                                                fontFamily = FontFamily(Font(R.font.roboto_medium))
                                            )
                                        )
                                    }
                                }
                                Spacer(Modifier.height(8.dp))

                                LazyRow (modifier = Modifier.fillMaxSize()){
                                    items(getAllProductList){
                                        UserRecentSeeProduct(
                                            itemImage = it.product_image_id,
                                            price = it.product_price,
                                            rating = it.product_rating.toFloat(),
                                            itemName = it.product_name
                                        ){
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
                                        }
                                    }
                                }
                            }

                        }
                    }




                }
            }
        }



        getAllProductState.value.error !=null->{
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.TopCenter) {
                Text(text = "Product Not getting ${getAllProductState.value.error}")
            }
        }
    }
}








