
//
//import android.content.Context
//import android.content.SharedPreferences
//
//class PreferenceManager(private val context: Context) {
//
//    private val sharedPreferences: SharedPreferences = context.getSharedPreferences("MyMedicalShopUserPrefs", Context.MODE_PRIVATE)
//
//    // Keys
//    companion object {
//        private const val KEY_IS_LOGGED_IN = "isLoggedIn"
//        private const val KEY_IS_APPROVED = "isApproved"
//        private const val KEY_USER_NAME = "userName"
//        private const val KEY_EMAIL_ID = "emailId"
//    }
//
//    // for already login or not
//    fun setLoginUserId(userId: String) {
//        sharedPreferences.edit().putString(KEY_IS_LOGGED_IN, userId).apply()
//    }
//
//    fun getLoginUserId(): String? {
//        return sharedPreferences.getString(KEY_IS_LOGGED_IN, "")
//    }
//
//    fun setApprovedStatus(isApproved: Int = 0) {
//        sharedPreferences.edit().putInt(KEY_IS_APPROVED, isApproved).apply()
//    }
//
//    fun getApprovedStatus(): Int {
//        return sharedPreferences.getInt(KEY_IS_APPROVED, 0)
//    }
//
//    fun setLoginUserName(userName: String) {
//        sharedPreferences.edit().putString(KEY_USER_NAME, userName).apply()
//    }
//
//    fun getLoginUserName(): String? {
//        return sharedPreferences.getString(KEY_USER_NAME, "")
//    }
//
//    fun setLoginEmailId(emailId: String) {
//        sharedPreferences.edit().putString(KEY_EMAIL_ID, emailId).apply()
//    }
//
//    fun getLoginEmailId(): String? {
//        return sharedPreferences.getString(KEY_EMAIL_ID, "")
//    }
//}

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.util.Log

class PreferenceManager( private  val context: Context) {

    private val isLogin = context.getSharedPreferences("isLoggedIn", MODE_PRIVATE)
    private val isApprove = context.getSharedPreferences("isApproved", MODE_PRIVATE)
    private val userName = context.getSharedPreferences("userName", MODE_PRIVATE)
    private val emailId = context.getSharedPreferences("EmailId", MODE_PRIVATE)



    //for already login or not
    fun setLoginUserId(userId: String) {
        with(isLogin.edit()) {
            putString("isLoggedIn", userId)
            apply()
        }
    }

    fun getLoginUserId(): String? {
        return isLogin.getString("isLoggedIn", "")
    }
    fun setApprovedStatus(isApproved: Int = 0) {
        Log.d("Preferences", "Saving isApproved: $isApproved")
        with(isApprove.edit()) {
            putInt("isApproved", isApproved)
            apply()
        }
    }

    fun getApprovedStatus(): Int {
        val status = isApprove.getInt("isApproved", 0)
        Log.d("Preferences", "Retrieved isApproved: $status")
        return status
    }

    fun setLoginUserName(userName: String) {
        with(this.userName.edit()) {
            putString("userName", userName)
            apply()
        }
    }
    fun getLoginUserName(): String? {
        return this.userName.getString("userName", "")
    }

    fun setLoginEmailId(emailId: String) {
        with(this.emailId.edit()) {
            putString("emailId", emailId)
            apply()
        }
    }
    fun getLoginEmailId(): String? {
        return this.emailId.getString("emailId", "")
    }
}

