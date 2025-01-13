package com.example.medicalstoreuser.UI_Layer.Screens.Search.Compnents

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
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
fun SearchTopBar(
    onValueChange: (String)-> Unit,
    searchText:String
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
        Column(modifier = Modifier.fillMaxWidth()
            .padding(start = 15.dp , end = 15.dp,bottom = 16.dp),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.Center
        )
        {
            Text(
                text = "Search", style = TextStyle(
                    color = Color.Black,
                    fontSize = 26.sp,
                    fontWeight = FontWeight.Bold,
                    fontFamily = FontFamily(Font(R.font.roboto_bold))
                )
            )
            Spacer(Modifier.height(8.dp))

            TextFieldComponent(
                value = searchText,
                onValueChange = {
                    onValueChange(it)
                },
                placeholder = "Search medicine..",
                leadingIcon = R.drawable.search
            )
        }
    }
}