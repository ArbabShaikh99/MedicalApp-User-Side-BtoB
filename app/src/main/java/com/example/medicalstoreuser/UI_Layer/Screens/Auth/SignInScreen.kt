package com.example.medicalstoreuser.UI_Layer.Screens.Auth

import PreferenceManager
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.medicalstoreuser.Data_Layer.ISOK
import com.example.medicalstoreuser.R
import com.example.medicalstoreuser.UI_Layer.Navigation.BottomViewRoute
import com.example.medicalstoreuser.UI_Layer.Navigation.HomeScreen
import com.example.medicalstoreuser.UI_Layer.Navigation.SignUpScreen
import com.example.medicalstoreuser.UI_Layer.Navigation.VerificationScreeenRoute
import com.example.medicalstoreuser.UI_Layer.Screens.Common.MultiColorText
import com.example.medicalstoreuser.ViewModel.UserViewModel
import com.example.medicalstoreuser.ui.theme.lightBlackColor
import com.example.medicalstoreuser.ui.theme.softWhiteColor


@Composable
fun SignInScreenUI (navController: NavController,userViewModel: UserViewModel= hiltViewModel()) {
    val context = LocalContext.current
    val loginResponseState by userViewModel.loginResponseState.collectAsState()
    val loginScreeenState by userViewModel.loginScreenStateData.collectAsState()

    val preferenceManager = PreferenceManager(context)

    when {
        loginResponseState.isLoading -> {

            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text("isLoading")
            }
        }
//            && loginResponseState.data!!.message != null
        loginResponseState.data != null -> {
            LaunchedEffect(loginResponseState.data) {
                if (loginResponseState.data!!.status == ISOK) {
                    val userId = loginResponseState.data!!.message
                    Log.d("@Verification", "loginResponseState : ${loginResponseState.data}")
                    Log.d("@Verification", "loginResponseState-userID : ${userId}")

                    preferenceManager.setLoginUserId(userId)
                    preferenceManager.setLoginEmailId(emailId = loginScreeenState.email.value)
                    userViewModel.resetLoginScreenStateData()
                    userViewModel.clearLoginResponseStateData()

                    // Retrieve approval status and navigate accordingly
                    val isApproved = preferenceManager.getApprovedStatus()
                    Log.d("@Verification", "Retrieved isApproved: $isApproved")

                    if (isApproved == 1) {
                        Log.d("@Verification", "User enter in the App")
                        navController.navigate(BottomViewRoute) {
                            navController.popBackStack()
                        }
                    } else {
                        Log.d("@Verification", "Navigating to VerificationScreen")
                        navController.navigate(VerificationScreeenRoute(userId)) {
                            navController.popBackStack()
                        }
                    }

                    Toast.makeText(context, "Login Successfully $userId", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(context, "Wrong Credentials", Toast.LENGTH_SHORT).show()
                }
            }

            Log.d("@@login", "SignInScreen: ${loginResponseState.data!!.message}")
            Log.d("@@login", "SignInScreen: ${loginResponseState.data!!.status}")
        }


        loginResponseState.error != null -> {
            val response = loginResponseState.error.toString()
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text(text = response)
            }
        }
    }
    val scrollState = rememberScrollState()

    Box(modifier = Modifier.fillMaxSize().background(softWhiteColor)) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            Spacer(modifier = Modifier.height(120.dp))

            Text(
                text = "PharmaPe",
                modifier = Modifier.padding(16.dp),
                fontSize = 40.sp,
                fontWeight = FontWeight.ExtraBold,
                color = Color.Black
            )

            Spacer(modifier = Modifier.height(40.dp))

            Column(
                modifier = Modifier.fillMaxSize()
                    .verticalScroll(scrollState)
                    .padding(horizontal = 16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(16.dp)

            ) {
                Text(text="Login to your Account                ",
                    fontSize = 25.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black)
                Spacer(modifier = Modifier.height(5.dp))

                TextField(
                    value = loginScreeenState.email.value,
                    onValueChange = { loginScreeenState.email.value = it },
                    label = { Text("Email Address",color = Color.Black) },
                    modifier = Modifier.fillMaxWidth(),
                    colors = TextFieldDefaults.colors(
                        unfocusedContainerColor = softWhiteColor,
                        focusedContainerColor = softWhiteColor ,
                        focusedIndicatorColor = lightBlackColor),
                    maxLines = 1
                )

             //   Spacer(modifier = Modifier.height(8.dp))

                TextField(

                    value = loginScreeenState.password.value,
                    onValueChange = { loginScreeenState.password.value = it},
                    label = { Text("Password", color = Color.Black) },
                    modifier = Modifier.fillMaxWidth(),
                    colors = TextFieldDefaults.colors(
                        unfocusedContainerColor = softWhiteColor,
                        focusedContainerColor = softWhiteColor ,
                        focusedIndicatorColor = lightBlackColor,
                      // unfocusedIndicatorColor = Color.Black
                    ),
                    maxLines = 1
                )
                Spacer(modifier = Modifier.height(8.dp))

//
//                OutlinedTextField(value = loginScreeenState.email.value, onValueChange = {
//                    loginScreeenState.email.value = it
//                },
//                    placeholder = { Text(text = "Enter Your Email") })
//



                Button(onClick = {
                    if (loginScreeenState.email.value.isNotEmpty() && loginScreeenState.password.value.isNotEmpty()) {


                        userViewModel.loginUser(
                            email = loginScreeenState.email.value,
                            password = loginScreeenState.password.value
                        )
                    } else {
                        Toast.makeText(context, "Please enter All Fields", Toast.LENGTH_SHORT)
                            .show()
                    }
                }, colors = ButtonDefaults.buttonColors(Color.Black),
                    modifier = Modifier.fillMaxWidth()
                     .background(shape = RoundedCornerShape(8.dp), color = Color.Black)
                ) {
                    Text(text = "Sign in")
                }
               // Spacer(modifier = Modifier.height(10.dp))

                MultiColorText("Don't have an account? ", "Sign up") {

                    navController.navigate(SignUpScreen)

                }

            }


        }

    }    }