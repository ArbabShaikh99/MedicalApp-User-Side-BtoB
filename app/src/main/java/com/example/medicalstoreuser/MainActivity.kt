  package com.example.medicalstoreuser


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.medicalstoreuser.UI_Layer.Navigation.AppNavigation
import com.example.medicalstoreuser.UI_Layer.Screens.Cart.CartScreenUI
import com.example.medicalstoreuser.UI_Layer.Screens.ReciveOrder.ReceiveOrderUI
import com.example.medicalstoreuser.local.viewmodel.RoomCartViewModel
import com.example.medicalstoreuser.ui.theme.MedicalStoreUserTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MedicalStoreUserTheme {
                val navcontroller = NavController(this)

                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                   Box(modifier = Modifier
                       .fillMaxSize()
                       .padding(innerPadding)){


                   AppNavigation(navcontroller)


                   }
                }
            }
        }
    }
}

