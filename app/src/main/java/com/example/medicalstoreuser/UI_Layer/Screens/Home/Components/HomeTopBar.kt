package com.example.medicalstoreuser.UI_Layer.Screens.Home.Components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import com.example.medicalstoreuser.UI_Layer.Componet.TextFieldComponent


@Composable
fun HomeTopBar(
    userName: String,
    searchText: String,
    onValueChange: (String) -> Unit
) {

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                Brush.verticalGradient(
                    colors = listOf(Color(0xFFF6F6E8), Color(0xFFEFEFDC)) // Light pastel gradient
                ),
                shape = RoundedCornerShape(topEnd =22.dp , bottomStart = 32.dp, bottomEnd = 32.dp)
            )
    ) {

        Column(
            modifier = Modifier
                .padding(start = 16.dp, end = 16.dp, bottom = 21.dp)
                .fillMaxWidth()
                //    .background(softWhiteColor)
                .padding(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Welcome, $userName", style = TextStyle(
                        color = Color.Black,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        fontFamily = FontFamily(Font(R.font.roboto_bold))
                    )
                )
                Icon(
                    imageVector = Icons.Default.Notifications,
                    contentDescription = null,
                    tint = Color.Black
                )
            }
            Spacer(Modifier.height(8.dp))

            TextFieldComponent(
                value = searchText,
                onValueChange = {
                    onValueChange(it)
                },
                placeholder = "Search medicine...",
                leadingIcon = R.drawable.search
            )

        }
    }
}