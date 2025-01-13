package com.example.medicalstoreuser.UI_Layer.Screens.Cart.Component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.medicalstoreuser.R
import com.example.medicalstoreuser.UI_Layer.Screens.animation.AnimatedContentComponent
import com.example.medicalstoreuser.ui.theme.GreenColor
import com.example.medicalstoreuser.ui.theme.WhiteGreyColor
import com.example.medicalstoreuser.ui.theme.gradientMidColor

@Composable
fun CartPriceCard(
    subTotalPrice: Float,
    checkOutClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .padding(top = 5.dp, start = 5.dp, end = 5.dp, bottom = 70.dp)
            .fillMaxWidth()
            .height(215.dp),
                shape = RoundedCornerShape(bottomStart = 36.dp, bottomEnd = 32.dp)
                    // .background(Color.White, shape = RoundedCornerShape(8.dp))
        ,elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(gradientMidColor)
                .padding(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            val totalPrice = totalPriceCalculate(subTotalPrice)

            TextRow(priceName = "Sub-Total", price = subTotalPrice.toString())
            TextRow(
                priceName = "Delivery Charge",
                price = if (calculateDeliveryCharge(subTotalPrice) > 0) calculateDeliveryCharge(
                    subTotalPrice
                ).toString() else "Free"
            )
            TextRow(
                priceName = "Tax Charge 18%",
                price = calculateTaxCharge(subTotalPrice).toString()
            )
            TextRow(priceName = "Discount 5%", price = calculateDiscount(subTotalPrice).toString())

            Spacer(modifier = Modifier.height(10.dp))

            HorizontalDivider()
            Spacer(modifier = Modifier.height(4.dp))

            TextRow(priceName = "Total Price", price = totalPrice.toString())

            Spacer(modifier = Modifier.height(10.dp))
            CheckoutButton {
                checkOutClick()
            }
        }
    }
}



@Composable
fun TextRow(priceName: String, price: String) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = priceName, style = TextStyle(
                fontSize = 18.sp,
                fontFamily = FontFamily(Font(R.font.roboto_regular)),
                color = Color.Black
            )
        )

        AnimatedContentComponent(
            targetState = price,
        ) { targetPrice ->
            Text(
                text = stringResource(R.string.cart_rs, targetPrice),
                style = TextStyle(
                    fontSize = 18.sp,
                    fontFamily = FontFamily(Font(R.font.roboto_bold)),
                    color = Color.Black
                )
            )
        }
    }
}

@Composable
fun CheckoutButton(
    onClick: () -> Unit
) {

    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Button(
            onClick = {
                onClick()
            },
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Black,
                contentColor = Color.White
            ),
            modifier = Modifier.fillMaxWidth(),
            elevation = ButtonDefaults.buttonElevation(4.dp)

        ) {
            Text(
                text = "Buy Now", style = TextStyle(
                    color = Color.White,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Medium,
                    fontFamily = FontFamily(Font(R.font.roboto_medium))
                )
            )
        }
    }

}
