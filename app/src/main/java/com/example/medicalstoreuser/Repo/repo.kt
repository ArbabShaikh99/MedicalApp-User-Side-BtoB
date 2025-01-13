package com.example.medicalstoreuser.Repo

import android.util.Log
import com.example.medicalstoreuser.Data_Layer.ApiService
import com.example.medicalstoreuser.Data_Layer.Response.GetAlluserResponseStatus
import com.example.medicalstoreuser.Data_Layer.Response.Order.MedicalOrderResponseItem
import com.example.medicalstoreuser.Data_Layer.Response.ResponseStatus
import com.example.medicalstoreuser.Data_Layer.Response.product.getAllProductResponseItem
import com.example.medicalstoreuser.State.State
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Response
import javax.inject.Inject


class repo @Inject constructor(
    private val apiServices: ApiService){



    suspend fun signUpUser(
        name: String, password: String, email: String, phoneNumber: String, address: String,
        pinCode: String): Flow<State<Response<ResponseStatus>>> = flow {
           emit(State.Loading)
        try{
            val response = apiServices.singUpUser(name=name,password=password,email,
                phoneNumber=phoneNumber,address=address,pinCode=pinCode)
            emit(State.Success(response))
        }
        catch (e:Exception){
            emit(State.Error(e.message.toString()))
        }
    }

    suspend fun loginUser(email:String,password: String):Flow<State<Response<ResponseStatus>>> = flow {
        emit(State.Loading)
        try {
            val response = apiServices.LoginUser(email=email,password=password)
            emit(State.Success(response))
        }
        catch (e:Exception){
            emit(State.Error(e.message.toString()))
        }
    }

    suspend fun updateUserData(
        userId : RequestBody, userName: RequestBody,
        userEmail: RequestBody, userPhone: RequestBody,
         address: RequestBody, password: RequestBody,
        pinCode: RequestBody,userImage : MultipartBody.Part?
    ): Flow<State<Response<ResponseStatus>>> = flow{
        emit(State.Loading)
        try {
            val response = apiServices.updateUserData(
                userId = userId, name = userName,
                email = userEmail, phone_number = userPhone,
                address = address, password = password,
                pinCode = pinCode, pic = userImage!!)
            emit(State.Success(response))
        }catch ( e : Exception){
            emit(State.Error(e.message.toString()))
        }
    }

    suspend fun getSpecificUsersRepo(userId: String): Flow<State<Response<ArrayList<GetAlluserResponseStatus>>>> = flow {
            emit(State.Loading)
            try {
                val response = apiServices.getSpecificUserApi(userId=userId)
                emit(State.Success(response))
                Log.e("API Response", "Code: ${response.code()}, Message: ${response.message()}")
                Log.d("API Response", response.body()?.toString() ?: "Null response")

            } catch (e: Exception) {
                emit(State.Error(e.message.toString()))
            }
        }

    suspend fun getAllProductRepo():Flow<State<Response<ArrayList<getAllProductResponseItem>>>> = flow {
        emit(State.Loading)
        try {
            val response = apiServices.getAllProducService()
            emit(State.Success(response))
        } catch (e: Exception) {
            emit(State.Error(e.message.toString()))
        }
    }
    suspend fun getSpecificProduct(productId : String):Flow<State<Response<ArrayList<getAllProductResponseItem>>>> = flow {
        emit(State.Loading)
        try {
            val response = apiServices.getSpecificProduct(productId =productId)
            emit(State.Success(response))
        } catch (e: Exception) {
            emit(State.Error(e.message.toString()))
        }
    }


     suspend fun createOrder(
        orderList: List<MedicalOrderResponseItem>
    ): Flow<State<Response<ResponseStatus>>> = flow {
        emit(State.Loading)
        try {
            for (order in orderList) {
                val response = apiServices.createOrder(
                    userId = order.user_id,
                    productId = order.product_id,
                    productName = order.product_name,
                    productCategory = order.product_category,
                    userName = order.user_name,
                    isApproved = order.isApproved,
                    productQuantity = order.product_quantity,
                    productPrice = order.product_price.toFloat(),
                    subTotalPrice = order.subtotal_price.toFloat(),
                    deliveryCharge = order.delivery_charge.toFloat(),
                    taxCharge = order.tax_charge.toFloat(),
                    totalPrice = order.subtotal_price.toFloat() + order.delivery_charge.toFloat() + order.tax_charge.toFloat(),
                    orderDate = java.util.Date(),
                    userAddress = order.user_address,
                    userPinCode = order.user_pinCode,
                    userMobile = order.user_mobile,
                    userEmail = order.user_email,
                    productImageId = order.product_image_id,
                    orderStatus = order.order_status,
                    orderCancelStatus = order.order_cancel_status,
                    userStreet = order.user_street,
                    userCity = order.user_city,
                    userState = order.user_state,
                    discountPrice = order.discount_price,
                    shippedDate = order.shipped_date,
                    outOfDeliveryDate = order.out_of_delivery_date,
                    deliveredDate = order.delivered_date
                )
                emit(State.Success(response))
            }
        } catch (e: Exception) {
            emit(State.Error(e.message.toString()))
        }
    }

     suspend fun getAllUserOrders(
        userId: String
    ): Flow<State<Response<ArrayList<MedicalOrderResponseItem>>>>  = flow{
        emit(State.Loading)
        try {
            val response = apiServices.getAllOrders(userId)
            Log.d("@TAG", "getAllUserOrders: ${response.body()?.size}")
            emit(State.Success(response))
        }catch (e : Exception){
            emit(State.Error(e.message.toString()))
        }
    }


    }


