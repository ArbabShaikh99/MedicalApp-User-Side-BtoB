package com.example.medicalstoreuser.UI_Layer.Screens.Cart

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.medicalstoreuser.UI_Layer.Navigation.CreateOrderScreenRoute
import com.example.medicalstoreuser.UI_Layer.Navigation.ProductDetailScreenRoute
import com.example.medicalstoreuser.UI_Layer.Navigation.ReceiveOrderRoute
import com.example.medicalstoreuser.UI_Layer.Screens.Cart.Component.CartItem
import com.example.medicalstoreuser.UI_Layer.Screens.Cart.Component.CartPriceCard
import com.example.medicalstoreuser.UI_Layer.Screens.Cart.Component.CartTopBar
import com.example.medicalstoreuser.local.viewmodel.RoomCartViewModel
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

@Composable
fun CartScreenUI (
    roomCartViewmodel: RoomCartViewModel = hiltViewModel(),
    navController: NavController
) {

    var selectedItem by remember {
        mutableIntStateOf(2)
    }
    val cartList by roomCartViewmodel.cartList.collectAsState()
    val subTotalPrice by roomCartViewmodel.subTotalPrice.collectAsState()
    val context = LocalContext.current

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.BottomCenter
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            Column(modifier = Modifier.fillMaxWidth()) {

                val previousRoute =
                    navController.previousBackStackEntry?.destination?.route

                Log.d("@prev", "CartScreen: $previousRoute")
                //when route comes from product detail screen then go for it other no need back button
                CartTopBar(
                    headerName = "Cart",
                    isBackButtonShow = previousRoute != null,
                    onClick = {
                        navController.navigateUp()
                    }
                )
            }
            Spacer(Modifier.height(8.dp))


                LazyColumn(
                    modifier = Modifier
                        .padding(8.dp)
                        .fillMaxWidth()
                        .height(350.dp)
                ) {
                    items(cartList) { product ->
                        CartItem(
                            count = product.product_count,
                            productItem = product,
                            itemOnClick = {
                                navController.navigate(
                                    ProductDetailScreenRoute(
                                        productName = product.product_name,
                                        productImageId = product.product_image_id,
                                        productPrice = product.product_price,
                                        productRating = product.product_rating,
                                        productStock = product.product_stock,
                                        productDescription = product.product_description,
                                        productPower = product.product_power,
                                        productCategory = product.product_category,
                                        productExpiryDate = product.product_expiry_date,
                                        productId = product.product_id
                                    )
                                )
                            }, onDelete = {
                                roomCartViewmodel.deleteCartById(product.product_id)
                            }, increaseItem = {
                                Log.d("@stock", "CartScreen: ${product.product_count}")
                                Log.d("@stock", "CartScreen: ${product.product_stock}")
                                if(product.product_stock > product.product_count){
                                    roomCartViewmodel.updateCartList(
                                        product.product_id,
                                        product.product_count + 1
                                    )
                                }else{
                                    Toast.makeText(context, "Product Stock Limit Reached ${product.product_stock}", Toast.LENGTH_SHORT).show()
                                }

                            }, decreaseItem = {
                                if (product.product_count > 1)
                                    roomCartViewmodel.updateCartList(
                                        product.product_id,
                                        product.product_count - 1
                                    )
                            }
                        )
                    }
                }
//            if (cartList.isNotEmpty()) {
                CartPriceCard(
                    subTotalPrice
                ) {
                   val itemCartListJson = Json.encodeToString(cartList.toList())
                    navController.navigate(
                        CreateOrderScreenRoute(
                            cartList = itemCartListJson,
                            subTotalPrice = subTotalPrice
                        )
                    )
                }
            }


        }

        }



