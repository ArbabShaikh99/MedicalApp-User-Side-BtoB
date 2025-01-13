package com.example.medicalstoreuser.UI_Layer.Screens.ReciveOrder

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.medicalstoreuser.R

@Composable
fun ReceiveOrderUI() {

    var isApproved by remember {
        mutableStateOf(0)
    }
    var NoReceive by remember {
        mutableStateOf(0)
    }




    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Spacer(modifier = Modifier.height(16.dp))

            Image(
                painter = painterResource(id = R.drawable.waiting),
                contentDescription = "Description of the image",
                modifier = Modifier.size(400.dp) //
            )
            //Spacer(modifier = Modifier.height(10.dp))

            Text(
                text = if (isApproved == 1) "Received" else "PENDING", style = TextStyle(
                    color = Color.Black,
                    fontSize = 40.sp,
                    fontWeight = FontWeight.Bold,
                    fontFamily = FontFamily(Font(R.font.roboto_bold))
                ), textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = if (isApproved == 1) "You have Successfully Receive Products."
                else "Delivery, product is  pending" + ".\n After the Sending Delivery Date you Click Receive Button",
                style = TextStyle(
                    color = Color.Gray,
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Normal,
                    fontFamily = FontFamily(Font(R.font.roboto_regular))
                ),
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(16.dp))

           Row (modifier = Modifier.fillMaxWidth(),
               Arrangement.Center ,Alignment.CenterVertically){

               ButtonView(
                   text = "Receive",
                   color = Color.Black,
                   enabled = isApproved == 0
               ){
                       isApproved =1
               }
               Spacer(modifier = Modifier.width(35.dp))

               ButtonView(
                   text = "No-Receive",
                   color = Color.White,
                   enabled = isApproved == 1
               ){
                   isApproved = 0
               }

//               Button(onClick = {
//
//               },colors = ButtonDefaults.buttonColors(Color.Black)
//               ) {
//                   Text("Receive", color = Color.White)
//               }
//               Spacer(modifier = Modifier.width(30.dp))
//
//               Button(onClick = {
//
//               },colors = ButtonDefaults.buttonColors(Color.Black),
//               ) {
//                   Text("No receive", color = Color.White)
//               }
           }
            }

        }
    }



@Composable
fun ButtonView(
    text: String,
    color: Color,
    enabled: Boolean,
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
) {
    Button(
        onClick = {
            onClick()
        },
        colors = ButtonDefaults.buttonColors(
            containerColor = color,
            contentColor = Color.White
        ),
        enabled = enabled,
        modifier = modifier

    ) {
        Text(text = text)
    }

}
