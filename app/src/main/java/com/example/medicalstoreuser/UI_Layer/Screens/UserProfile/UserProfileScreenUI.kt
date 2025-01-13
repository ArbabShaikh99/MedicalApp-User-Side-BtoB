package com.example.medicalstoreuser.UI_Layer.Screens.UserProfile


import PreferenceManager
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.medicalstoreuser.UI_Layer.Navigation.MyAccountScreenRoute
import com.example.medicalstoreuser.UI_Layer.Navigation.SignInScreen
import com.example.medicalstoreuser.UI_Layer.Screens.Cart.Component.CartTopBar
import com.example.medicalstoreuser.UI_Layer.Screens.UserProfile.Component.LogoutScreen
import com.example.medicalstoreuser.UI_Layer.Screens.UserProfile.Component.MetaVerifiedSection
import com.example.medicalstoreuser.UI_Layer.Screens.UserProfile.Component.MoreInformation
import com.example.medicalstoreuser.UI_Layer.Screens.UserProfile.Component.OrderPayment
import com.example.medicalstoreuser.UI_Layer.Screens.UserProfile.Component.PersonalInformationOfUser
import com.example.medicalstoreuser.UI_Layer.Screens.UserProfile.Component.SocialMediaAccount
import com.example.medicalstoreuser.ViewModel.UserViewModel


@Composable
fun UserProfileScreenUI(
    userViewModel: UserViewModel,
    preferenceManager: PreferenceManager,
    navController: NavController
) {
    var showDialog by remember {mutableStateOf(false) }
    val context = LocalContext.current
    var selectedItem by remember {
        mutableIntStateOf(3)
    }
    LaunchedEffect(Unit) {
        userViewModel.getSpecificUserVM(preferenceManager.getLoginUserId()!!)
        Log.d("@Acc", "preferenceManager Check: ${preferenceManager.getLoginUserId()!!}")

    }
    val getSpecificUser by userViewModel.getSpecificUser.collectAsState()
    val profileScreenStateUserData by userViewModel.UserUpdateScreenStateUserData.collectAsState()
    val updatedProfileResponseState by userViewModel.updateProfileResponseState.collectAsState()

    when {
        getSpecificUser.loading -> {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator(color = Color.Black)
            }
        }

        getSpecificUser.data != null -> {
            profileScreenStateUserData.userName.value = getSpecificUser.data!!.body()!![0].name
            profileScreenStateUserData.userPhone.value = getSpecificUser.data!!.body()!![0].phone_number
            profileScreenStateUserData.userEmail.value = getSpecificUser.data!!.body()!![0].email
            profileScreenStateUserData.pinCode.value = getSpecificUser.data!!.body()!![0].pinCode
            profileScreenStateUserData.password.value = getSpecificUser.data!!.body()!![0].password
            profileScreenStateUserData.address.value = getSpecificUser.data!!.body()!![0].address
            profileScreenStateUserData.dateOfCreationAccount.value =
                getSpecificUser.data!!.body()!![0].date_of_account_creation
            //profileScreenStateUserData.userImageId.value = getSpecificUser.data!![0].user_image_id
        }

        getSpecificUser.Error != null -> {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text(text = getSpecificUser.Error!!)
            }
        }
    }

    //when user data updated response
    when {
        updatedProfileResponseState.isLoading -> {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator(color = Color.Black)
            }
        }

        updatedProfileResponseState.data != null -> {
            LaunchedEffect(updatedProfileResponseState.data!!.status) {
                if (updatedProfileResponseState.data!!.status == 200) {
                    // userViewModel.resetProfileScreenStateData()
                    Toast.makeText(
                        context,
                        updatedProfileResponseState.data!!.message,
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
            Log.d("@Acc", "ProfileScreen updated data: ${updatedProfileResponseState.data!!.message}"
            )

        }

        updatedProfileResponseState.error != null -> {
            Log.d("@Acc", "ProfileScreen updated Error: ${updatedProfileResponseState.error}")
            Toast.makeText(context, updatedProfileResponseState.error, Toast.LENGTH_SHORT).show()
        }
    }



    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        contentAlignment = Alignment.BottomEnd
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.Top
        ) {
            item {
                CartTopBar(
                    headerName = "Setting and Edit",
                    isBackButtonShow = false
                )
                Spacer(modifier = Modifier.height(8.dp))
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color.White)
                        .padding(8.dp)
                ) {
                    PersonalInformationOfUser(
                        userName = "ArabShaikh99",
                        userImageId = profileScreenStateUserData.userImageId.value
                    ) {
                        navController.navigate(MyAccountScreenRoute)
                    }
                    Spacer(modifier = Modifier.height(20.dp))

                    MetaVerifiedSection()
                    Spacer(modifier = Modifier.height(16.dp))

                    OrderPayment()
                    Spacer(modifier = Modifier.height(10.dp))

                    MoreInformation()
                    Spacer(modifier = Modifier.height(20.dp))

                    SocialMediaAccount()

                    Text(
                        text = "Login",
                        style = MaterialTheme.typography.titleMedium,
                        modifier = Modifier.padding(vertical = 8.dp)
                    )

                    LogoutScreen(preferenceManager,navController)

                }
            }
        }


    }
}
