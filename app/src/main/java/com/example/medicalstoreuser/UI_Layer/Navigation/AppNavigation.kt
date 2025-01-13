package com.example.medicalstoreuser.UI_Layer.Navigation

import PreferenceManager
import android.util.Log
import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.core.EaseIn
import androidx.compose.animation.core.EaseOut
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.example.medicalstoreuser.UI_Layer.Navigation.BottomNavigation.BottomView
import com.example.medicalstoreuser.UI_Layer.Screens.Auth.SignInScreenUI
import com.example.medicalstoreuser.UI_Layer.Screens.Auth.SignUpScreenUI
import com.example.medicalstoreuser.UI_Layer.Screens.Auth.VerificationPendingScreen
import com.example.medicalstoreuser.UI_Layer.Screens.Cart.CartScreenUI
import com.example.medicalstoreuser.UI_Layer.Screens.Home.HomeScreenUI
import com.example.medicalstoreuser.UI_Layer.Screens.Order.AddressScreen
import com.example.medicalstoreuser.UI_Layer.Screens.Order.Component.OrderItemView
import com.example.medicalstoreuser.UI_Layer.Screens.Order.CreateOrderScreen
import com.example.medicalstoreuser.UI_Layer.Screens.Product.ProductDetailUI
import com.example.medicalstoreuser.UI_Layer.Screens.ReciveOrder.ReceiveOrderUI
import com.example.medicalstoreuser.UI_Layer.Screens.Search.SearchScreenUI
import com.example.medicalstoreuser.UI_Layer.Screens.UserProfile.Component.EditProfile
import com.example.medicalstoreuser.UI_Layer.Screens.UserProfile.UserProfileScreenUI
import com.example.medicalstoreuser.ViewModel.OrderViewmodel
import com.example.medicalstoreuser.ViewModel.ProductViewModel
import com.example.medicalstoreuser.ViewModel.UserViewModel
import com.example.medicalstoreuser.local.model.ClientChoiceModelEntity
import com.example.medicalstoreuser.local.viewmodel.RoomAddressViewModel
import com.example.medicalstoreuser.local.viewmodel.RoomCartViewModel
import kotlinx.serialization.json.Json


