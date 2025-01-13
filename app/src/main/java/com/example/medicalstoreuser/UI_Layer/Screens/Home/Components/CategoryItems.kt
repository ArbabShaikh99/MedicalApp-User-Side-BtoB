package com.example.medicalstoreuser.UI_Layer.Screens.Home.Components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.medicalstoreuser.R
import com.example.medicalstoreuser.ui.theme.LightGreenColor
import com.example.medicalstoreuser.ui.theme.softWhiteColor


data class CategoryItems(
    val itemName: String,
    val itemImage: Int
)

val categoryList = listOf(
    CategoryItems(
        itemName = "Thermometer",
        itemImage = R.drawable.thermometer
    ),
    CategoryItems(
        itemName = "Health",
        itemImage = R.drawable.healthcare
    ),
    CategoryItems(
        itemName = "Capsules",
        itemImage = R.drawable.capsules
    ),

    CategoryItems(
        itemName = "X-ray",
        itemImage = R.drawable.x_ray
    ),
    CategoryItems(
        itemName = "Syringe",
        itemImage = R.drawable.syringe
    ),
    CategoryItems(
        itemName = "Vitamins",
        itemImage = R.drawable.vitamins
    ),
    CategoryItems(
        itemName = "Bandages",
        itemImage = R.drawable.bandages
    ),
    CategoryItems(
        itemName = "Inhalers",
        itemImage = R.drawable.inhalers
    ),
    CategoryItems(
        itemName = "Masks",
        itemImage = R.drawable.masks
    ),
)


@Composable
fun CategoryItemCard(
    itemName: String,
    itemImage : Int,
    onClick:()->Unit
) {
    Column(
        modifier = Modifier
            .padding(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Box(
            modifier = Modifier
                .size(80.dp)
                .background(color = softWhiteColor, shape = CircleShape),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(id = itemImage),
                contentDescription = null,
                modifier = Modifier
                    .size(60.dp)
                    .clip(CircleShape) // Clip the image into a circular shape

            )
        }
        Spacer(Modifier.height(4.dp))
        Text(
            text = itemName, style = TextStyle(
                fontSize = 14.sp,
                fontWeight = FontWeight.Medium,
                fontFamily = FontFamily(Font(R.font.roboto_regular)),
                color = Color.Black
            )
        )
    }
}
