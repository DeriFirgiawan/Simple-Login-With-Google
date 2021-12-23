package com.derifdev.loginwithgoogle.presentation

import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.derifdev.loginwithgoogle.utils.AuthResultContract
import com.derifdev.loginwithgoogle.viewModel.AuthViewModel
import com.google.android.gms.common.api.ApiException
import kotlinx.coroutines.launch

@Composable
fun AuthScreen(viewModel: AuthViewModel) {
    val coroutineScope = rememberCoroutineScope()
    var text by remember { mutableStateOf<String?>(null)}
    val user by remember(viewModel) {
        viewModel.user
    }.collectAsState()
    val signInRequest = 1

    val authResultLauncer = rememberLauncherForActivityResult(contract = AuthResultContract()) {
        task ->
        try {
            val account = task?.getResult(ApiException::class.java)
            if (account == null) {
                text = "Google Sign In Failed"
            } else {
                coroutineScope.launch {
                    viewModel.signIn(email = account.email, displayName = account.displayName)
                }
            }
        } catch (e: ApiException) {
            println(e)
            text = "Google Sign In Failed"
        }
    }

    Column(horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center, modifier = Modifier.fillMaxSize()) {
        Button(onClick = {
            authResultLauncer.launch(signInRequest)
        }) {
            Text(text = "Login")
        }
    }
}