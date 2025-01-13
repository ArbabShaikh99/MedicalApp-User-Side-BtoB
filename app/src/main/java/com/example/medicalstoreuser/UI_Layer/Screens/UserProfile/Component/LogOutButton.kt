package com.example.medicalstoreuser.UI_Layer.Screens.UserProfile.Component

import PreferenceManager
import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.ui.Alignment
import androidx.navigation.NavController
import com.example.medicalstoreuser.UI_Layer.Navigation.SignInScreen


@Composable
fun LogoutDialog(
    showDialog: Boolean,
    onConfirm: () -> Unit,
    onDismiss: () -> Unit
) {
    if (showDialog) {
        AlertDialog(
            onDismissRequest = { onDismiss() },
            title = {
                Text(
                    text = "Log out of arbabshaikh99?",
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp
                )
            },
            text = {
                Text(
                    text = "Any drafts that you've saved will still be available on this device.",
                    fontSize = 14.sp
                )
            },
            confirmButton = {
                TextButton(onClick = onConfirm) {
                    Text(
                        text = "Log out",
                        color = Color.Blue,
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp
                    )
                }
            },
            dismissButton = {
                TextButton(onClick = onDismiss) {
                    Text(
                        text = "Cancel",
                        fontSize = 16.sp,
                        color = Color.Blue
                    )
                }
            }
        )
    }
}

@Composable
fun LogoutScreen(
    preferenceManager: PreferenceManager,
    navController: NavController
) {
    var showDialog by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.Start
    ) {
        Button(
            onClick = { showDialog = true },
            colors = ButtonDefaults.buttonColors(Color.Red)
        ) {
            Text(
                text = "Log out",
                color = Color.White,
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp
            )
        }
        LogoutDialog(
            showDialog = showDialog,
            onConfirm = {
                showDialog = false
                // Perform logout action here

                preferenceManager.setLoginUserId("")
                Log.d("@@Nav", "Logged out successfully")
                navController.navigate(SignInScreen)
            },
            onDismiss = { showDialog = false }
        )
    }
}
