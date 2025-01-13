package com.example.medicalstoreuser.ViewModel


import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.medicalstoreuser.FunctionResponseState.LoginSignUpResponseState
import com.example.medicalstoreuser.UI_Layer.ScreenState.UserState.LoginScreenState
import com.example.medicalstoreuser.UI_Layer.ScreenState.UserState.SignUpScreenState
import com.example.medicalstoreuser.State.State
import com.example.medicalstoreuser.Repo.repo
import com.example.medicalstoreuser.FunctionResponseState.GetAllUserState
import com.example.medicalstoreuser.FunctionResponseState.User.UpdateUserResponseState
import com.example.medicalstoreuser.UI_Layer.ScreenState.UserState.UpdateUserScreenState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.toRequestBody
import javax.inject.Inject


@HiltViewModel
class UserViewModel @Inject constructor(private val repo: repo) : ViewModel() {

//    private val _getAllUserStateCheckApprove = MutableStateFlow(GetAllUserState())
//    val getAllUserStateCheckApprove = _getAllUserStateCheckApprove.asStateFlow()

     private val _getSpecificUser = MutableStateFlow(GetAllUserState())
    val getSpecificUser = _getSpecificUser.asStateFlow()

    private val _signupResponseState = MutableStateFlow(LoginSignUpResponseState())
    val signupResponseState = _signupResponseState.asStateFlow()

    private val _signupScreenStateData = MutableStateFlow(SignUpScreenState())
    val signupScreenStateData = _signupScreenStateData.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = SignUpScreenState()
    )

    private val _loginResponseState = MutableStateFlow(LoginSignUpResponseState())
    val loginResponseState = _loginResponseState.asStateFlow()

    private val _loginScreenStateData = MutableStateFlow(LoginScreenState())
    val loginScreenStateData = _loginScreenStateData.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = LoginScreenState()
    )

    private val _updateProfileUserData = MutableStateFlow(UpdateUserResponseState())
    val updateProfileResponseState = _updateProfileUserData.asStateFlow()

    private val _UserUpdateScreenStateUserData = MutableStateFlow(UpdateUserScreenState())
    val UserUpdateScreenStateUserData = _UserUpdateScreenStateUserData.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = UpdateUserScreenState()
    )

     fun SignUpUser(name: String, password : String, email:String,
        phoneNumber: String, address:String, pinCode:String){

         viewModelScope.launch (Dispatchers.IO) {

              repo.signUpUser(  name = name, password = password,
                 email = email, phoneNumber = phoneNumber, address = address, pinCode = pinCode
             ).collect{
                 when(it){
                 is State.Loading -> {
                  _signupResponseState.value = LoginSignUpResponseState(isLoading = true)

                 }
                     is State.Success -> {
                         _signupResponseState.value=    LoginSignUpResponseState(data =it.data.body())
                     }

                     is State.Error -> {
                        _signupResponseState.value = LoginSignUpResponseState(error = it.message)
                     }
                 }
             }
         }
    }

    fun loginUser(email :String , password: String){
    viewModelScope.launch(Dispatchers.IO) {
        repo.loginUser(
            email=email,
            password=password
        ).collect{
            when(it){
                is State.Loading ->{
                    _loginResponseState.value = LoginSignUpResponseState(isLoading = true)
                }
                is State.Success ->{
                    _loginResponseState.value = LoginSignUpResponseState(data =it.data.body())
                }
                is State.Error ->{
                    _loginResponseState.value = LoginSignUpResponseState(error = it.message)
                }
            }
        }
    }
    }



    fun  getSpecificUserVM(userId: String) {
        viewModelScope.launch(Dispatchers.IO) {
            repo.getSpecificUsersRepo(userId).collect { state ->
                when (state) {
                    is State.Loading -> {
                        _getSpecificUser.value = GetAllUserState(loading = true)
                    }
                    is State.Success ->{
                        _getSpecificUser.value = GetAllUserState(data = state.data,loading = false)
                    }
                    is State.Error -> {
                        _getSpecificUser.value = GetAllUserState(Error = state.message,loading = false)
                    }

                }
            }
        }
    }

    fun updateUserData(loginUserId: String
                    , userImageFile: MultipartBody.Part?
    ) {
        viewModelScope.launch {
            Log.d("@Acc", "updateUserData: ${UserUpdateScreenStateUserData.value.userName.value}")
            Log.d("@Acc", "updateUserData: ${UserUpdateScreenStateUserData.value.userName.value}")
            Log.d("@Acc", "updateUserData: ${UserUpdateScreenStateUserData.value.address.value}")
            Log.d("@Acc", "updateUserData: ${UserUpdateScreenStateUserData.value.address.value}")
           // Log.d("@Acc", "updateUserData userImageFile: ${userImageFile}")
            repo.updateUserData(
                userId = loginUserId.toRequestBody("text/plain".toMediaTypeOrNull()),
                userName = UserUpdateScreenStateUserData.value.userName.value.toRequestBody("text/plain".toMediaTypeOrNull()),
                userEmail = UserUpdateScreenStateUserData.value.userEmail.value.toRequestBody("text/plain".toMediaTypeOrNull()),
                pinCode = UserUpdateScreenStateUserData.value.pinCode.value.toRequestBody("text/plain".toMediaTypeOrNull()),
                userPhone = UserUpdateScreenStateUserData.value.userPhone.value.toRequestBody("text/plain".toMediaTypeOrNull()),
                address = UserUpdateScreenStateUserData.value.address.value.toRequestBody("text/plain".toMediaTypeOrNull()),
                password = UserUpdateScreenStateUserData.value.password.value.toRequestBody("text/plain".toMediaTypeOrNull()),
              userImage = userImageFile
            ).collect{
                when(it){
                    is State.Loading->{
                        _updateProfileUserData.value = UpdateUserResponseState(isLoading = true)
                    }
                    is State.Success->{
                        _updateProfileUserData.value = UpdateUserResponseState(data = it.data.body())
                    }
                    is State.Error->{
                        _updateProfileUserData.value = UpdateUserResponseState(error = it.message)
                    }
                }
            }
        }
    }

    fun resetSignupScreenStateData() {
        _signupScreenStateData.value = SignUpScreenState(
            userName = mutableStateOf(""),
            mobileNo = mutableStateOf(""),
            email = mutableStateOf(""),
            password = mutableStateOf(""),
            address = mutableStateOf(""),
            pinCode = mutableStateOf("")
        )
    }

    fun resetLoginScreenStateData() {
        _loginScreenStateData.value = LoginScreenState(
            email = mutableStateOf(""),
            password = mutableStateOf("")
        )
    }

    fun clearLoginResponseStateData(){
        _loginResponseState.value = LoginSignUpResponseState(
            isLoading = false,
            data = null,
            error = null
        )
    }


}









