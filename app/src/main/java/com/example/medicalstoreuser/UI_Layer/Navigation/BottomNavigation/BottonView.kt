package com.example.medicalstoreuser.UI_Layer.Navigation.BottomNavigation


import PreferenceManager
import android.util.Log
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.medicalstoreuser.UI_Layer.Screens.Cart.CartScreenUI
import com.example.medicalstoreuser.UI_Layer.Screens.Home.HomeScreenUI
import com.example.medicalstoreuser.UI_Layer.Screens.Search.SearchScreenUI
import com.example.medicalstoreuser.UI_Layer.Screens.UserProfile.UserProfileScreenUI
import com.example.medicalstoreuser.ViewModel.ProductViewModel
import com.example.medicalstoreuser.ViewModel.UserViewModel
import com.example.medicalstoreuser.local.viewmodel.RoomCartViewModel
import com.example.medicalstoreuser.ui.theme.navigationColor
import com.example.medicalstoreuser.ui.theme.softWhiteColor

@Composable
fun BottomView(navController: NavController,
     userViewModel: UserViewModel,
       productViewModel: ProductViewModel
) {
    val context = LocalContext.current
    val prefenceManager = PreferenceManager(context)
    val roomCartViewmodel : RoomCartViewModel = hiltViewModel()

    //userViewModel.getSpecificUserVM(userId = prefenceManager.getLoginUserId().toString())
    val getSpecificUser by userViewModel.getSpecificUser.collectAsState()

    var isApproved by remember {
        mutableStateOf(0)
    }
    var userName by remember {
        mutableStateOf("Arbab")
    }

    when {
        getSpecificUser.loading -> {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text(text = "Loading...")
            }
        }

        getSpecificUser.data != null -> {
            Log.d("@@verify", "Enter The App: ${getSpecificUser.data}")
            if (getSpecificUser.data!!.isSuccessful) {
                val isVerifiedAccount = getSpecificUser.data!!.body()!![0].isApproved
                val name = getSpecificUser.data!!.body()!![0].name
                LaunchedEffect(isVerifiedAccount) {
                    isApproved = isVerifiedAccount
                    // userName = name

                    prefenceManager.setApprovedStatus(isVerifiedAccount)

                }
            }
        }

        getSpecificUser.Error != null -> {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.BottomEnd) {
                Text(text = "Something went wrong")
            }
        }
    }


    var selected by remember {
        mutableIntStateOf(0)
    }

    val bottomNavItem = listOf(
        BotoomNavItem(
            name = "Home",
            icon = Icons.Default.Home
        ),
        BotoomNavItem(
            name = "Buy",
            icon = Icons.Default.ShoppingCart
        ),
        BotoomNavItem(
            name = "Search",
            icon = Icons.Default.Search
        ),

        BotoomNavItem(
            name = "Profile",
            icon = Icons.Default.Person
        )
    )

    Box {
        Scaffold(
            bottomBar = {
                NavigationBar(
                    containerColor = softWhiteColor,
//                    modifier = Modifier.height(400.dp)// Set the bottom navigation bar color to gray
                ) {
                    bottomNavItem.forEachIndexed { index, botoomNavItem ->
                        NavigationBarItem(
                            selected = selected == index,
                            onClick = { selected = index },
                            icon = {
                                Icon(
                                    botoomNavItem.icon,
                                    contentDescription = null,
                                    tint = Color.Black
                                )
                            },
                            label = { Text(botoomNavItem.name, color = Color.Black) }

                        )
                    }
                }

            }
        ) {    innerpadding ->
            Box(modifier = Modifier.fillMaxSize().padding(innerpadding)){
                when(selected){
                    0 -> HomeScreenUI(navController, userViewModel, productViewModel)
                    1 -> CartScreenUI(roomCartViewmodel, navController)
                    2 -> SearchScreenUI(productViewModel, navController)
                    3 -> UserProfileScreenUI(userViewModel, prefenceManager, navController)

                }
            }
        }
    }

}















//@Composable
//fun BottomView (navController: NavController){
//
//   val  userViewModel: UserViewModel = hiltViewModel()
//    val productViewModel : ProductViewModel = hiltViewModel()
//
//    var selected by remember {
//        mutableIntStateOf(0)
//    }
//
//    val bottomNavItem = listOf(
//        BotoomNavItem(
//            name = "Home",
//            icon = Icons.Default.Home
//        ),
//        BotoomNavItem(
//            name = "Search",
//            icon = Icons.Default.Search
//        ),
//        BotoomNavItem(
//            name = "Profile",
//            icon = Icons.Default.Person
//        )
//
//    )
//
//    Box{
//        Scaffold (
//            bottomBar = {
//                NavigationBar(containerColor = navigationColor
//                  //, modifier = Modifier.height(85.dp),
//                ) {
//                    bottomNavItem.forEachIndexed { index, botoomNavItem ->
//                        NavigationBarItem(
//                            selected = selected == index ,
//                            onClick = {selected = index },
//                            icon = { Icon(botoomNavItem.icon , contentDescription = null, tint = Color.Black) },
//                           label = { Text(botoomNavItem.name, color = Color.Black) },
//                        )
//
//
//
//                    }
//                }
//
//            }
//        ){
//                innerpadding ->
//            Box(modifier = Modifier.fillMaxSize().padding(innerpadding)){
//                when(selected){
//                    0 -> HomeScreenUI(navController,userViewModel,productViewModel)
//                    1 -> SearchScreenUI(productViewModel,navController)
//
//
//                    //4 ->
//                }
//            }
//        }
//    }
//
//}

