package com.example.medicalstoreuser.UI_Layer.Screens.Cart.Component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.medicalstoreuser.R
import com.example.medicalstoreuser.ui.theme.complementaryColor


@Composable
fun  CartTopBar(
    headerName: String,
    isBackButtonShow: Boolean,
    onClick: () -> Unit = {}
) {

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background( Brush.verticalGradient(
                colors = listOf(Color(0xFFF6F6E8), Color(0xFFEFEFDC))
            ),
                shape = RoundedCornerShape(bottomStart = 36.dp, bottomEnd = 32.dp)
            )
            .height(85.dp)
    ) {

        Column(
            modifier = Modifier
                .padding(start = 16.dp, end = 16.dp, bottom = 16.dp)
                .fillMaxWidth()
                .padding(top = 8.dp),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.Center
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically
            ) {
                if (isBackButtonShow) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.KeyboardArrowLeft,
                        contentDescription = null,
                        tint = Color.Black,
                        modifier = Modifier
                            .size(39.dp)
                            .clickable(
                                interactionSource = remember { MutableInteractionSource() },
                                indication = null
                            ) {
                                onClick()
                            }
                    )
                    Spacer(Modifier.width(8.dp))
                }
                Text(
                    text = headerName, style = TextStyle(
                        color = Color.Black,
                        fontSize = 28.sp,
                        fontWeight = FontWeight.Bold,
                        fontFamily = FontFamily(Font(R.font.roboto_bold))
                    )
                )
            }
        }
    }
}
