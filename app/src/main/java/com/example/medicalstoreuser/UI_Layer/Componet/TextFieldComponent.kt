package com.example.medicalstoreuser.UI_Layer.Componet

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.medicalstoreuser.ui.theme.softWhiteColor


@Composable
fun TextFieldComponent(
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String,
    leadingIcon: Int,
) {

    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        placeholder = { Text(placeholder) },

        leadingIcon = {
            Icon(
                painter = painterResource(leadingIcon),
                contentDescription = null,
                modifier = Modifier.size(29.dp),
                tint = Color.Gray
            )
        },
        modifier = Modifier
            .fillMaxWidth()
           // .padding(horizontal = 16.dp)
           // .background(Color.White, RoundedCornerShape(8.dp)),
           .background(shape = RoundedCornerShape(16.dp), color = Color.White),
        shape = RoundedCornerShape(16.dp),
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = softWhiteColor,
            cursorColor = Color.Black
        ), maxLines = 1
    )

}