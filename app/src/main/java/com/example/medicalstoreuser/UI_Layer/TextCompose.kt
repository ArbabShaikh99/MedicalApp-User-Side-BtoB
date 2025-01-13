///*
//package com.example.medicalstoreuser.UI_Layer
//
//import androidx.compose.foundation.Image
//import androidx.compose.foundation.clickable
//import androidx.compose.foundation.layout.Spacer
//import androidx.compose.foundation.layout.fillMaxSize
//import androidx.compose.foundation.layout.height
//import androidx.compose.foundation.layout.size
//import androidx.compose.foundation.lazy.LazyColumn
//import androidx.compose.foundation.shape.CircleShape
//import androidx.compose.material3.Button
//import androidx.compose.material3.OutlinedTextField
//import androidx.compose.material3.Text
//import androidx.compose.runtime.Composable
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.draw.clip
//import androidx.compose.ui.res.painterResource
//import androidx.compose.ui.tooling.preview.Preview
//import androidx.compose.ui.unit.dp
//import com.example.medicalstoreuser.R
//import com.example.medicalstoreuser.UI_Layer.Screens.Common.MultiColorText
//
//@Preview(showBackground = true, showSystemUi = true)
//@Composable
//fun TextCompose() {
//LazyColumn( modifier = Modifier.fillMaxSize(),
//horizontalAlignment = Alignment.CenterHorizontally) {
//item {
//Spacer(modifier = Modifier.height(25.dp))
//Image(painter = painterResource(id = R.drawable.logo),
//contentDescription = null,
//modifier = Modifier
//.size(150.dp)
//.clip(CircleShape)
//)
//Spacer(modifier = Modifier.height(25.dp))
//
//
//OutlinedTextField(value = "", onValueChange = {},
//placeholder = { Text(text = "Enter Your Email")})
//
//Spacer(modifier = Modifier.height(25.dp))
//
//
//OutlinedTextField(value = "", onValueChange = {},
//placeholder = { Text(text = "Enter Your Password")})
//
//
//Spacer(modifier = Modifier.height(25.dp))
//
//
//Button(onClick = {
//
//}) {
//Text(text = "Add User")
//}
//Spacer(modifier = Modifier.height(25.dp))
//
//MultiColorText("Don't have an account?","Sign Up",
//modifier = Modifier.clickable {
//
//})
//
//}
//
//
//}
//}
//*/
//
//import androidx.compose.material.icons.Icons
//import androidx.compose.material.icons.filled.Home
//import androidx.compose.material.icons.filled.Person
//import androidx.compose.material.icons.filled.Search
//import androidx.compose.material3.Icon
//import androidx.compose.material3.Scaffold
//import androidx.compose.material3.Surface
//import androidx.compose.material3.Text
//import androidx.compose.runtime.Composable
//import androidx.compose.ui.graphics.vector.ImageVector
//import androidx.navigation.NavController
//import androidx.navigation.NavGraph.Companion.findStartDestination
//import androidx.navigation.NavHostController
//import androidx.navigation.compose.NavHost
//import androidx.navigation.compose.composable
//import androidx.navigation.compose.rememberNavController
//import androidx.compose.material.*
//
//// Bottom Navigation Item Data Class
//data class BottomNavItem(
//    val name: String,
//    val route: String,
//    val icon: ImageVector
//)
//
//// Bottom Navigation Setup
//@Composable
//fun BottomNavigationBar(
//    navController: NavController,
//    items: List<BottomNavItem>
//) {
//    BottomNavigation {
//        items.forEach { item ->
//            BottomNavigationItem(
//                selected = navController.currentDestination?.route == item.route,
//                onClick = {
//                    navController.navigate(item.route) {
//                        popUpTo(navController.graph.findStartDestination().id) {
//                            saveState = true
//                        }
//                        launchSingleTop = true
//                        restoreState = true
//                    }
//                },
//                icon = {
//                    Icon(imageVector = item.icon, contentDescription = item.name)
//                },
//                label = { Text(text = item.name) }
//            )
//        }
//    }
//}
//
//// Main Screen with Bottom Navigation
//@Composable
//fun MainScreen() {
//    val navController = rememberNavController()
//    val items = listOf(
//        BottomNavItem("Home", "home", Icons.Default.Home),
//        BottomNavItem("Search", "search", Icons.Default.Search),
//        BottomNavItem("Profile", "profile", Icons.Default.Person)
//    )
//
//    Scaffold(
//        bottomBar = { BottomNavigationBar(navController, items) }
//    ) {
//        NavigationGraph(navController = navController)
//    }
//}
//
//// Navigation Graph
//@Composable
//fun NavigationGraph(navController: NavHostController) {
//    NavHost(
//        navController = navController,
//        startDestination = "home"
//    ) {
//        composable("home") {
//            HomeScreen()
//        }
//        composable("search") {
//            SearchScreen()
//        }
//        composable("profile") {
//            ProfileScreen()
//        }
//    }
//}
//
//// Screens
//@Composable
//fun HomeScreen() {
//    Surface {
//        Text("Home Screen")
//    }
//}
//
//@Composable
//fun SearchScreen() {
//    Surface {
//        Text("Search Screen")
//    }
//}
//
//@Composable
//fun ProfileScreen() {
//    Surface {
//        Text("Profile Screen")
//    }
//}
