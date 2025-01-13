package com.example.medicalstoreuser.UI_Layer.Screens.Auth

import PreferenceManager
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.example.medicalstoreuser.R
import com.example.medicalstoreuser.UI_Layer.Componet.ButtonComponent
import com.example.medicalstoreuser.UI_Layer.Navigation.BottomViewRoute
import com.example.medicalstoreuser.UI_Layer.Navigation.HomeScreen
import com.example.medicalstoreuser.ViewModel.UserViewModel

@Composable
fun VerificationPendingScreen( userId: String,
    userViewModel: UserViewModel, navController: NavController
) {

    val context = LocalContext.current
    val preferenceManager = PreferenceManager(context)

    userViewModel.getSpecificUserVM(userId)
    val getSpecificUsers by userViewModel.getSpecificUser.collectAsState()


    var isApproved by remember {
        mutableStateOf(1    )
    }

    when {
        getSpecificUsers.loading -> {
           // Log.d("@Verification", "VerificationPendingScreen----Lodaing: ${getSpecificUsers.loading}")

            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text(text = "Loading...")
            }
        }

        getSpecificUsers.data != null -> {
            Log.d("@Verification", "VerificationPendingScreen: ${getSpecificUsers.data}")
            val isVerifiedAccount = getSpecificUsers.data!!.body()!![0].isApproved
            Log.d("@Verification", "isVerifiedAccount: $isVerifiedAccount")
            LaunchedEffect(isVerifiedAccount) {
                isApproved = isVerifiedAccount
                //Log.d("@Verification", "isVerifiedAccount: $isVerifiedAccount")
                preferenceManager.setApprovedStatus(isVerifiedAccount)
                Log.d("@Preference", "isApproved Status Saved: ${preferenceManager.getApprovedStatus()}")

            }
            Log.d("@isApproved", "Verification Screen: ${getSpecificUsers.data!!.body()!![0].isApproved}")

        }


           getSpecificUsers.Error != null -> {
      // Log.d("@Verification", "VerificationPendingScreen----Error: ${getSpecificUsers.Error}")

                    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text(text = getSpecificUsers.Error!!)
            }
        }
    }

    val preloaderLottieComposition = rememberLottieComposition(
        spec = LottieCompositionSpec.RawRes(
            if (isApproved == 0)
                R.raw.pending_lottie
            else
                R.raw.verified_lottie

        )
    )

    val preloaderProgress = animateLottieCompositionAsState(
        composition = preloaderLottieComposition.value,
        iterations = LottieConstants.IterateForever,
        isPlaying = true
    )

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center

    ) {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(
               Brush.verticalGradient(
                    colors = listOf(Color(0xFFF6F6E8), Color(0xFFEFEFDC))
               ))
               // .background(Color.Blue)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Spacer(modifier = Modifier.height(16.dp))

            LottieAnimation(
                composition = preloaderLottieComposition.value,
                progress = preloaderProgress.value,
                modifier = Modifier.size(200.dp)
            )
            Spacer(modifier = Modifier.height(32.dp))

            Text(
                text = if (isApproved == 1) "VERIFIED" else "PENDING", style = TextStyle(
                    color = Color.Black,
                    fontSize = 40.sp,
                    fontWeight = FontWeight.Bold,
                    fontFamily = FontFamily(Font(R.font.roboto_bold))
                ), textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = if (isApproved == 1) "You have Successfully verified the Account." else "Verification your pending\n Please wait.",
                style = TextStyle(
                    color = Color.Gray,
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Normal,
                    fontFamily = FontFamily(Font(R.font.roboto_regular))
                ),
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(16.dp))

            ButtonComponent(
                text = if (isApproved == 1) "Go, in the app" else "Wait"
            ) {
                //here to do on click
                if (isApproved == 1) {
                    navController.navigate(BottomViewRoute) {
                        navController.popBackStack()
                    }
                }
            }

        }


    }
}