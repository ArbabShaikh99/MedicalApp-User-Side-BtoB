package com.example.medicalstoreuser.UI_Layer.Screens.Product

import androidx.compose.foundation.Image
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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.medicalstoreuser.R
import com.example.medicalstoreuser.UI_Layer.Navigation.CartScreenRoute
import com.example.medicalstoreuser.UI_Layer.Screens.Cart.AddToCartButton
import com.example.medicalstoreuser.UI_Layer.Screens.Product.component.PowerEachRow
import com.example.medicalstoreuser.UI_Layer.Screens.Product.component.ProductThumbnail
import com.example.medicalstoreuser.local.model.ClientChoiceModelEntity
import com.example.medicalstoreuser.local.viewmodel.RoomCartViewModel

@Composable
fun ProductDetailUI (
    roomCartViewModel: RoomCartViewModel,
    productItem: ClientChoiceModelEntity,
    navController: NavController
) {
    val addToCart by remember {
        mutableStateOf(false)
    }
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.BottomCenter
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
        ) {
            item {
                ProductThumbnail(
                    productImageId = productItem.product_image_id
                ) {
                    // Handle back navigation
                    navController.navigateUp()
                }
            }
            item {

                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(top = 16.dp, start = 16.dp, end = 16.dp)
                        .padding(bottom = 80.dp), // Add padding to avoid overlap with the button
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Bottom
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = productItem.product_name, style = TextStyle(
                                color = Color.Black,
                                fontSize = 24.sp,
                                fontWeight = FontWeight.Medium,
                                fontFamily = FontFamily(Font(R.font.roboto_medium))
                            )
                        )
                        Text(
                            text = "${productItem.product_price.toString()} Rs", style = TextStyle(
                                color = Color.Black,
                                fontSize = 24.sp,
                                fontWeight = FontWeight.Medium,
                                fontFamily = FontFamily(Font(R.font.roboto_medium))
                            )
                        )
                    }
                    Spacer(Modifier.height(8.dp))
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = productItem.product_category, style = TextStyle(
                                color = Color.Black,
                                fontSize = 22.sp,
                                fontWeight = FontWeight.Normal,
                                fontFamily = FontFamily(Font(R.font.roboto_regular))
                            )
                        )
                        Spacer(modifier = Modifier.width(20.dp))
                        Text(
                            text = productItem.product_power, style = TextStyle(
                                color = Color.Black,
                                fontSize = 22.sp,
                                fontWeight = FontWeight.Normal,
                                fontFamily = FontFamily(Font(R.font.roboto_regular))
                            )
                        )
                    }
                    Spacer(Modifier.height(12.dp))
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = "Select Power", style = TextStyle(
                                color = Color.Black,
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Medium,
                                fontFamily = FontFamily(Font(R.font.roboto_medium))
                            )
                        )
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.End
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.star), // Replace with your image resource
                                contentDescription = null,
                                modifier = Modifier.size(15.dp) // Adjust size as needed
                            )
                            Spacer(modifier = Modifier.width(8.dp)) // Space between image and text
                            Text(
                                text = productItem.product_rating.toString(), style = TextStyle(
                                    color = Color.Black,
                                    fontSize = 16.sp,
                                    fontWeight = FontWeight.Medium,
                                    fontFamily = FontFamily(Font(R.font.roboto_medium))
                                )
                            )
                        }
                    }
                    Spacer(Modifier.height(8.dp))

                    var selectedIndex by remember { mutableIntStateOf(-1) } // -1 means no item is selected

                    LazyRow(
                        modifier = Modifier.fillMaxWidth(),
                    ) {
                        items(8) { index ->
                            PowerEachRow(isSelectedPower = selectedIndex == index) {
                                // Update selectedIndex to the clicked item's index
                                selectedIndex = index
                            }
                        }
                    }
                     Spacer(Modifier.height(8.dp))

                    Text(
                        text = " **Description** : ${productItem.product_description}",
                        style = TextStyle(
                            color = Color.Black,
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Normal,
                            fontFamily = FontFamily(Font(R.font.roboto_regular))
                        )
                    )
                }
            }
        }
        AddToCartButton(
            modifier = Modifier
                .padding(start = 16.dp, end = 16.dp, bottom = 32.dp)
                .align(Alignment.BottomCenter),
            addToCart = if (roomCartViewModel.findProductById(productItem.product_id) != null) !addToCart else addToCart
        ) {

            if (roomCartViewModel.findProductById(productItem.product_id) != null) {
                navController.navigate(CartScreenRoute)
            } else {
                roomCartViewModel.insertCartList(
                    productItem
                )
            }
        }
    }
}


