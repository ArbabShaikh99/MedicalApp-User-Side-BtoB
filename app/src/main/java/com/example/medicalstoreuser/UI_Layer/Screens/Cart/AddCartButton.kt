package com.example.medicalstoreuser.UI_Layer.Screens.Cart

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.medicalstoreuser.R
import com.example.medicalstoreuser.ui.theme.complementaryColor
import com.example.medicalstoreuser.ui.theme.gradientMidColor


@Composable
fun AddToCartButton(
    modifier: Modifier = Modifier,
    addToCart: Boolean,
    onClick: () -> Unit
) {

    Row(
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Button(
            onClick = {
                onClick()
            },
            colors = ButtonDefaults.buttonColors(
                containerColor = gradientMidColor,
                contentColor = Color.Black
            ),
            modifier = Modifier.fillMaxWidth(0.8f),
            elevation = ButtonDefaults.buttonElevation(4.dp)

        ) {
            Text(
                text = if (addToCart) "Go to Cart" else "Add to Cart", style = TextStyle(
                    color =Color.Black ,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Medium,
                    fontFamily = FontFamily(Font(R.font.roboto_medium))
                ), modifier = Modifier.padding(4.dp)
            )
        }
        Card(
            modifier = Modifier
                .padding(4.dp)
                .size(50.dp),
            elevation = CardDefaults.cardElevation(4.dp),
            shape = CircleShape
        ) {
            Box(
                contentAlignment = Alignment.Center, // Center the content
                modifier = Modifier.fillMaxSize() // Fill the entire Card
            ) {
                Image(
                    painter = painterResource(id = if (addToCart) R.drawable.baseline_shopping_cart_24
                    else R.drawable.baseline_shopping_cart_24),
                    contentDescription = null,
                    modifier = Modifier
                        .size(50.dp)

                        .background(gradientMidColor),
                         colorFilter = ColorFilter.tint(Color.Black)

                )
            }
        }

    }

}