@Composable
fun AppNavigation  (
     navController: NavController
) {

    val navController = rememberNavController()
    //val userViewModel:UserViewModel = hiltViewModel()
    val context = LocalContext.current

//    val userid by userPrefenceManager.userId.collectAsState(initial = null)
//
//    LaunchedEffect(userid) {
//        if(userid !=null){
//            navController.navigate()
//        }
//        else{
//            navController.navigate(SignInScreen)
//        }
//    }
    val userViewModel: UserViewModel = hiltViewModel()
    val productViewModel: ProductViewModel = hiltViewModel()
    val roomCartViewmodel : RoomCartViewModel = hiltViewModel()
    val orderViewModel : OrderViewmodel = hiltViewModel()
    val roomAddressViewModel :RoomAddressViewModel = hiltViewModel()

    val preferenceManager = PreferenceManager(context)
    val currentStatus = preferenceManager.getApprovedStatus()
    Log.d("@PreferenceCheck", "Current Approved Status: $currentStatus")

    Log.d("@@Nav", "AppNavigation: ${preferenceManager.getLoginUserId()}")
    Log.d("@@Nav", "AppNavigation: ${preferenceManager.getApprovedStatus()}")

    NavHost(
        navController = navController, startDestination =
          if (preferenceManager.getLoginUserId() != "" &&
        preferenceManager.getApprovedStatus() == 1
    )
              BottomViewRoute
    else if (preferenceManager.getLoginUserId() != "" && preferenceManager.getApprovedStatus() == 0)
        VerificationScreeenRoute(preferenceManager.getLoginUserId()!!)
    else
        SignInScreen,
        enterTransition = { EnterTransition.None},
        exitTransition = { ExitTransition.None}
    )
    {
        composable<ReceiveOrderRoute> {
            ReceiveOrderUI()
        }
        composable<BottomViewRoute> {
            BottomView(navController,userViewModel,productViewModel)
        }
        composable<SignInScreen> {
            SignInScreenUI(navController)
        }
        composable<SignUpScreen> {
            SignUpScreenUI(navController)
        }

        composable<HomeScreen> {
            HomeScreenUI(navController, userViewModel, productViewModel)
        }
        composable<SearchScreenRoutes> {
            SearchScreenUI(productViewModel, navController)
        }
        composable<UserProfilerRoute> {
            UserProfileScreenUI(userViewModel,preferenceManager,navController)
        }

        composable<MyAccountScreenRoute>{
            EditProfile(navController,userViewModel,preferenceManager)
        }

        composable<VerificationScreeenRoute>(
            enterTransition = {
                fadeIn(
                    animationSpec = tween(
                        500, easing = LinearEasing
                    )
                ) + slideIntoContainer(
                    animationSpec = tween(500, easing = EaseIn),
                    towards = AnimatedContentTransitionScope.SlideDirection.Up
                )
            }
        ) { navBackStackEntry ->
            val userId = navBackStackEntry.toRoute<VerificationScreeenRoute>().userId
            VerificationPendingScreen(userId, userViewModel, navController)

        }



        composable<ProductDetailScreenRoute>(
            enterTransition = {
                fadeIn(
                    animationSpec = tween(
                        400, easing = LinearEasing
                    )
                ) + slideIntoContainer(
                    animationSpec = tween(400, easing = EaseIn),
                    towards = AnimatedContentTransitionScope.SlideDirection.Up
                )
            }
    ){navBackStackEntry ->
            val productName = navBackStackEntry.toRoute<ProductDetailScreenRoute>().productName
            val productId = navBackStackEntry.toRoute<ProductDetailScreenRoute>().productId
            val productImageId =
                navBackStackEntry.toRoute<ProductDetailScreenRoute>().productImageId
            val productPrice = navBackStackEntry.toRoute<ProductDetailScreenRoute>().productPrice
            val productRating = navBackStackEntry.toRoute<ProductDetailScreenRoute>().productRating
            val productStock = navBackStackEntry.toRoute<ProductDetailScreenRoute>().productStock
            val productDescription =
                navBackStackEntry.toRoute<ProductDetailScreenRoute>().productDescription
            val productPower = navBackStackEntry.toRoute<ProductDetailScreenRoute>().productPower
            val productCategory =
                navBackStackEntry.toRoute<ProductDetailScreenRoute>().productCategory
            val productExpiryDate =
                navBackStackEntry.toRoute<ProductDetailScreenRoute>().productExpiryDate

            val productItem = ClientChoiceModelEntity(
                product_name = productName,
                product_image_id = productImageId,
                product_price = productPrice,
                product_rating = productRating,
                product_stock = productStock,
                product_description = productDescription,
                product_power = productPower,
                product_category = productCategory,
                product_expiry_date = productExpiryDate,
                product_id = productId,
            )

            ProductDetailUI(roomCartViewmodel,productItem , navController)


        }
        composable<CartScreenRoute>(
            enterTransition ={
                fadeIn(
                    animationSpec = tween(
                        400, easing = LinearEasing
                    )
                )+slideIntoContainer(
                    animationSpec = tween(400, easing = EaseIn),
                    towards = AnimatedContentTransitionScope.SlideDirection.End
                )
            }
        ) {
            CartScreenUI(roomCartViewmodel,navController)
        }

        composable<CreateOrderScreenRoute>(
            enterTransition ={
                fadeIn(
                    animationSpec = tween(
                        400, easing = LinearEasing
                    )
                )+slideIntoContainer(
                    animationSpec = tween(400, easing = EaseIn),
                    towards = AnimatedContentTransitionScope.SlideDirection.Start
                )
            },
            exitTransition = {
                fadeOut(
                    animationSpec = tween(
                        400, easing = LinearEasing
                    )
                ) +slideOutOfContainer(
                    animationSpec = tween(400, easing = EaseOut),
                    towards = AnimatedContentTransitionScope.SlideDirection.End
                )
            }
        ) { backStackEntry ->
            val orderListInJson = backStackEntry.toRoute<CreateOrderScreenRoute>().cartList
            val cartList =
                orderListInJson.let { Json.decodeFromString<List<ClientChoiceModelEntity>>(it) }
            val subTotalPrice = backStackEntry.toRoute<CreateOrderScreenRoute>().subTotalPrice
            CreateOrderScreen(
                cartList,
                orderViewModel,
                roomAddressViewModel,
                subTotalPrice,
                roomCartViewmodel,
                navController
            )
        }
        composable<AddressScreenRoute>(
            enterTransition ={
                fadeIn(
                    animationSpec = tween(
                        400, easing = LinearEasing
                    )
                )+slideIntoContainer(
                    animationSpec = tween(400, easing = EaseIn),
                    towards = AnimatedContentTransitionScope.SlideDirection.Start
                )
            }, exitTransition = {
                fadeOut(
                    animationSpec = tween(
                        400, easing = LinearEasing
                    )
                )+slideOutOfContainer(
                    animationSpec = tween(400, easing = EaseOut),
                    towards = AnimatedContentTransitionScope.SlideDirection.End
                )
            }
        ) {
            AddressScreen(navController, roomAddressViewModel)
        }

    }
}

