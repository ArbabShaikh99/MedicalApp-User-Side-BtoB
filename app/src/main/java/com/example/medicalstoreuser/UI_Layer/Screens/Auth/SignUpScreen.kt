package com.example.medicalstoreuser.UI_Layer.Screens.Auth

import PreferenceManager
import android.util.Log
import android.widget.Toast
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
import com.example.medicalstoreuser.UI_Layer.Navigation.SignInScreen
import com.example.medicalstoreuser.UI_Layer.Navigation.VerificationScreeenRoute
import com.example.medicalstoreuser.UI_Layer.Screens.Common.MultiColorText
import com.example.medicalstoreuser.ViewModel.UserViewModel
import com.example.medicalstoreuser.ui.theme.lightBlackColor
import com.example.medicalstoreuser.ui.theme.softWhiteColor


@Composable
fun SignUpScreenUI(navController: NavController,userViewModel: UserViewModel = hiltViewModel()) {

    val context = LocalContext.current
    val SignUpResponseState by userViewModel.signupResponseState.collectAsState()
    val SignUpScreenState by userViewModel.signupScreenStateData.collectAsState()

    val prefence = PreferenceManager(context)

    when {
        SignUpResponseState.isLoading -> {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text(text = "Loading...")
            }
        }

        SignUpResponseState.data != null -> {
            LaunchedEffect(Unit) {
                if (SignUpResponseState.data!!.status == ISOK) {
                    val userId = SignUpResponseState.data!!.message
                    Log.d("Problem", "SignUp--- UserID: ${SignUpResponseState.data!!.message}")
                    Log.d("Problem", "SignUp--- Data: ${SignUpResponseState.data}")



                    prefence.setLoginUserId(userId)
                    prefence.setLoginEmailId(emailId = SignUpScreenState.email.value)

                    navController.navigate(VerificationScreeenRoute(userId)) {
                        navController.popBackStack()
                    }
                    userViewModel.resetSignupScreenStateData()

                    Toast.makeText(context, "Register User Successfully $userId", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(context, "Failed ${SignUpResponseState.data!!.message}", Toast.LENGTH_SHORT).show()
                }
            }

        }
            SignUpResponseState.error != null -> {
                Log.d("Problem", "SignUp--- Error: ${SignUpResponseState.error}")

                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text(text = SignUpResponseState.error!!.toString())
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
            Spacer(modifier = Modifier.height(65.dp))

            Text(
                text = "PharmaPe",
                modifier = Modifier.padding(16.dp),
                fontSize = 40.sp,
                fontWeight = FontWeight.ExtraBold,
                color = Color.Black
            )

            Spacer(modifier = Modifier.height(40.dp))
            Text(text="Create an Account Account             ",
                fontSize = 25.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black)
            Spacer(modifier = Modifier.height(25.dp))
            Column(
                modifier = Modifier.fillMaxSize()
                    .verticalScroll(scrollState)
                    .padding(horizontal = 16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(16.dp)

            ) {


                TextField(
                    value = SignUpScreenState.userName.value,
                    onValueChange = { SignUpScreenState.userName.value = it },
                    label = { Text("User name",color = Color.Black) },
                    modifier = Modifier.fillMaxWidth(),
                    colors = TextFieldDefaults.colors(
                        unfocusedContainerColor = softWhiteColor,
                        focusedContainerColor = softWhiteColor ,
                        focusedIndicatorColor = lightBlackColor
                    ),
                    maxLines = 1
                )
                TextField(
                    value = SignUpScreenState.email.value,
                    onValueChange = { SignUpScreenState.email.value = it },
                    label = { Text("Email Address",color = Color.Black) },
                    modifier = Modifier.fillMaxWidth(),
                    colors = TextFieldDefaults.colors(
                        unfocusedContainerColor = softWhiteColor,
                        focusedContainerColor = softWhiteColor ,
                        focusedIndicatorColor = lightBlackColor
                    ),
                    maxLines = 1
                )
                TextField(
                    value = SignUpScreenState.mobileNo.value,
                    onValueChange = { SignUpScreenState.mobileNo.value = it },
                    label = { Text("Contact number",color = Color.Black) },
                    modifier = Modifier.fillMaxWidth(),
                    colors = TextFieldDefaults.colors(
                        unfocusedContainerColor = softWhiteColor,
                        focusedContainerColor = softWhiteColor ,
                        focusedIndicatorColor = lightBlackColor
                    ),
                    maxLines = 1
                )

                TextField(
                    value = SignUpScreenState.address.value,
                    onValueChange = { SignUpScreenState.address.value = it },
                    label = { Text("Address",color = Color.Black) },
                    modifier = Modifier.fillMaxWidth(),
                    colors = TextFieldDefaults.colors(
                        unfocusedContainerColor = softWhiteColor,
                        focusedContainerColor = softWhiteColor ,
                        focusedIndicatorColor = lightBlackColor
                    ),
                    maxLines = 1
                )
                TextField(
                    value = SignUpScreenState.pinCode.value,
                    onValueChange = { SignUpScreenState.pinCode.value = it },
                    label = { Text("PinCode",color = Color.Black) },
                    modifier = Modifier.fillMaxWidth(),
                    colors = TextFieldDefaults.colors(
                        unfocusedContainerColor = softWhiteColor,
                        focusedContainerColor = softWhiteColor ,
                        focusedIndicatorColor = lightBlackColor
                    ),
                    maxLines = 1
                )

                TextField(
                    value = SignUpScreenState.password.value,
                    onValueChange = { SignUpScreenState.password.value = it },
                    label = { Text("Password",color = Color.Black) },
                    modifier = Modifier.fillMaxWidth(),
                    colors = TextFieldDefaults.colors(
                        unfocusedContainerColor = softWhiteColor,
                        focusedContainerColor = softWhiteColor ,
                        focusedIndicatorColor = Color.Black
                    ),
                    maxLines = 1
                )

//    LazyColumn(
//        modifier = Modifier.fillMaxSize(),
//        horizontalAlignment = Alignment.CenterHorizontally
//    ) {
//        item {
//            Spacer(modifier = Modifier.height(25.dp))
//            Image(
//                painter = painterResource(id = R.drawable.logo),
//                contentDescription = null,
//                modifier = Modifier
//                    .size(150.dp)
//                    .clip(CircleShape)
//            )
//            Spacer(modifier = Modifier.height(25.dp))

//            OutlinedTextField(
//                value = SignUpScreenState.address.value,
//                onValueChange = {
//                    SignUpScreenState.address.value = it
//                },
//                placeholder = { Text(text = "Enter Your Adreess") })
//
//            Spacer(modifier = Modifier.height(25.dp))
//
//
//            OutlinedTextField(
//                value = SignUpScreenState.pinCode.value,
//                onValueChange = {
//                    SignUpScreenState.pinCode.value = it
//                },
//                placeholder = { Text(text = "Enter Your PinCode") })

       //     Spacer(modifier = Modifier.height(8.dp))


            Button(onClick = {

                if(SignUpScreenState.userName.value.isNotEmpty() && SignUpScreenState.mobileNo.value.isNotEmpty() &&
                    SignUpScreenState.email.value.isNotEmpty() && SignUpScreenState.password.value.isNotEmpty() &&
                    SignUpScreenState.address.value.isNotEmpty() && SignUpScreenState.pinCode.value.isNotEmpty() )
                {
                userViewModel.SignUpUser(
                    name = SignUpScreenState.userName.value,
                    phoneNumber = SignUpScreenState.mobileNo.value,
                    email = SignUpScreenState.email.value,
                    password = SignUpScreenState.password.value,
                    address = SignUpScreenState.address.value,
                    pinCode = SignUpScreenState.pinCode.value
                )
            }
            else{
                    Toast.makeText(context, "Please enter All Fields", Toast.LENGTH_SHORT).show()
            }},
                colors = ButtonDefaults.buttonColors(Color.Black),
                modifier = Modifier.fillMaxWidth()) {
                Text(text = "Sign up")
            }
          //  Spacer(modifier = Modifier.height(8.dp))

            MultiColorText(
                firstText = "Already have an account? ",
                secondText = "Sign In"
            ) {
                navController.navigate(SignInScreen) {
                    navController.popBackStack()
                }


            }


        }
    }
}
    }
