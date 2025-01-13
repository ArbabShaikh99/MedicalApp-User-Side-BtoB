package com.example.medicalstoreuser.UI_Layer.Screens.UserProfile.Component

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.medicalstoreuser.R

@Composable
fun MetaVerifiedSection() {
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        // .padding(vertical = 8.dp),
        verticalAlignment = Alignment.Bottom,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Icon(
            painter = painterResource(id = R.drawable.meta), // Replace with appropriate Meta Verified icon
            contentDescription = "Meta Verified",
            modifier = Modifier.size(34.dp)
        )
        Spacer(Modifier.width(16.dp))
        Column ( modifier = Modifier.weight(.2f),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.Center){
            Text("Meta Verified", fontWeight = FontWeight.Bold, fontSize = 18.sp)
            Text("Not subscribed", color = Color.Gray, fontSize = 14.sp)
        }
        Icon(
            imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
            contentDescription = null,
            modifier = Modifier.size(24.dp),
            tint = Color.Black)
    }
}



@Composable
fun OrderPayment() {
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        // .padding(vertical = 8.dp),
        verticalAlignment = Alignment.Bottom,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Spacer(Modifier.width(10.dp))

        Column ( modifier = Modifier.weight(.2f),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.Center){
            Text("Your Order and fundraisers", style = MaterialTheme.typography.titleMedium, fontSize = 16.sp)
            Spacer(Modifier.height(10.dp))
            Row{
                Spacer(Modifier.width(20.dp))
                Icon(
                    painter = painterResource(id = R.drawable.baseline_bookmark_border_24),
                    contentDescription = null,
                    modifier = Modifier.size(24.dp)
                        .background(Color.White),
                    tint = Color.Black
                )
                Spacer(Modifier.width(7.dp))

                Text("Orders and payments", color = Color.Gray, fontSize = 15.sp)
            }

        }
        Icon(
            imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
            contentDescription = null,
            modifier = Modifier.size(24.dp),
            tint = Color.Black)
    }
}

@Composable
fun MoreInformation() {

    Column(
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.Bottom
    ) {
        Spacer(Modifier.height(10.dp))

        // Title
        Text(
            "More info and support",
            style = MaterialTheme.typography.titleMedium,
            fontSize = 16.sp,
            modifier = Modifier.padding(start = 10.dp)
        )
        Spacer(Modifier.height(10.dp))

        // Help Row
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp, vertical = 8.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    painter = painterResource(id = R.drawable.help),
                    contentDescription = "Help",
                    modifier = Modifier.size(25.dp),
                    tint = Color.Black
                )
                Spacer(Modifier.width(7.dp))
                Text(
                    "Help",
                    color = Color.Black,
                    fontSize = 16.sp
                )
            }
            Icon(
                imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
                contentDescription = null,
                modifier = Modifier.size(24.dp),
                tint = Color.Black
            )
        }

        // Privacy Centre Row
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp, vertical = 8.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    painter = painterResource(id = R.drawable.privacy),
                    contentDescription = "Privacy Centre",
                    modifier = Modifier.size(25.dp),
                    tint = Color.Black
                )
                Spacer(Modifier.width(7.dp))
                Text(
                    "Privacy Centre",
                    color = Color.Black,
                    fontSize = 16.sp
                )
            }
            Icon(
                imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
                contentDescription = null,
                modifier = Modifier.size(24.dp),
                tint = Color.Black
            )
        }
        //Account Status
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp, vertical = 8.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    painter = painterResource(id = R.drawable.accountstatus),
                    contentDescription = "Account Status",
                    modifier = Modifier.size(25.dp),
                    tint = Color.Black
                )
                Spacer(Modifier.width(7.dp))
                Text(
                    "Account Status",
                    color = Color.Black,
                    fontSize = 16.sp
                )
            }
            Icon(
                imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
                contentDescription = null,
                modifier = Modifier.size(24.dp),
                tint = Color.Black
            )
        }
        // About
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp, vertical = 8.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    painter = painterResource(id = R.drawable.about),
                    contentDescription = "About",
                    modifier = Modifier.size(25.dp),
                    tint = Color.Black
                )
                Spacer(Modifier.width(7.dp))
                Text(
                    "About",
                    color = Color.Black,
                    fontSize = 16.sp
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
}





@Composable
fun SocialMediaAccount() {

    val context = LocalContext.current

    Column(
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.Bottom
    ) {


        // Title
        Text(
            "Also from Social Media",
            style = MaterialTheme.typography.titleMedium,
            fontSize = 16.sp,
            modifier = Modifier.padding(start = 10.dp)
        )
        Spacer(Modifier.height(10.dp))

        // Help Row
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp, vertical = 8.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    painter = painterResource(id = R.drawable.whatsap),
                    contentDescription = "WhatsApp",
                    modifier = Modifier.size(25.dp),
                    tint = Color.Black
                )
                Spacer(Modifier.width(7.dp))
                Text(
                    "WhatsApp",
                    color = Color.Black,
                    fontSize = 16.sp
                )
            }
            Icon(
                imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
                contentDescription = null,
                modifier = Modifier.size(24.dp),
                tint = Color.Black
            )
        }

        // Privacy Centre Row
        Row(
            modifier = Modifier
                .fillMaxWidth()
           .clickable {
                val intent = Intent(
                    Intent.ACTION_VIEW,
                    Uri.parse("https://www.linkedin.com/in/arbab-shaikh/")
                )
                context.startActivity(intent)
            }
                .padding(horizontal = 20.dp, vertical = 8.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    painter = painterResource(id = R.drawable.linkedin),
                    contentDescription = "Linkedin",
                    modifier = Modifier.size(25.dp),
                    tint = Color.Black
                )
                Spacer(Modifier.width(7.dp))
                Text(
                    "Linkedin",
                    color = Color.Black,
                    fontSize = 16.sp
                )
            }
            Icon(
                imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
                contentDescription = null,
                modifier = Modifier.size(24.dp),
                tint = Color.Black
            )
        }
        //Account Status
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp, vertical = 8.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    painter = painterResource(id = R.drawable.facebook),
                    contentDescription = "Facebook",
                    modifier = Modifier.size(25.dp),
                    tint = Color.Black
                )
                Spacer(Modifier.width(7.dp))
                Text(
                    "Facebook",
                    color = Color.Black,
                    fontSize = 16.sp
                )
            }
            Icon(
                imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
                contentDescription = null,
                modifier = Modifier.size(24.dp),
                tint = Color.Black
            )
        }
        // About
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clickable {
                    val intent = Intent(
                        Intent.ACTION_VIEW,
                        Uri.parse("https://www.instagram.com/in/application.developer/")
                    )
                    context.startActivity(intent)
                }
                .padding(horizontal = 20.dp, vertical = 8.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    painter = painterResource(id = R.drawable.instagram),
                    contentDescription = "Instagram",
                    modifier = Modifier.size(25.dp),
                    tint = Color.Black
                )
                Spacer(Modifier.width(7.dp))
                Text(
                    "Instagram",
                    color = Color.Black,
                    fontSize = 16.sp
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
}