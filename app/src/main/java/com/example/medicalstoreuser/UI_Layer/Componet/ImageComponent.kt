package com.example.medicalstoreuser.UI_Layer.Componet

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil.compose.SubcomposeAsyncImage
import com.example.medicalstoreuser.Data_Layer.IMG_URL_ID
import com.example.medicalstoreuser.R


@Composable
fun ImageComponent(
    imageId: String,
    imageSize: Dp,
    padding: Dp = 0.dp,
    shape: Shape,
    modifier: Modifier = Modifier
) {
    SubcomposeAsyncImage(
        model = IMG_URL_ID + imageId, modifier = modifier
            .padding(padding)
            .size(imageSize)
            .shadow(
             elevation = 2.dp,
                shape = shape
            ), contentScale = ContentScale.Crop,
        loading = {
            Box(
                modifier = Modifier
                    .size(imageSize)
                    .background(Color.LightGray) // Placeholder color
                    //.align(Alignment.Center) // Centering the content
            ) {
                Text("Loading")
              //  ShimmerEffect(modifier = Modifier.fillMaxSize())
            }
        },
        error = {
            Image(
                painter = painterResource(R.drawable.ic_launcher_foreground),
                contentDescription = null,
                modifier = Modifier.size(imageSize)
            )
        },
        contentDescription = null
    )

}
