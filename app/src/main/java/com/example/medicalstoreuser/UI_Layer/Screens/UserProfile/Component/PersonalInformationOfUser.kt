package com.example.medicalstoreuser.UI_Layer.Screens.UserProfile.Component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.medicalstoreuser.R
import com.example.medicalstoreuser.UI_Layer.Componet.ImageComponent
import com.example.medicalstoreuser.ui.theme.softWhiteColor


@Composable
fun PersonalInformationOfUser(
    userName: String,
    userImageId: String,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = null
            ) {
                onClick()
            }
            .fillMaxWidth(),
//            .shadow(
//                elevation = 3.dp,
//                shape = RoundedCornerShape(16.dp)
//            )
//            //.background(softWhiteColor)
//            .padding(8.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Icon(
            painter = painterResource(id = R.drawable.baseline_supervised_user_circle_24),
            contentDescription = null,
            modifier = Modifier.size(50.dp),
            tint = Color.Black
        )
//        ImageComponent(
//            imageId = userImageId,
//            modifier = Modifier
//                .shadow(
//                    elevation = 10.dp,
//                    spotColor = Color.Black,
//                    ambientColor = Color.Black,
//                    shape = CircleShape
//                ),
//            imageSize = 50.dp,
//            shape = CircleShape
//        )
        Spacer(modifier = Modifier.width(16.dp))
        Column(
            modifier = Modifier.weight(.2f),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = userName, style = TextStyle(
                    color = Color.Black,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    fontFamily = FontFamily(Font(R.font.roboto_medium))
                )
            )
            Text(
                text = "edit personal details", style = TextStyle(
                    color = Color.Gray,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Light,
                    fontFamily = FontFamily(Font(R.font.roboto_regular))
                )
            )
        }
        Icon(
            imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
            contentDescription = null,
            modifier = Modifier.size(24.dp),
            tint = Color.Black
        )
    }
}