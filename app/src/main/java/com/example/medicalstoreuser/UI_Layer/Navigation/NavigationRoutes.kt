package com.example.medicalstoreuser.UI_Layer.Navigation

import kotlinx.serialization.Serializable


@Serializable
object ReceiveOrderRoute

@Serializable
object HomeScreen

@Serializable
object SignInScreen

@Serializable
object  SignUpScreen

@Serializable
object CartScreenRoute


@Serializable
data class VerificationScreeenRoute (
    val userId: String
)

//@Serializable
//data class ProductDetailScreenRoute(
//    val productId: String,
//    )

@Serializable
data class ProductDetailScreenRoute(
    val productName: String,
    val productId: String,
    val productImageId: String,
    val productPrice: Int,
    val productRating: Float,
    val productStock: Int,
    val productDescription: String,
    val productPower: String,
    val productCategory: String,
    val productExpiryDate: String,

    )

@Serializable
object SearchScreenRoutes

@Serializable
object BottomViewRoute

@Serializable
object UserProfilerRoute

@Serializable
object MyAccountScreenRoute

@Serializable
data class CreateOrderScreenRoute(
    val cartList : String,
    val subTotalPrice : Float
)

@Serializable
object AddressScreenRoute