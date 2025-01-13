package com.example.medicalstoreuser.UI_Layer.Screens.Product.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.dp
import com.example.medicalstoreuser.R
import com.example.medicalstoreuser.ui.theme.Pink40
import com.example.medicalstoreuser.ui.theme.softWhiteColor


@Composable
fun PowerEachRow(isSelectedPower: Boolean, onClick:()->Unit) {

    Column(
        modifier = Modifier.padding(8.dp).width(60.dp).height(40.dp).background(
            color = if(isSelectedPower) Pink40 else softWhiteColor,
            shape = RoundedCornerShape(8.dp)
        ).clickable {
            onClick()
        },
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "250mg", style = TextStyle(
            fontFamily = FontFamily(Font(R.font.roboto_regular)),
            fontStyle = FontStyle.Normal
        )
        )

    }
}