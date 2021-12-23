package com.derifdev.loginwithgoogle

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import com.derifdev.loginwithgoogle.presentation.AuthScreen
import com.derifdev.loginwithgoogle.ui.theme.LoginWithGoogleTheme
import com.derifdev.loginwithgoogle.viewModel.AuthViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LoginWithGoogleTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    AuthScreen(viewModel = AuthViewModel())
                }
            }
        }
    }
}
